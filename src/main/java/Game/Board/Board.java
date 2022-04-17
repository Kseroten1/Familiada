package Game.Board;

import Game.phrase.Phrase;
import Game.team.Team;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Board {
    private final Map<Phrase, Boolean> guessed = new TreeMap<>();
    private int sumOfScoreGuessedPhrases = 0;
    private final Team redTeam;
    private final Team blueTeam;
    private static final List<Phrase> finalAnswer = new ArrayList<>();

    public Board(List<Phrase> initializeWords, Team redTeam, Team blueTeam) {
        for (Phrase phrase : initializeWords) {
            guessed.put(phrase, false);
        }
        this.redTeam = redTeam;
        this.blueTeam = blueTeam;
    }

    public void setGuessed(String phrase) {
        int score = 0;
        for (Phrase p : guessed.keySet()) {
            if (p.getPhrase().equals(phrase)) {
                score = p.getScore();
            }
        }
        if (score == 0) return;
        sumOfScoreGuessedPhrases += score;
        guessed.put(new Phrase(phrase, score), true);
    }

    public void setGuessedFinal(String phrase, int score) {
        finalAnswer.add(new Phrase(phrase, score));
    }

    public int getCurrentScore() {
        return sumOfScoreGuessedPhrases;
    }

    public void printBoard() {
        String notGuessedPlaceholder = "----------------------- ##";

        int index = 1;
        for (Map.Entry<Phrase, Boolean> entry : guessed.entrySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            String phrase = entry.getKey().getPhrase();
            int score = entry.getKey().getScore();
            if (entry.getValue()) {
                stringBuilder.append(phrase);
                int phraseRightPadding = notGuessedPlaceholder.length() - phrase.length() - 1;
                if (score / 10 >= 1) {
                    phraseRightPadding -= 1;
                }
                stringBuilder.append(" ".repeat(Math.max(0, phraseRightPadding)));
                stringBuilder.append(score);
            } else {
                stringBuilder.append(notGuessedPlaceholder);
            }
            System.out.printf("%d. %s\n", index, stringBuilder);
            index++;
        }

        System.out.printf("             Suma: %d\n", sumOfScoreGuessedPhrases);
        System.out.printf("%s:%d %s     %s:%d %s\n",
                blueTeam.getName(), blueTeam.getPoints(), "X".repeat(blueTeam.getBadGuessesCounter()),
                redTeam.getName(), redTeam.getPoints(), "X".repeat(redTeam.getBadGuessesCounter()));
    }

    public void printBoardFinal() {
        String notGuessedPlaceholderLeft = "------------------ ##";
        String notGuessedPlaceholderRight = "## ------------------";


        for (int i = 0; i < 6; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            if (i < finalAnswer.size()) {
                Phrase answerLeft = finalAnswer.get(i);
                int phraseRightPadding = notGuessedPlaceholderLeft.length() - answerLeft.getPhrase().length() - 1;
                if (answerLeft.getScore() / 10 >= 1) {
                    phraseRightPadding -= 1;
                }
                stringBuilder.append(answerLeft.getPhrase());
                stringBuilder.append(" ".repeat(Math.max(0, phraseRightPadding)));
                stringBuilder.append(answerLeft.getScore());
            } else {
                stringBuilder.append(notGuessedPlaceholderLeft);

            }
            stringBuilder.append(" ".repeat(4));
            System.out.printf("%s", stringBuilder);

            if (i + 6 < finalAnswer.size()) {
                Phrase answerRight = finalAnswer.get(i + 6);
                System.out.printf("%s", answerRight);
            } else {
                System.out.printf("%s", notGuessedPlaceholderRight);
            }
            System.out.println();
        }
    }
}