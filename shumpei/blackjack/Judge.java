package shumpei.blackjack;

import java.util.List;

public class Judge {
    /**
     *
     * @param playerHand
     * @param dealerHand
     * @return 0（ディーラーの勝利）/1（引き分け）/2（プレーヤーの勝利）
     */
    public int handJudge(Hand playerHand, Hand dealerHand) {
        int playerScore = playerHand.getScore();
        int dealerScore = dealerHand.getScore();
        int numOfPlayerCards = playerHand.getCardList().size();
        int numOfDealerCards = dealerHand.getCardList().size();

        // 少なくともどちらかがバーストしている場合

        if (playerScore > 21) {// プレイヤーがバーストしていたらディーラーの勝ち（ディーラーのバーストは関係ない）
            return 0;
        }

        if (dealerScore > 21) {// ディーラーがバーストしていたら、プレイヤーの勝ち
            return 2;
        }

        // どちらもバーストせず、かつ同点でない場合

        if (dealerScore > playerScore) {// ディーラーがプレイヤーよりもスコアが高いなら、ディーラーの勝ち
            return 0;
        }

        if (playerScore > dealerScore) {// プレイヤーがディーラーよりもスコアが高いなら、プレイヤーの勝ち
            return 2;
        }

        // どちらもバーストせず、かつ同点の場合

        if (dealerScore == 21 && numOfDealerCards == 2) {// どちらも21点だが、ディーラーの手札が2枚なら、ディーラーの勝ち（プレイヤーの手札数は関係ない）
            return 0;
        }

        if (dealerScore == 21 && numOfPlayerCards == 2) {// どちらも21点だが、プレイヤーのみ手札が2枚なら、プレイヤーの勝ち
            return 2;
        }

        return 1;// 上のいずれでもなければ引き分け
    }

    public int finalJudge(double playerPoint) {
        if (playerPoint < 1) {
            return 0;
        }

        if (playerPoint >= 2000) {
            return 2;
        }

        return 1;
    }

}
