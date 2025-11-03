package lotto.exception;

public enum ErrorMessage {
    WRONG_PURCHASE_INPUT("[ERROR] 구입 금액은 1,000원 단위여야 합니다."),
    NULL_INPUT_NOT_ALLOWED("[ERROR] 입력값이 없습니다."),
    IS_NOT_NUMERIC("[ERROR] 숫자만 입력 가능합니다."),
    LOTTO_SIZE_ERROR("[ERROR] 로또 번호는 6개여야 합니다."),
    WINNING_NUMBER_DUPLICATED("[ERROR] 당첨 번호는 중복될 수 없습니다."),
    WRONG_WINNING_NUMBER_INPUT("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    BONUS_NOT_ALLOWED_DUPLICATE("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
