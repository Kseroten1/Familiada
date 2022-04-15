import Game.Board.Board;
import Game.Game;
import Game.phrase.Phrase;
import Game.team.Team;

import java.util.ArrayList;
import java.util.List;

public class FamiliadaTheGame {
    public static void main(String[] args) {
        List<Phrase> test = new ArrayList<>();
        test.add(new Phrase("spacer", 10));
        test.add(new Phrase("muzyka", 8));
        test.add(new Phrase("zakupy", 7));
        test.add(new Phrase("kąpiel", 6));
        test.add(new Phrase("spotkanie z przyjaciółmi", 5));
        test.add(new Phrase("sport", 4));

        Team redTeam = new Team("chuje");
        Team blueTeam = new Team("cipy");

        Game game = new Game(blueTeam, redTeam);
        game.playGame();
    }
}