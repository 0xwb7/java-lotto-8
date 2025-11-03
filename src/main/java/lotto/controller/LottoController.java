package lotto.controller;

import lotto.exception.LottoException;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final LottoService lottoService = new  LottoService();

    public void run() {
        OutputView.purchasePriceOutput();
        int amount = lottoService.purchaseAmount(purchasePriceRead());
    }

    private int purchasePriceRead() {
        while (true) {
            try {
                String input = InputView.purchasePriceInput();
                return lottoService.validatePurchasePriceInput(input);
            } catch (LottoException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
