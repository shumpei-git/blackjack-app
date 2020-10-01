package shumpei.cardgame;

import shumpei.cardgame.blackjack.Blackjack;
import shumpei.cardgame.lot.Lot;

import java.util.Scanner;

public class Menu {
    public void start(){
        System.out.println("トランプで遊びましょう。");
        System.out.println("ブラックジャックで遊ぶなら0、おみくじをするなら1を入力してください。");
        int gameId = selectZeroOrOne();
        if (gameId == 0) {
            new Blackjack().start();
        }
        if (gameId == 1) {
            new Lot().start();
        }
        System.out.println("また遊んでくださいね。");
    }

    private int selectZeroOrOne() {
        while (true) {
            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()) {
                int num = in.nextInt();
                if (num == 0 || num == 1) {
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
