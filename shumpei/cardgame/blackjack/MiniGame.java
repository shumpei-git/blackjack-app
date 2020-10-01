package shumpei.cardgame.blackjack;

import shumpei.cardgame.playingcard.Card;
import shumpei.cardgame.playingcard.CardStuck;

import java.util.List;
import java.util.Scanner;

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

        ready();

        makePlayerAct();

        makeDealerAct();

        int miniGameResult = judge();

        showMiniGameResult(miniGameResult);

        return new Result(miniGameResult, playerHand.getRate());
    }

    private void ready() {
        //プレイヤーに手札を２枚配る
        //ディーラーに手札を２枚配る
        miniGameMsg.dealOutCards();
        for (int i = 0; i < 2; i++) {
            playerHand.addOneCardFrom(cardStuck);
            dealerHand.addOneCardFrom(cardStuck);
        }

        //プレイヤーのカードを表示
        miniGameMsg.showHand(playerHand);
        //ディーラーのカードを表示（２枚目は画面上 *として表示）
        miniGameMsg.showFirstCard(dealerHand);

        miniGameMsg.lineFeed();
    }

    private void makePlayerAct() {
        //プレイヤーがカードを引くか選択（ストップするかバーストするかまで繰り返し）
        while(true) {
            miniGameMsg.hitOrStand();
            int hitOrStand = selectHitOrStand();
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

    private int selectHitOrStand() {
        while (true) {
            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()) {
                int num = in.nextInt();
                if (num == 0 || num == 1) {
                    return num;
                } else {
                    System.out.println("入力値が不正です。再入力してください。");
                }
            } else {
                System.out.println("入力値が不正です。再入力してください。");
            }
        }
    }

    private void makeDealerAct() {
        //ディーラーがカードを引く（手札を17以上にするまでカードを引く）
        while(true){
            // 手札を17以上にするまでカードを引く
            if (dealerHand.getScore() >= 17) {
                break;
            }
            dealerHand.addOneCardFrom(cardStuck);
            // バースト判定
            if (dealerHand.checkBust()) {
                break;
            }
        }
        miniGameMsg.dealerAction();
        miniGameMsg.lineFeed();
    }

    private int judge() {
        final int dealerWin = 0;
        final int tie = 1;
        final int playerWin = 2;
        final int playerScore = playerHand.getScore();
        final int dealerScore = dealerHand.getScore();
        final int numOfPlayerCards = playerHand.getCardList().size();
        final int numOfDealerCards = dealerHand.getCardList().size();

        // 少なくともどちらかがバーストしている場合
        if (playerHand.checkBust()) {// プレイヤーがバーストしていたらディーラーの勝ち（ディーラーのバーストは関係ない）
            return dealerWin;
        }
        if (dealerHand.checkBust()) {// ディーラーがバーストしていたら、プレイヤーの勝ち
            return playerWin;
        }

        // どちらもバーストせず、かつ同点でない場合
        if (dealerScore > playerScore) {// ディーラーがプレイヤーよりもスコアが高いなら、ディーラーの勝ち
            return dealerWin;
        }
        if (playerScore > dealerScore) {// プレイヤーがディーラーよりもスコアが高いなら、プレイヤーの勝ち
            return playerWin;
        }

        // どちらもバーストせず、かつ同点の場合
        if (dealerScore == 21 && numOfDealerCards == 2) {// どちらも21点だが、ディーラーの手札が2枚なら、ディーラーの勝ち（プレイヤーの手札数は関係ない）
            return dealerWin;
        }
        if (dealerScore == 21 && numOfPlayerCards == 2) {// どちらも21点だが、プレイヤーのみ手札が2枚なら、プレイヤーの勝ち
            return playerWin;
        }

        // 上のいずれでもなければ引き分け
        return tie;
    }

    private void showMiniGameResult(int miniGameResult) {
        miniGameMsg.result();
        miniGameMsg.showHand(playerHand);
        miniGameMsg.showHand(dealerHand);
        if (miniGameResult == 0) {
            miniGameMsg.loose();
        } else if (miniGameResult == 1) {
            miniGameMsg.tie();
        } else if (miniGameResult == 2) {
            miniGameMsg.win();
        }

        miniGameMsg.lineFeed();
    }
}
