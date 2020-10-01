package shumpei.cardgame.blackjack;

public class Blackjack {
    private GameMsg gameMsg;
    private Judge judge;

    private double point = 1000;

    public static Blackjack createBlackjack() {
        GameMsg gameMsg = new GameMsg();
        Judge judge = new Judge();
        return new Blackjack(gameMsg, judge);
    }

    private Blackjack(GameMsg gameMsg, Judge judge) {
        this.gameMsg = gameMsg;
        this.judge = judge;
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
        // 最終結果

        // finalJudgeResultの値は、次のように定まる
        // プレイヤーの負け：0
        // ゲームが続く（勝敗未決）：1
        // プレイヤーの勝ち：2
        int finalJudgeResult = judge.finalJudge(point);

        // ゲームが終了する場合の処理
        if(finalJudgeResult == 0) {
            gameMsg.finalResult();
            gameMsg.loose(point);
            return true;
        }
        if(finalJudgeResult == 2) {
            gameMsg.finalResult();
            gameMsg.win(point);
            return true;
        }
        return false;
    }

}
