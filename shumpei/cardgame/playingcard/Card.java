package shumpei.cardgame.playingcard;

public class Card {
    private String suit;
    private int number;

    public Card(String suit, int number) {
        setSuit(suit);
        setNumber(number);
    }

    public String getSuit() {
        return this.suit;
    }

    public int getNumber() {
        return this.number;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
