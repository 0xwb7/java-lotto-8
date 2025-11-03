package lotto.dto;

import lotto.domain.Rank;

import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> counts;
    private final double profitRate;

    public LottoResult(Map<Rank, Integer> counts, double profitRate) {
        this.counts = counts;
        this.profitRate = profitRate;
    }

    public Map<Rank, Integer> getCounts() {
        return counts;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
