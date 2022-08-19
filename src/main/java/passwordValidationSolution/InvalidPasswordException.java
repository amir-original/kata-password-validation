package passwordValidationSolution;

import static passwordValidationSolution.ErrorCode.OK;

public class InvalidPasswordException extends RuntimeException {

    private ErrorCode errorCode = OK;

    public InvalidPasswordException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode(){
        return errorCode;
    }

}
