package shumpei.blackjack;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Player player = new Player("プレイヤー", 1000);
        Player dealer = new Player("ディーラー", 0);
        GameMsg gameMsg = new GameMsg();
        CardStuck cardStuck = new CardStuck();
        Judge judge = new Judge();
        Game game = new Game(player, dealer, gameMsg, cardStuck, judge);
        game.start();
        
    }
}
