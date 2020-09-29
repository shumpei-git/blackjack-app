package shumpei.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class CardStuck {
    private List<Card> cardList;

    public List<Card> getCardList(){
        return this.cardList;
    }

    public CardStuck(){
        this.cardList = new ArrayList<Card>();
        createCardStuck();
        shuffle();
    }

    public void createCardStuck() {
        String[] suits = {"spade", "club", "heart", "diamond"};
        for (int i = 1; i <= 13; i++){
            for (int j = 0; j < 4; j++) {
                Card card = new Card(suits[j], i);
                this.cardList.add(card);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(this.cardList);
    }

    public void reset() {
        this.cardList = null;
        this.cardList = new ArrayList<Card>();
        createCardStuck();
        shuffle();
    }
}
