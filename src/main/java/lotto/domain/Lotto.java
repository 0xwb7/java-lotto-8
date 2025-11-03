package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

import java.util.List;

public class Lotto {

    private static final int LOTTO_SIZE = 6;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers
                .stream()
                .sorted()
                .toList();
    }

    public static void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new LottoException(ErrorMessage.LOTTO_SIZE_ERROR);
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int countMatch(WinningLotto winningLotto) {
        return (int) numbers
                .stream()
                .filter(winningLotto::contains)
                .count();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }
}
