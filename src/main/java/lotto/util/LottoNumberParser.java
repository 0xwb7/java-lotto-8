package lotto.util;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class LottoNumberParser {

    private static final String SPLIT_DELIMITER = ",";
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;

    public static void checkNullInput(String input) {
        if (input == null || input.isBlank()) {
            throw new LottoException(ErrorMessage.NULL_INPUT_NOT_ALLOWED);
        }
    }

    public static void checkDuplicate(List<Integer> winningNumbers) {
        Set<Integer> duplicateNumbers = new HashSet<>(winningNumbers);

        if (winningNumbers.size() != duplicateNumbers.size()) {
            throw new LottoException(ErrorMessage.WINNING_NUMBER_DUPLICATED);
        }
    }

    public static List<Integer> parseWinningNumbers(String input) {
        return Stream.of(input.split(SPLIT_DELIMITER))
                .map(String::trim)
                .map(LottoNumberParser::parseAndValidate)
                .toList();
    }

    public static int parseAndValidate(String input) {
        checkNullInput(input);
        String trimmed = input.trim();
        exceptionIsNotNumeric(trimmed);
        int number = Integer.parseInt(trimmed);
        validateNumberRange(number);
        return number;
    }

    private static void exceptionIsNotNumeric(String input) {
        if (!input.chars().allMatch(Character::isDigit)) {
            throw new LottoException(ErrorMessage.IS_NOT_NUMERIC);
        }
    }

    public static void validateNumberRange(int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new LottoException(ErrorMessage.WRONG_WINNING_NUMBER_INPUT);
        }
    }
}
