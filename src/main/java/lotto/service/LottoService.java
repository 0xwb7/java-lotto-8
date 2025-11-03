package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.dto.LottoResult;
import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;
import lotto.util.LottoNumberParser;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoService {

    private static final int PRICE = 1000;

    public int validatePurchasePriceInput(String input) {
        try {
            String trimmed = input.trim();
            LottoNumberParser.checkNullInput(trimmed);
            int purchasePrice = Integer.parseInt(trimmed);
            exceptionWrongPurchasePriceInput(purchasePrice);
            return purchasePrice;
        } catch (NumberFormatException e) {
            throw new LottoException(ErrorMessage.IS_NOT_NUMERIC);
        }
    }

    private void exceptionWrongPurchasePriceInput(int purchasePrice) {
        if (purchasePrice % PRICE != 0 || purchasePrice < PRICE) {
            throw new LottoException(ErrorMessage.WRONG_PURCHASE_INPUT);
        }
    }

    public int purchaseAmount(int purchasePrice) {
        return purchasePrice / PRICE;
    }

    public int validateBonusNumber(List<Integer> winningNumbers, String input) {
        int bonus = LottoNumberParser.parseAndValidate(input);
        if (winningNumbers.contains(bonus)) {
            throw new LottoException(ErrorMessage.BONUS_NOT_ALLOWED_DUPLICATE);
        }

        return bonus;
    }

    public LottoResult calculateResult(List<Lotto> lottos, WinningLotto winningLotto, int purchasedCount) {
        Map<Rank, Integer> counts = initRankMap();

        for (Lotto lotto : lottos) {
            int matchCount = lotto.countMatch(winningLotto);
            boolean bonusMatched = lotto.contains(winningLotto.getBonus());
            Rank rank = Rank.of(matchCount, bonusMatched);
            counts.put(rank, counts.get(rank) + 1);
        }

        long totalPrize = calcTotalPrize(counts);
        int spent = purchasedCount * PRICE;
        double profitRate = ((double) totalPrize / spent) * 100;

        return new LottoResult(counts, profitRate);
    }

    private Map<Rank, Integer> initRankMap() {
        Map<Rank, Integer> map = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            map.put(rank, 0);
        }
        return map;
    }

    private long calcTotalPrize(Map<Rank, Integer> counts) {
        long sum = 0;
        for (Rank rank : Rank.values()) {
            int count = counts.getOrDefault(rank, 0);
            sum += (long) rank.getPrize() * count;
        }
        return sum;
    }
}
