package shumpei.cardgame.blackjack;

import java.util.List;

class MiniGame {
    private List<Card> cardStuck;
    private Hand playerHand;
    private Hand dealerHand;
    private MiniGameMsg miniGameMsg;

    MiniGame() {
        cardStuck = new CardStuck().getCardList();
        playerHand = new Hand();
        dealerHand = new Hand();
        miniGameMsg = new MiniGameMsg();
    }

    Result play() {



        int dummyInt = 100;
        String dummyString = "";
        return new Result(dummyInt, dummyString);
    }

    private void makePlayerAct() {
        //プレイヤーがカードを引くか選択（ストップするかバーストするかまで繰り返し）
        while(true) {
            miniGameMsg.hitOrStand();
            int hitOrStand = Input.selectHitOrStand();
            if (hitOrStand == 0) {
                miniGameMsg.stand();
                break;
            }
            playerHand.addOneCardFrom(cardStuck);
            miniGameMsg.hit();
            miniGameMsg.showHand(playerHand);
            // バースト判定
            if (playerHand.checkBust()) {
                miniGameMsg.bust();
                break;
            }
            miniGameMsg.lineFeed();
        }
        miniGameMsg.lineFeed();
    }
}
