package shumpei.cardgame.lot;

import shumpei.cardgame.playingcard.Card;
import shumpei.cardgame.playingcard.CardStuck;

import java.util.List;
import java.util.Scanner;

public class Lot {
    public Lot() {}

    public void start() {
        System.out.println("【おみくじを開始します】");
        List<Card> cardStuck = new CardStuck().getCardList();
        System.out.println("1から52の間で好きな数字を選んでください。引いたカードの数字が大きいほど、今日はラッキーです。");
        int num = selectNum();
        System.out.println("引いたカードの数は" + cardStuck.get(num - 1).getNumber() + "でした。");
        System.out.println("【おみくじを終了します】");
    }

    private int selectNum() {
        while (true) {
            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()) {
                int num = in.nextInt();
                if (num >= 1 && num <= 52) {
                    return num;
                } else {
                    System.out.println("入力値が不正です。再入力してください。");
                }
            } else {
                System.out.println("入力値が不正です。再入力してください。");
            }
        }
    }
}
