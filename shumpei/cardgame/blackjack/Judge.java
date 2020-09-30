package shumpei.cardgame.blackjack;

public class Judge {
    /**
     * 手札に基づく勝敗判定メソッド
     * @param playerHand プレイヤーのHandオブジェクト
     * @param dealerHand ディーラーのHandオブジェクト
     * @return 0（ディーラーの勝利）/1（引き分け）/2（プレーヤーの勝利）
     */
    public int handJudge(Hand playerHand, Hand dealerHand) {
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

    /**
     * プレイヤーのポイントに基づく勝敗判定メソッド
     * @param playerPoint
     * @return
     */
    public int finalJudge(double playerPoint) {
        final int loose = 0;
        final int next = 1;
        final int win = 2;

        if (playerPoint < 1) {
            return loose;
        }

        if (playerPoint >= 2000) {
            return win;
        }

        return next;
    }

}
