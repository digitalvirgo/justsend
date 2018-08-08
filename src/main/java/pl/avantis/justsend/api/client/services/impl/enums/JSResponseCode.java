package pl.avantis.justsend.api.client.services.impl.enums;

public enum JSResponseCode {

    INVALID_VALUE("Invalid value of {0} parameter"),
    INVALID_FORMAT("Invalid format of {0} parameter"),
    INVALID_LENGTH("Invalid length of {0} parameter"),
    INVALID_USER_STATUS(104, "Invalid user status"),
    NO_SUBUSER_FOUND(105, "No subuser found"),
    NO_SUFFICIENT_POINTS(106, "Master/Slave does not have sufficient points to proceed operation"),
    NO_USER_FOUND(106, "No user found"),
    FORBIDDEN_VALUE("Forbidden value of {0} parameter"),
    DISALLOWED_CHARACTERS("Value of {0} contains disallowed characters"),
    NOT_FUNDS("Not sufficient funds in your account"),
    LIMIT_EXCEEDED("Limit exceeded for {0}"),
    NUMBER_ON_BLACK_LIST("Number {0} is on black list"),
    NO_AUTHORIZATION_BULK("No user authorization to send {0} bulk variant"),
    INTERNAL_ERROR(100, "Internal system error"),
    NO_AUTHENTICATION(101, "No user authentication"),
    NO_AUTHORIZATION(102, "No user authorization"),
    NOT_FOUND(103, "Not found"),
    OK(0, "Successful"),
    NO_ACCESS(102, "No access"),
    NO_NOTIFICATION_INFO_FOUND(103, "Not found notification info"),
    USER_READED_NOTIFICATION_INFO(105, "User readed notification info"),
    WRONG_CREDENTIALS(105, "Wrong credentials"),
    INVALID_BULK_STATE(107, "Bulk is accepted"),
    ILLEGAL_CREATE_PREFIX(109, "Incorrect prefix parameters"),
    NOT_FOUND_CYCLICAL_PAYMENT(103, "Cyclical payments not found"),
    NOT_FOUND_USER_CARD(103, "User card not found"),
    INVALID_NOTIFICATION(103, "Invalid notification"),
    NOT_FOUND_HISTORICAL_TRANSACTION(103, "Not found historical transaction");

    private String errorMessage;
    private Integer errorId;

    JSResponseCode(final String errorDescription) {
        this.errorMessage = errorDescription;
    }

    JSResponseCode(final Integer errorId, final String errorDescription) {
        this.errorMessage = errorDescription;
        this.errorId = errorId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorId() {
        return errorId;
    }

    public void setErrorId(final Integer errorId) {
        this.errorId = errorId;
    }
}
