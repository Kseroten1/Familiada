package Game.Board;

import Game.phrase.Phrase;
import Game.team.Team;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Board {
    private final Map<Phrase, Boolean> guessed = new TreeMap<>();
    private int sumOfScoreGuessedPhrases = 0;
    private final Team redTeam;
    private final Team blueTeam;

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
                redTeam.getName(), redTeam.getPoints(),"X".repeat(redTeam.getBadGuessesCounter()));
    }
}
