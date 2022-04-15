package Game.team;

public class Team {
    private final String name;
    private int points = 0;
    private int badGuesses = 0 ;

    public Team(String name) {
        this.name = name;
    }

    public int getBadGuessesCounter() {
        return badGuesses;
    }

    public void markBadGuess() {
        badGuesses++;
    }

    public void resetBadGuessCounter() {
        badGuesses = 0;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }
}