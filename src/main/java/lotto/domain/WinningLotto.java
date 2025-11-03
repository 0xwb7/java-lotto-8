package lotto.domain;

import java.util.List;

public class WinningLotto {

    private final List<Integer> winningNumbers;
    private final int bonus;

    public WinningLotto(List<Integer> winningNumbers, int bonus) {
        this.winningNumbers = winningNumbers
                .stream()
                .sorted()
                .toList();
        this.bonus = bonus;
    }

    public boolean contains(int number) {
        return winningNumbers.contains(number);
    }

    public int getBonus() {
        return bonus;
    }
}
