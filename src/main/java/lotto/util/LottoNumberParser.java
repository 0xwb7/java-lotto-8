package lotto.util;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

public class LottoNumberParser {

    public static void checkNullInput(String input) {
        if (input == null || input.isBlank()) {
            throw new LottoException(ErrorMessage.NULL_INPUT_NOT_ALLOWED);
        }
    }
}
