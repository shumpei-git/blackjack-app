package shumpei.cardgame.blackjack;

public class Blackjack {
    private Player player;
    private Player dealer;
    private GameMsg gameMsg;
    private CardStuck cardStuck;
    private Judge judge;

    public static Blackjack createBlackjack() {
        Player player = new Player("プレイヤー", 1000);
        Player dealer = new Player("ディーラー", 0);
        GameMsg gameMsg = new GameMsg();
        CardStuck cardStuck = new CardStuck();
        Judge judge = new Judge();
        return new Blackjack(player, dealer, gameMsg, cardStuck, judge);
    }

    private Blackjack(Player player, Player dealer, GameMsg gameMsg, CardStuck cardStuck, Judge judge) {
        this.player = player;
        this.dealer = dealer;
        this.gameMsg = gameMsg;
        this.cardStuck = cardStuck;
        this.judge = judge;
    }

    public void start(){
        int numOfGames = 0;

        gameMsg.start();

        while(true) {

            ++numOfGames;
            gameMsg.showNumOfGames(numOfGames);

            int betPoint = ready();

            makePlayerAct();

            makeDealerAct();

            showResult(betPoint);

            if (checkWhetherFinished()) {
                break;
            }

        }

        gameMsg.end();
    }
    
    private int ready() {
        //掛けポイントを入力
        gameMsg.urgePlayerToBet(player.getPoint());
        gameMsg.displayPoint(player.getPoint());
        int betPoint = Input.inputBetPoint();
        gameMsg.lineFeed();

        //プレイヤーに手札を２枚配る
        //ディーラーに手札を２枚配る
        gameMsg.dealOutCards();
        for (int i = 0; i < 2; i++) {
            player.draw(cardStuck);
            dealer.draw(cardStuck);
        }

        //プレイヤーのカードを表示
        gameMsg.showHand(player);
        //ディーラーのカードを表示（２枚目は画面上 *として表示して下さい）
        gameMsg.showFirstCard(dealer);

        gameMsg.lineFeed();
        return betPoint;
    }

    private void makePlayerAct() {
        //プレイヤーがカードを引くか選択（ストップするかバーストするかまで繰り返し）
        while(true) {
            gameMsg.hitOrStand();
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
    }

    private void makeDealerAct() {
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
    }

    private void showResult(int betPoint) {
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
    }

    private boolean checkWhetherFinished() {
        // 最終結果

        // finalJudgeResultの値は、次のように定まる
        // プレイヤーの負け：0
        // ゲームが続く（勝敗未決）：1
        // プレイヤーの勝ち：2
        int finalJudgeResult = judge.finalJudge(player.getPoint());

        // ゲームが終了する場合の処理
        if(finalJudgeResult == 0) {
            gameMsg.finalResult();
            gameMsg.loose(player.getPoint());
            return true;
        }
        if(finalJudgeResult == 2) {
            gameMsg.finalResult();
            gameMsg.win(player.getPoint());
            return true;
        }

        // ゲームが続く場合の処理（山札と手札の更新）
        cardStuck.reset();
        player.getHand().discardAllCards();
        dealer.getHand().discardAllCards();
        return false;
    }

}
