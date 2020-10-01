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

    public void tieHand() {
        System.out.println("引き分けです。");
        System.out.println("ポイントに変動はありません。");
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
