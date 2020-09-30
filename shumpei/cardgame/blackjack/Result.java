package shumpei.cardgame.blackjack;

class Result {
    public int gameResult;
    public String rank;// 手札の役

    Result(int gameResult, String rank) {
        this.gameResult = gameResult;
        this.rank = rank;
    }
}
