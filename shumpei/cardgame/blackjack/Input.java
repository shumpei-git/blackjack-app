package shumpei.cardgame.blackjack;

import java.util.Scanner;

public class Input {
    /**
     * 賭けポイント入力メソッド
     * @return 賭けポイント（1<=賭けポイント<=100の整数）
     */
    public static int inputBetPoint() {
        int betPoint;
        Scanner in = new Scanner(System.in);
        while (true) {
            if (in.hasNextInt()) {
                betPoint = in.nextInt();
                if (1 <= betPoint && betPoint <= 100) {
                    return betPoint;
                } else {
                    System.out.println("入力値が不正です。再入力してください。");
                    in.next();
                }
            } else {
                System.out.println("入力値が不正です。再入力してください。");
                in.next();
            }
        }
    }

    /**
     * ヒットorスタンド選択メソッド
     * @return 0 or 1（スタンドなら0、ヒットなら1）
     */
    public static int selectHitOrStand() {
        int num;
        Scanner in = new Scanner(System.in);
        while (true) {
            if (in.hasNextInt()) {
                num = in.nextInt();
                if (num == 0 || num == 1) {
                    return num;
                } else {
                    System.out.println("入力値が不正です。再入力してください。");
                    in.next();
                }
            } else {
                System.out.println("入力値が不正です。再入力してください。");
                in.next();
            }
        }
    }

}
