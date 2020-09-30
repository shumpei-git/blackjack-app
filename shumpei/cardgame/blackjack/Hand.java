package shumpei.cardgame.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private List<Card> cardList;
    private int score;
    private String rank = "なし";
    private double rate = 1;

    public Hand() {
        this.cardList = new ArrayList<Card>();
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public int getScore() {
        return score;
    }

    public String getRank() {
        return this.rank;
    }

    public double getRate() {
        return this.rate;
    }

    public void updateScore() {
        List<Integer> cardNumbers = new ArrayList<Integer>();
        for (Card card: this.cardList) {
            cardNumbers.add(card.getNumber());
        }
        Collections.sort(cardNumbers);
        int numOfAce = cardNumbers.lastIndexOf(1) + 1; // 手札に含まれるエースの枚数

        this.score = 0; //scoreを一旦初期化
        // エース以外の手札を元に、点数を計算
        for (int i = cardNumbers.size() - 1; i >= numOfAce; i--) {
            this.score += cardNumbers.get(i) > 10 ? 10 : cardNumbers.get(i);// 11, 12, 13は10と見なす。
        }
        // エースを考慮して点数を計算
        if (numOfAce >= 1) {
            if (this.score + numOfAce - 1 <= 10) {
                this.score = this.score + 1 * (numOfAce - 1) + 11;
            } else {
                this.score = this.score + 1 * numOfAce;
            }
        }
    }

    public boolean checkBust() {
        return this.score > 21;
    }

    public void discardAllCards() {
        for (int i = this.cardList.size(); i > 0; i--) {
            this.cardList.remove(0);
        }
    }

    public void updateRankAndRate() {

        if (this.score == 21 && this.cardList.size() == 2 ) {
            // ジャックが含まれるか調査
            boolean jackFlg = false;
            for (Card card: this.cardList) {
                if (card.getNumber() == 11) {
                    jackFlg = true;
                    break;
                }
            }

            // スペードのエースが含まれるか調査
            boolean spadeFlg = false;
            for (Card card: this.cardList) {
                if (card.getNumber() == 1 && card.getSuit().equals("spade")) {
                    spadeFlg = true;
                    break;
                }
            }

            // rankとrate決定
            if (jackFlg) {
                if(spadeFlg) {
                    this.rank = "表ブラックジャック";
                    this.rate = 15;
                    return;
                }
                this.rank = "裏ブラックジャック";
                this.rate = 5;
                return;
            }
            this.rank = "ブラックジャック";
            this.rate = 2.5;
            return;
        }

        if (this.cardList.size() >= 7) {
            this.rank = "セブンカード";
            this.rate = 10;
            return;
        }

        if (this.cardList.size() >= 6) {
            this.rank = "シックスカード";
            this.rate = 5;
            return;
        }

        if (this.cardList.size() == 3) {
            boolean threeSevenFlg = true;
            for (Card card: this.cardList) {
                if (card.getNumber() != 7) {
                    threeSevenFlg = false;
                    break;
                }
            }
            if (threeSevenFlg) {
                this.rank = "スリーセブン";
                this.rate = 10;
                return;
            }
        }

        this.rank = "なし";
        this.rate = 1;
        return;
    }
}
