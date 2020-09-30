package shumpei.blackjack;

import java.util.List;
import java.util.Scanner;

public class Game {
    private Player player;
    private Player dealer;
    private GameMsg gameMsg;
    private CardStuck cardStuck;
    private Judge judge;

    public Game(Player player, Player dealer, GameMsg gameMsg, CardStuck cardStuck, Judge judge) {
        this.player = player;
        this.dealer = dealer;
        this.gameMsg = gameMsg;
        this.cardStuck = cardStuck;
        this.judge = judge;
    }

    public void start(){
        int numOfGames = 1;

        gameMsg.start();

        while(true) {

            gameMsg.showNumOfGames(numOfGames);

            //掛けポイントを入力
            gameMsg.urgePlayerToBet(player.getPoint());
            gameMsg.displayPoint(player.getPoint());
            // double betPoint = new Scanner(System.in).nextDouble();// TODO: 入力値チェック
            int betPoint = Input.inputBetPoint();
            gameMsg.lineFeed();

            //プレイヤーに手札を２枚配る
            //ディーラーに手札を２枚配る
            gameMsg.dealOutCards();
            dealOutCards();

            //プレイヤーのカードを表示
            gameMsg.showHand(player);
            //ディーラーのカードを表示（２枚目は画面上 *として表示して下さい）
            gameMsg.showFirstCard(dealer);

            gameMsg.lineFeed();

            //プレイヤーがカードを引くか選択（ストップするかバーストするかまで繰り返し）
            while(true) {
                gameMsg.hitOrStand();
                // int hitOrStand = new Scanner(System.in).nextInt();// TODO: 入力値チェック
                int hitOrStand = Input.selectHitOrStand();
                if (hitOrStand == 0) {
                    gameMsg.stand();
                    break;
                }
                player.draw(cardStuck);
                gameMsg.hit();
                gameMsg.showHand(player);
                // バースト判定
                if (player.getHand().checkBust()) {
                    gameMsg.bust(player.getName());
                    break;
                }
                gameMsg.lineFeed();
            }
            gameMsg.lineFeed();

            //ディーラーがカードを引く（手札を17以上にするまでカードを引く）
            while(true){
                // 手札を17以上にするまでカードを引く
                if (dealer.getHand().getScore() >= 17) {
                    break;
                }
                dealer.draw(cardStuck);
                // バースト判定
                if (dealer.getHand().checkBust()) {
                    break;
                }
            }
            gameMsg.dealerAction();
            gameMsg.lineFeed();

            //勝敗を表示する
            //結果のポイント
            gameMsg.result();
            gameMsg.showHand(player);
            gameMsg.showHand(dealer);
            int result = judge.handJudge(player.getHand(), dealer.getHand());
            if (result == 0) {
                player.setPoint(player.getPoint() - betPoint);
                gameMsg.looseHand(betPoint);
            } else if (result == 1) {
                gameMsg.tieHand();
            } else if (result == 2) {
                double earnedPoint = betPoint * player.getHand().getRate();
                gameMsg.winHand(earnedPoint);
                player.setPoint(player.getPoint() + earnedPoint);
            }

            gameMsg.lineFeed();

            // 最終結果

            // finalJudgeResultの値は、次のように定まる
            // プレイヤーの負け：0
            // ゲームが続く（勝敗未決）：1
            // プレイヤーの勝ち：2
            int finalJudgeResult = judge.finalJudge(player.getPoint());

            if (finalJudgeResult == 1) {
                // ゲームが続く場合の処理（山札と手札の更新）
                cardStuck.reset();
                player.getHand().discardAllCards();
                dealer.getHand().discardAllCards();
                ++numOfGames;
                continue;
            }

            gameMsg.finalResult();
            if(finalJudgeResult == 0) {
                gameMsg.loose(player.getPoint());
                break;
            }
            if(finalJudgeResult == 2) {
                gameMsg.win(player.getPoint());
                break;
            }

        }

        gameMsg.end();
    }

    private void dealOutCards() {
        for (int i = 0; i < 2; i++) {
            player.draw(cardStuck);
            dealer.draw(cardStuck);
        }
    }

}
