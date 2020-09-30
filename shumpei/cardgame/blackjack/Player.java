package shumpei.cardgame.blackjack;

public class Player {
    private String name;
    private double point;
    private Hand hand;

    public Player(String name, int point){
        this.name = name;
        this.point = point;
        this.hand = new Hand();
    }

    public String getName(){
        return name;
    }

    public double getPoint() {
        return point;
    }

    public Hand getHand() {
        return hand;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public void draw(CardStuck cardStuck) {
        Card newCard = cardStuck.getCardList().get(0);
        hand.getCardList().add(newCard);
        hand.updateScore();
        hand.updateRankAndRate();
        cardStuck.getCardList().remove(0);
    }

}
