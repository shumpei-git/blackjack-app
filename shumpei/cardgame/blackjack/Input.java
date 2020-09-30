package shumpei.cardgame.blackjack;

import java.util.Scanner;

public class Input {
    /**
     * 賭けポイント入力メソッド
     * @return 賭けポイント（1<=賭けポイント<=100の整数）
     */
    public static int inputBetPoint() {
        while (true) {
            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()) {
                int betPoint = in.nextInt();
                if (1 <= betPoint && betPoint <= 100) {
                    return betPoint;
                } else {
                    System.out.println("入力値が不正です。再入力してください。");
                }
            } else {
                System.out.println("入力値が不正です。再入力してください。");
            }
        }
    }

    /**
     * ヒットorスタンド選択メソッド
     * @return 0 or 1（スタンドなら0、ヒットなら1）
     */
    public static int selectHitOrStand() {
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
