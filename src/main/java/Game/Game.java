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

    public void guessFinal(String guess) {
        int score = 0;
        for (Phrase p : phrases) {
            if (guess.equals(p.getPhrase())) {
                score = p.getScore();
            }
        }
        board.setGuessedFinal(guess, score);
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
        while (currentTeam.getBadGuessesCounter() < 3 && sumOfPoints != currentScore) {
            System.out.printf("Tura drużyny %s\n", currentTeam.getName());
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
        test1.add(new Phrase("kawe", 30));
        test1.add(new Phrase("herbate", 25));
        test1.add(new Phrase("kakao", 19));
        test1.add(new Phrase("sok", 16));
        test1.add(new Phrase("mleko", 13));
        test1.add(new Phrase("wode", 10));
        listOfPhraseListFinal.add(test1);
        List<Phrase> test2 = new ArrayList<>();
        test2.add(new Phrase("jabłko", 28));
        test2.add(new Phrase("gruszka", 24));
        test2.add(new Phrase("śliwka", 22));
        test2.add(new Phrase("winogrono", 18));
        test2.add(new Phrase("brzoskwinia", 15));
        test2.add(new Phrase("nektarynka", 12));
        listOfPhraseListFinal.add(test2);
        List<Phrase> test3 = new ArrayList<>();
        test3.add(new Phrase("na krześle", 27));
        test3.add(new Phrase("na ławce", 25));
        test3.add(new Phrase("na fotelu", 21));
        test3.add(new Phrase("na kanapie", 19));
        test3.add(new Phrase("na trawie", 14));
        test3.add(new Phrase("na kocu", 10));
        listOfPhraseListFinal.add(test3);
        List<Phrase> test4 = new ArrayList<>();
        test4.add(new Phrase("słońce", 30));
        test4.add(new Phrase("banan", 25));
        test4.add(new Phrase("cytryna", 23));
        test4.add(new Phrase("farba", 20));
        test4.add(new Phrase("koszulka", 16));
        test4.add(new Phrase("kwiat", 12));
        listOfPhraseListFinal.add(test4);
        List<Phrase> test5 = new ArrayList<>();
        test5.add(new Phrase("konewka", 25));
        test5.add(new Phrase("kran", 20));
        test5.add(new Phrase("kwiaty", 18));
        test5.add(new Phrase("kretowisko", 16));
        test5.add(new Phrase("konwalia", 14));
        test5.add(new Phrase("krzewy", 12));
        listOfPhraseListFinal.add(test5);
        List<Phrase> test6 = new ArrayList<>();
        test6.add(new Phrase("portfel", 30));
        test6.add(new Phrase("parasol", 25));
        test6.add(new Phrase("dokumenty", 20));
        test6.add(new Phrase("klucze", 15));
        test6.add(new Phrase("skarpetke", 10));
        test6.add(new Phrase("kolczyk", 5));
        listOfPhraseListFinal.add(test6);
        ListIterator<List<Phrase>> iter = listOfPhraseListFinal.listIterator();

        for (int i = 0; i < 12; i++) {
            if (i == 6) {
                for (int j = 0; j < 6; j++) {
                    setPhrases(iter.previous());
                }
            }
            setPhrases(iter.next());
            input = scanner.nextLine();
            guessFinal(input);
            board.printBoardFinal();
        }
    }

    public void playGame() {
        List<List<Phrase>> listOfPhraseList = new ArrayList<>();
        List<Phrase> test = new ArrayList<>();
        test.add(new Phrase("żywica", 25));
        test.add(new Phrase("guma", 20));
        test.add(new Phrase("miód", 18));
        test.add(new Phrase("syrop", 13));
        test.add(new Phrase("klej", 8));
        test.add(new Phrase("landrynka", 5));
        listOfPhraseList.add(test);
        List<Phrase> test1 = new ArrayList<>();
        test1.add(new Phrase("sarna", 23));
        test1.add(new Phrase("zając", 19));
        test1.add(new Phrase("dzik", 16));
        test1.add(new Phrase("bocian", 12));
        test1.add(new Phrase("czapla", 10));
        test1.add(new Phrase("lis", 4));
        listOfPhraseList.add(test1);
        List<Phrase> test2 = new ArrayList<>();
        test2.add(new Phrase("spodnie", 24));
        test2.add(new Phrase("skarpety", 18));
        test2.add(new Phrase("spódnica", 15));
        test2.add(new Phrase("sukienka", 14));
        test2.add(new Phrase("sweter", 9));
        test2.add(new Phrase("szalik", 6));
        listOfPhraseList.add(test2);
        List<Phrase> test3 = new ArrayList<>();
        test3.add(new Phrase("borowiki", 27));
        test3.add(new Phrase("podgrzybki", 16));
        test3.add(new Phrase("kurki", 14));
        test3.add(new Phrase("sowy", 10));
        test3.add(new Phrase("rydze", 7));
        test3.add(new Phrase("maślaki", 3));
        listOfPhraseList.add(test3);
        List<Phrase> test4 = new ArrayList<>();
        test4.add(new Phrase("okna", 24));
        test4.add(new Phrase("drzwi", 21));
        test4.add(new Phrase("toaleta", 16));
        test4.add(new Phrase("fotel", 13));
        test4.add(new Phrase("stolik", 8));
        test4.add(new Phrase("korytarz", 5));
        listOfPhraseList.add(test4);
        List<Phrase> test5 = new ArrayList<>();
        test5.add(new Phrase("sum", 30));
        test5.add(new Phrase("pies", 30));
        test5.add(new Phrase("kot", 30));
        test5.add(new Phrase("mysz", 30));
        test5.add(new Phrase("foka", 30));
        test5.add(new Phrase("wiewiórka", 30));
        listOfPhraseList.add(test5);


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