package shumpei.cardgame.blackjack;

class Result {
    private int gameResult;
    private double rate;
//    public String playerHandRank;// プレイヤーの手札の役
//    public String dealerHandRank;// ディーラーの手札の役

    Result(int gameResult, double rate) {
        this.gameResult = gameResult;
        this.rate = rate;
//        this.playerHandRank = playerHandRank;
//        this.dealerHandRank = dealerHandRank;
    }

    int getGameResult() {
        return gameResult;
    }

    double getRate() {
        return rate;
    }
}
