package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.dto.LottoResult;

import java.util.List;

public class OutputView {

    private static final String PURCHASE_PRICE_INPUT_MESSAGE = "구입금액을 입력해주세요.";
    private static final String SHOW_PURCHASE_AMOUNT = "%d개를 구매했습니다.";
    private static final String WINNING_NUMBER_INPUT_MESSAGE = "당첨 번호를 입력해주세요.";
    private static final String BONUS_NUMBER_INPUT_MESSAGE = "보너스 번호를 입력해주세요.";
    private static final String WIN_STATISTICS = "당첨 통계";
    private static final String HYPHEN = "---";
    private static final String FIRST_WINNING = "6개 일치 (2,000,000,000원)";
    private static final String SECOND_WINNING = "5개 일치, 보너스 볼 일치 (30,000,000원)";
    private static final String THIRD_WINNING = "5개 일치 (1,500,000원)";
    private static final String FOURTH_WINNING = "4개 일치 (50,000원)";
    private static final String FIFTH_WINNING = "3개 일치 (5,000원)";
    private static final String TOTAL_ROI = "총 수익률은 %.1f%%입니다.";
    private static final String RANK_AND_COUNT = "%s - %d개%n";
    private static final String LINE_BREAK = "\n";
    public static void printError(String message) {
        System.out.println(message);
    }

    public static void purchasePriceOutput() {
        System.out.println(PURCHASE_PRICE_INPUT_MESSAGE);
    }

    public static void showPurchaseAmountOutput(int amount) {
        System.out.printf(LINE_BREAK + SHOW_PURCHASE_AMOUNT + LINE_BREAK, amount);
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
        System.out.println(LINE_BREAK + BONUS_NUMBER_INPUT_MESSAGE);
    }

    public static void printWinningStatistics(LottoResult result) {
        System.out.println(LINE_BREAK + WIN_STATISTICS);
        System.out.println(HYPHEN);
        for (Rank rank : Rank.printOrder()) {
            System.out.printf(RANK_AND_COUNT,
                    toMessage(rank),
                    result.getCounts().getOrDefault(rank, 0));
        }
        System.out.printf(TOTAL_ROI, result.getProfitRate());
    }

    private static String toMessage(Rank rank) {
        if (rank == Rank.FIRST) {
            return FIRST_WINNING;
        }
        if (rank == Rank.SECOND) {
            return SECOND_WINNING;
        }
        if (rank == Rank.THIRD) {
            return THIRD_WINNING;
        }
        if (rank == Rank.FOURTH) {
            return FOURTH_WINNING;
        }
        if (rank == Rank.FIFTH) {
            return FIFTH_WINNING;
        }
        return "";
    }
}
