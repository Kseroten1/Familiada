package Game.phrase;

public class Phrase implements Comparable<Phrase> {
    private final String phrase;
    private final int score;

    public Phrase(String phrase, int score) {
        this.phrase = phrase;
        this.score = score;
    }

    public String getPhrase() {
        return phrase;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Phrase o) {
        if (o != null) {
            return -(this.score - o.score);
        }
        return -1;
    }

    @Override
    public String toString() {
        return String.format("%s (%dp)", phrase, score);
    }
}
