package shumpei.cardgame.blackjack;

class Result {
    private int gameResult;
    private double rate;

    Result(int gameResult, double rate) {
        this.gameResult = gameResult;
        this.rate = rate;
    }

    int getGameResult() {
        return gameResult;
    }

    double getRate() {
        return rate;
    }
}
