package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.dto.LottoResult;
import lotto.exception.LottoException;
import lotto.service.LottoService;
import lotto.util.LottoNumberGenerator;
import lotto.util.LottoNumberParser;
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

        OutputView.winningNumberOutput();
        List<Integer> winningNumbers = winningNumberRead();

        OutputView.bonusNumberOutput();
        int bonus = bonusNumberRead(winningNumbers);

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonus);
        LottoResult result = lottoService.calculateResult(lottos, winningLotto, amount);
        OutputView.printWinningStatistics(result);
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

    private List<Integer> winningNumberRead() {
        while (true) {
            try {
                String input = InputView.winningNumberInput();
                List<Integer> winningNumbers = LottoNumberParser.parseWinningNumbers(input);
                Lotto.validate(winningNumbers);
                LottoNumberParser.checkDuplicate(winningNumbers);
                return winningNumbers;
            } catch (LottoException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private int bonusNumberRead(List<Integer> winningNumbers) {
        while (true) {
            try {
                String input = InputView.bonusNumberInput();
                return lottoService.validateBonusNumber(winningNumbers, input);
            } catch (LottoException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
