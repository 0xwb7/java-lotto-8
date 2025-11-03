package lotto.service;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;
import lotto.util.LottoNumberParser;

public class LottoService {

    private static final int PRICE = 1000;

    public int validatePurchasePriceInput(String input) {
        try {
            LottoNumberParser.checkNullInput(input);
            int purchasePrice = Integer.parseInt(input);
            if (purchasePrice % PRICE != 0) {
                throw new LottoException(ErrorMessage.WRONG_PURCHASE_INPUT);
            }
            if (purchasePrice < PRICE) {
                throw new LottoException(ErrorMessage.MINIMUM_PURCHASE_PRICE);
            }
            return purchasePrice;
        } catch (NumberFormatException e) {
            throw new LottoException(ErrorMessage.IS_NOT_NUMERIC);
        }
    }

    public int purchaseAmount(int purchasePrice) {
        return purchasePrice / PRICE;
    }
}
