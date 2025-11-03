package lotto.service;

import lotto.domain.Lotto;
import lotto.exception.LottoException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

}
