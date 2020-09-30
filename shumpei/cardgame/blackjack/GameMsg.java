package shumpei.cardgame.blackjack;

public class GameMsg {

    public void start() {
        System.out.println("【ブラックジャックを開始します】");
    }

    public void showNumOfGames(int numOfGames) {
        System.out.println("【第" + numOfGames + "回戦】");
    }

    public void urgePlayerToBet(double point) {
        int max = point < 100 ? (int) point : 100;
        System.out.println("1から" + max + "の間の整数値で、賭けポイントを決めてください。");
    }

    public void displayPoint(double point) {
        System.out.println("残りポイント：" + point);
    }

    public void dealOutCards() {
        System.out.println("手札を2枚ずつ配ります。");
    }

    public void showHand(Player player) {
        System.out.println(player.getName() + "の手札：");
        for(Card card: player.getHand().getCardList()) {
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
        String score = player.getHand().checkBust() ? "バースト" : String.valueOf(player.getHand().getScore());
        System.out.println(" スコア：" + score);

        System.out.println(" 役：" + player.getHand().getRank());

        System.out.println(" 倍率：" + player.getHand().getRate() + "倍");
    }

    public void showFirstCard(Player dealer) {
        System.out.println(dealer.getName() + "の手札：");

        Card firstCard = dealer.getHand().getCardList().get(0);
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

    public void hitOrStand() {
        System.out.println("ヒットするなら1、スタンドするなら0を入力してください。");
    }

    public void hit() {
        System.out.println("ヒットしました。");
    }

    public void stand() {
        System.out.println("スタンドしました。");
    }

    public void dealerAction() {
        System.out.println("スコアが17以上になるまで、ディーラーがヒットしました。");
    }

    public void looseHand(int betPoint) {
        System.out.println("プレイヤーの負けです。");
        System.out.println("賭けた" + betPoint + "ポイントが没収されました。");
    }

    public void tieHand() {
        System.out.println("引き分けです。");
        System.out.println("ポイントに変動はありません。");
    }

    public void winHand(double earnedPoint) {
        System.out.println("プレイヤーの勝ちです。");
        System.out.println(earnedPoint + "ポイント獲得しました。");
    }

    public void bust(String name) {
        System.out.println(name + "がバーストしました。");
    }

    public void result() {
        System.out.println("【結果発表】");
    }

    public void finalResult() {
        System.out.println("【最終結果】");
    }

    public void loose(double point) {
        System.out.println("残りポイントが" + point + "です。");
        System.out.println("もう賭けるポイントがありません。");
        System.out.println("プレイヤーの負けです。");
    }

    public void win(double point) {
        System.out.println("残りポイントは" + point + "です。");
        System.out.println("2000ポイントに達しました。");
        System.out.println("プレイヤーの勝ちです。");
    }

    public void end() {
        System.out.println("【ブラックジャックを終了します】");
    }

    public void lineFeed() { System.out.println();}
}
