package Game;

import Game.Board.Board;
import Game.phrase.Phrase;
import Game.team.Team;

import java.util.*;

public class Game {
    private Board board = null;
    List<Phrase> phrases = null;
    final private Team redTeam;
    final private Team blueTeam;
    private boolean redTurn;
    private int maxScore;
    private int sumOfPoints;
    private final Scanner scanner = new Scanner(System.in);

    public Game(Team blueTeam, Team redTeam) {
        this.blueTeam = blueTeam;
        this.redTeam = redTeam;
    }

    public void setPhrases(List<Phrase> phrases) {
        this.phrases = phrases;
        maxScore = phrases.stream().min(Phrase::compareTo).get().getScore();
        sumOfPoints = 0;
        for (Phrase p : phrases) {
            sumOfPoints += p.getScore();
        }
        this.board = new Board(phrases, redTeam, blueTeam);
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public int guess(String guess) {
        for (Phrase p : phrases) {
            if (guess.equals(p.getPhrase())) {
                board.setGuessed(guess);
                return p.getScore();
            }
        }
        return 0;
    }

    public int guessFinal(String guess) {
        for (Phrase p : phrases) {
            if (guess.equals(p.getPhrase())) {
                board.setGuessedFinal(guess);
                return p.getScore();
            }
        }
        return 0;
    }

    public void determineTurn() {
        board.printBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Która drużyna podaje hasło:");
        String whichTeamFirst = scanner.nextLine();

        System.out.println("Podaj hasło: ");
        String guess = scanner.nextLine();

        int firstScore = guess(guess);

        if (firstScore == maxScore) {
            redTurn = whichTeamFirst.equals("red");
            board.printBoard();
            return;
        }
        System.out.println("Podaj 2 hasło: ");
        guess = scanner.nextLine();
        int secondScore = guess(guess);

        if (firstScore > secondScore) {
            redTurn = whichTeamFirst.equals("red");
        } else {
            redTurn = false;
        }
        board.printBoard();
        if(firstScore == 0 && secondScore == 0){
            determineTurn();
        }
    }

    public void takeGuess() {
        Team currentTeam = redTurn ? redTeam : blueTeam;
        int currentScore = board.getCurrentScore();
        int checkingScore = 0;
        String guess = "";
        System.out.println(sumOfPoints);
        while (currentTeam.getBadGuessesCounter() < 3 && sumOfPoints != currentScore) {
            System.out.printf("Tura drużyny %s\n", currentTeam.getName());
            System.out.println(currentScore);
            guess = scanner.nextLine();
            checkingScore = guess(guess);
            if (checkingScore == 0) {
                currentTeam.markBadGuess();
            }
            board.printBoard();
            currentScore += checkingScore;
        }

        if (currentTeam.getBadGuessesCounter() != 3) {
            System.out.printf("Ture wygrywa drużyna %s!\n", currentTeam.getName());
            currentTeam.addPoints(sumOfPoints);
            return;
        }

        redTurn = !redTurn;
        currentTeam = redTurn ? redTeam : blueTeam;
        System.out.printf("Drużyna %s przejmuje, hasło:\n", currentTeam.getName());
        guess = scanner.nextLine();
        checkingScore = guess(guess);
        if(checkingScore != 0){
            currentScore += checkingScore;
            currentTeam.addPoints(currentScore);
            System.out.printf("Ture wygrywa drużyna %s!\n", currentTeam.getName());
            return;
        }

        redTurn = !redTurn;
        currentTeam = redTurn ? redTeam : blueTeam;
        currentTeam.addPoints(currentScore);
        System.out.printf("Ture wygrywa drużyna %s!\n", currentTeam.getName());
    }

    public void finalGuesses() {
        String input = "";
        int inputScore = 0;
        List<List<Phrase>> listOfPhraseListFinal = new ArrayList<>();
        List<Phrase> test1 = new ArrayList<>();
        test1.add(new Phrase("1", 22));
        test1.add(new Phrase("2", 10));
        test1.add(new Phrase("3", 8));
        test1.add(new Phrase("4", 5));
        test1.add(new Phrase("5", 4));
        test1.add(new Phrase("6", 3));
        listOfPhraseListFinal.add(test1);
        List<Phrase> test2 = new ArrayList<>();
        test2.add(new Phrase("11", 22));
        test2.add(new Phrase("22", 10));
        test2.add(new Phrase("33", 8));
        test2.add(new Phrase("44", 5));
        test2.add(new Phrase("55", 4));
        test2.add(new Phrase("66", 3));
        listOfPhraseListFinal.add(test2);
        List<Phrase> test3 = new ArrayList<>();
        test3.add(new Phrase("111", 22));
        test3.add(new Phrase("222", 10));
        test3.add(new Phrase("333", 8));
        test3.add(new Phrase("444", 5));
        test3.add(new Phrase("555", 4));
        test3.add(new Phrase("666", 3));
        listOfPhraseListFinal.add(test3);
        ListIterator<List<Phrase>> iter = listOfPhraseListFinal.listIterator();

        for (int i = 0; i < 12; i++) {
            setPhrases(iter.next());
            input = scanner.nextLine();
            inputScore = guessFinal(input);
            board.printBoardFinal(input, inputScore);
        }
    }

    public void playGame() {
        List<List<Phrase>> listOfPhraseList = new ArrayList<>();
        List<Phrase> test = new ArrayList<>();
        test.add(new Phrase("spacer", 250));
        test.add(new Phrase("muzyka", 10));
        test.add(new Phrase("zakupy", 6));
        test.add(new Phrase("kąpiel", 5));
        test.add(new Phrase("spotkanie z przyjaciółmi", 4));
        test.add(new Phrase("sport", 3));
        listOfPhraseList.add(test);
        List<Phrase> test1 = new ArrayList<>();
        test1.add(new Phrase("spacerki", 22));
        test1.add(new Phrase("muzyczka", 10));
        test1.add(new Phrase("zakupki", 8));
        test1.add(new Phrase("kąpielki", 5));
        test1.add(new Phrase("spotkanie z przyjaciółkami", 4));
        test1.add(new Phrase("sportowe", 3));
        listOfPhraseList.add(test1);
        List<Phrase> test2 = new ArrayList<>();
        test1.add(new Phrase("1", 22));
        test1.add(new Phrase("2", 10));
        test1.add(new Phrase("3", 8));
        test1.add(new Phrase("4", 5));
        test1.add(new Phrase("5", 4));
        test1.add(new Phrase("6", 3));
        listOfPhraseList.add(test2);

        ListIterator<List<Phrase>> iter = listOfPhraseList.listIterator();
        while (redTeam.getPoints() < 200 && blueTeam.getPoints() < 200) {
            if (!iter.hasNext()) {
                System.out.println("Koniec gry dzieciaczki!");
                break;
            }
            setPhrases(iter.next());
            determineTurn();
            takeGuess();
            redTeam.resetBadGuessCounter();
            blueTeam.resetBadGuessCounter();
        }
        finalGuesses();
    }
}
