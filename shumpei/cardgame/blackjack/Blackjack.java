package shumpei.cardgame.blackjack;

import java.util.Scanner;

public class Blackjack {
    private GameMsg gameMsg = new GameMsg();
    private double point;

    public static Blackjack createNormalModeBlackjack() {
        return new Blackjack(1000);
    }

    public static Blackjack createHardModeBlackjack() {
        return new Blackjack(500);
    }

    private Blackjack(int point) {
        this.point = point;
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
        int betPoint = inputBetPoint(min, max);
        gameMsg.lineFeed();
        return betPoint;
    }

    private int inputBetPoint(int min, int max) {
        while (true) {
            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()) {
                int betPoint = in.nextInt();
                if (1 <= betPoint && betPoint <= max) {
                    return betPoint;
                } else {
                    System.out.println("入力値が不正です。再入力してください。");
                }
            } else {
                System.out.println("入力値が不正です。再入力してください。");
            }
        }
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
