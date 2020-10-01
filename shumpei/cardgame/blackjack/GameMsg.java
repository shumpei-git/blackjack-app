package shumpei.cardgame.blackjack;

class GameMsg {

    void start() {
        System.out.println("【ブラックジャックを開始します】");
    }

    void showNumOfGames(int numOfGames) {
        System.out.println("【第" + numOfGames + "回戦】");
    }

    void urgePlayerToBet(double point) {
        int max = point < 100 ? (int) point : 100;
        System.out.println("1から" + max + "の間の整数値で、賭けポイントを決めてください。");
    }

    void displayPoint(double point) {
        System.out.println("残りポイント：" + point);
    }

    void tieHand() {
        System.out.println("引き分けです。");
        System.out.println("ポイントに変動はありません。");
    }

    void finalResult() {
        System.out.println("【最終結果】");
    }

    void loose(double point) {
        System.out.println("残りポイントが" + point + "です。");
        System.out.println("もう賭けるポイントがありません。");
        System.out.println("プレイヤーの負けです。");
    }

    void win(double point) {
        System.out.println("残りポイントは" + point + "です。");
        System.out.println("2000ポイントに達しました。");
        System.out.println("プレイヤーの勝ちです。");
    }

    void end() {
        System.out.println("【ブラックジャックを終了します】");
    }

    void lineFeed() { System.out.println();}
}
