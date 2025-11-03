package lotto.view;

import lotto.domain.Lotto;

import java.util.List;

public class OutputView {

    private static final String PURCHASE_PRICE_INPUT_MESSAGE = "구입금액을 입력해주세요.";
    private static final String WINNING_NUMBER_INPUT_MESSAGE = "당첨 번호를 입력해주세요.";
    private static final String BONUS_NUMBER_INPUT_MESSAGE = "보너스 번호를 입력해주세요.";

    public static void printError(String message) {
        System.out.println(message);
    }

    public static void purchasePriceOutput() {
        System.out.println(PURCHASE_PRICE_INPUT_MESSAGE);
    }

    public static void printLotto(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
        System.out.println();
    }

    public static void winningNumberOutput() {
        System.out.println(WINNING_NUMBER_INPUT_MESSAGE);
    }

    public static void bonusNumberOutput() {
        System.out.println("\n" + BONUS_NUMBER_INPUT_MESSAGE);
    }
}
