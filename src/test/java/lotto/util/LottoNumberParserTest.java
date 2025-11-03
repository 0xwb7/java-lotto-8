package lotto.util;

import lotto.exception.LottoException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberParserTest {

    @Test
    void 사용자_입력_공백_제거() {
        String input = "1, 2 , 3    , 4, 5, 6";

        List<Integer> parsed = LottoNumberParser.parseWinningNumbers(input);

        assertEquals(List.of(1, 2, 3, 4, 5, 6), parsed);
    }

    @Test
    void 공백_문자열_예외() {
        assertThrows(LottoException.class,
                () -> LottoNumberParser.parseWinningNumbers("  "));
    }

    @Test
    void 문자열_입력_예외() {
        assertThrows(LottoException.class,
                () -> LottoNumberParser.parseWinningNumbers("abc"));
    }

    @Test
    void 범위_초과_예외() {
        assertThrows(LottoException.class,
                () -> LottoNumberParser.parseAndValidate("0"));

        assertThrows(LottoException.class,
                () -> LottoNumberParser.parseAndValidate("46"));
    }

    @Test
    void 중복_예외() {
        assertThrows(LottoException.class,
                () -> LottoNumberParser.checkDuplicate(List.of(1, 2, 3, 4, 5, 5)));
    }
}
