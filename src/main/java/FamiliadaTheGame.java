import Game.Board.Board;
import Game.Game;
import Game.phrase.Phrase;
import Game.team.Team;

import java.util.ArrayList;
import java.util.List;

public class FamiliadaTheGame {
    public static void main(String[] args) {

        //ZŁA ODPOWIEDŹ NIE MOŻE BYĆ DOBRĄ ODPOWIEDZIĄ NA INNE PYTANIE !


        Team redTeam = new Team("Czerwoni");
        Team blueTeam = new Team("Niebiescy");

        Game game = new Game(blueTeam, redTeam);
        game.playGame();

        //game.finalGuesses();
    }
}