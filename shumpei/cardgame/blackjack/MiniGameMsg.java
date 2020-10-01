package shumpei.cardgame.blackjack;

class MiniGameMsg {
    public void dealOutCards() {
        System.out.println("手札を2枚ずつ配ります。");
    }

    public void hitOrStand() {
        System.out.println("ヒットするなら1、スタンドするなら0を入力してください。");
    }

    public void stand() {
        System.out.println("スタンドしました。");
    }

    public void hit() {
        System.out.println("ヒットしました。");
    }

    public void bust() {
        System.out.println("プレイヤーがバーストしました。");
    }

    public void showHand(Hand playerHand) {
        System.out.println("プレイヤーの手札：");
        for(Card card: playerHand.getCardList()) {
            // 絵札の場合に数値ではなく名称を表示するための処理
            String number;
            switch (card.getNumber()) {
                case 1:
                    number = "ACE";
                    break;
                case 11:
                    number = "JACK";
                    break;
                case 12:
                    number = "QUEEN";
                    break;
                case 13:
                    number = "KING";
                    break;
                default:
                    number = String.valueOf(card.getNumber());
            }
            System.out.println(card.getSuit() + " " + number);
        }

        // バーストしている場合に、数値ではなく「バースト」と表示するための処理
        String score = playerHand.checkBust() ? "バースト" : String.valueOf(playerHand.getScore());
        System.out.println(" スコア：" + score);

        System.out.println(" 役：" + playerHand.getRank());

        System.out.println(" 倍率：" + playerHand.getRate() + "倍");
    }

    public void showFirstCard(Hand dealerHand) {
        System.out.println("ディーラーの手札：");

        Card firstCard = dealerHand.getCardList().get(0);
        // 絵札の場合に数値ではなく名称を表示するための処理
        String number;
        switch (firstCard.getNumber()) {
            case 1:
                number = "ACE";
                break;
            case 11:
                number = "JACK";
                break;
            case 12:
                number = "QUEEN";
                break;
            case 13:
                number = "KING";
                break;
            default:
                number = String.valueOf(firstCard.getNumber());
        }
        System.out.println(firstCard.getSuit() + " " + number);
        System.out.println("*");
    }

    public void dealerAction() {
        System.out.println("スコアが17以上になるまで、ディーラーがヒットしました。");
    }

    public void result() {
        System.out.println("【結果発表】");
    }

    public void loose() {
        System.out.println("プレイヤーの負けです。");
    }

    public void tie() {
        System.out.println("引き分けです。");
    }

    public void win() {
        System.out.println("プレイヤーの勝ちです。");
    }

    public void lineFeed() { System.out.println();}
}
