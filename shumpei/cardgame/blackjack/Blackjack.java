package shumpei.cardgame.blackjack;

public class Blackjack {
    private GameMsg gameMsg;
    private double point = 1000;

    public static Blackjack createBlackjack() {
        GameMsg gameMsg = new GameMsg();
        return new Blackjack(gameMsg);
    }

    private Blackjack(GameMsg gameMsg) {
        this.gameMsg = gameMsg;
    }

    public void start(){
        int numOfGames = 0;

        gameMsg.start();

        while(true) {

            ++numOfGames;
            gameMsg.showNumOfGames(numOfGames);

            int betPoint = bet();

            updatePoint(betPoint, new MiniGame().play());

            if (checkWhetherFinished()) {
                break;
            }

        }

        gameMsg.end();
    }

    private int bet() {
        //掛けポイントを入力
        gameMsg.urgePlayerToBet(point);
        gameMsg.displayPoint(point);
        int min = 1;
        int max = (int) point < 100 ? (int) point : 100;
        int betPoint = Input.inputBetPoint(min, max);
        gameMsg.lineFeed();
        return betPoint;
    }

    private void updatePoint(int betPoint, Result miniGameResult) {
        if (miniGameResult.getGameResult() == 0) {
            point -= betPoint;
        } else if (miniGameResult.getGameResult() == 1) {
            gameMsg.tieHand();
        } else if (miniGameResult.getGameResult() == 2) {
            point += betPoint * miniGameResult.getRate();
        }
    }

    private boolean checkWhetherFinished() {
        // ゲームが終了する場合の処理
        if(point < 1) {// 負け
            gameMsg.finalResult();
            gameMsg.loose(point);
            return true;
        }
        if(point >= 2000) {// 勝ち
            gameMsg.finalResult();
            gameMsg.win(point);
            return true;
        }

        //勝負継続
        return false;
    }

}
