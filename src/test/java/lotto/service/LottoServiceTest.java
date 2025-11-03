package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.dto.LottoResult;
import lotto.exception.LottoException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoServiceTest {

    LottoService lottoService = new LottoService();

    @Test
    void 구입금액_검증() {
        assertEquals(5000, lottoService.validatePurchasePriceInput("5000"));
        assertEquals(8000, lottoService.validatePurchasePriceInput("  8000 "));
    }

    @Test
    void 구입금액_문자열_예외() {
        assertThrows(LottoException.class,
                () -> lottoService.validatePurchasePriceInput("5,000"));

        assertThrows(LottoException.class,
                () -> lottoService.validatePurchasePriceInput("abc123"));
    }

    @Test
    void 구입금액_단위_불일치_예외() {
        assertThrows(LottoException.class,
                () -> lottoService.validatePurchasePriceInput("5500"));
    }

    @Test
    void 구입금액_최소금액_미달_예외() {
        assertThrows(LottoException.class,
                () -> lottoService.validatePurchasePriceInput("0"));
        assertThrows(LottoException.class,
                () -> lottoService.validatePurchasePriceInput("900"));
    }

    @Test
    void 보너스_번호와_당첨번호가_중복될_경우_예외() {
        assertThrows(LottoException.class,
                () -> lottoService.validateBonusNumber(
                        List.of(1, 2, 3, 4, 5, 6), "6")
        );
    }

    @Test
    void 당첨_결과_통합테스트() {
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        Lotto first  = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto second = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto third  = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        Lotto fourth = new Lotto(List.of(1, 2, 3, 4, 9, 10));
        Lotto fifth  = new Lotto(List.of(1, 2, 3, 11, 12, 13));
        Lotto miss   = new Lotto(List.of(1, 20, 21, 22, 23, 24));

        List<Lotto> lottos = List.of(first, second, third, fourth, fifth, miss);
        int purchaseCount = lottos.size();

        LottoResult result = lottoService.calculateResult(lottos, winning, purchaseCount);

        Map<Rank, Integer> counts = result.getCounts();
        assertEquals(1, counts.get(Rank.FIRST));
        assertEquals(1, counts.get(Rank.SECOND));
        assertEquals(1, counts.get(Rank.THIRD));
        assertEquals(1, counts.get(Rank.FOURTH));
        assertEquals(1, counts.get(Rank.FIFTH));
        assertEquals(1, counts.get(Rank.MISS));

        long totalPrize = 2_000_000_000L + 30_000_000L + 1_500_000L + 50_000L + 5_000L;
        double expectedROI = ((double) totalPrize / (purchaseCount * 1000)) * 100.0;

        assertEquals(expectedROI, result.getProfitRate());
    }


}
