package lotto.exception;

public enum ErrorMessage {
    WRONG_PURCHASE_INPUT("[ERROR] 구입 금액은 1,000원 단위여야 합니다."),
    MINIMUM_PURCHASE_PRICE("[ERROR] 1개 이상은 구매해야 합니다."),
    NULL_INPUT_NOT_ALLOWED("[ERROR] 입력값이 없습니다."),
    IS_NOT_NUMERIC("[ERROR] 숫자만 입력 가능합니다.");



    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
