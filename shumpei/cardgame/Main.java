package shumpei.cardgame;

import shumpei.cardgame.blackjack.Blackjack;

public class Main {

    public static void main(String[] args) {

        Blackjack blackJack = Blackjack.createNormalModeBlackjack();
//        Blackjack blackJack = Blackjack.createNormalModeBlackjack();
        blackJack.start();
        
    }
}
