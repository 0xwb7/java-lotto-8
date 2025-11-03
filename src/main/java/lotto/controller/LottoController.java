package lotto.controller;

import lotto.domain.Lotto;
import lotto.exception.LottoException;
import lotto.service.LottoService;
import lotto.util.LottoNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private final LottoService lottoService = new LottoService();
    private final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

    public void run() {
        OutputView.purchasePriceOutput();
        int amount = lottoService.purchaseAmount(purchasePriceRead());

        List<Lotto> lottos = lottoNumberGenerator.generateLottoNumber(amount);
        OutputView.printLotto(lottos);
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
