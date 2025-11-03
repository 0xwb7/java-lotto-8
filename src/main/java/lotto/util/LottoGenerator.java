package lotto.util;

import lotto.domain.Lotto;

import java.util.List;

public interface LottoGenerator {
    Lotto generateLottoNumber();
    List<Lotto> generateLottoNumber(int amount);
}
