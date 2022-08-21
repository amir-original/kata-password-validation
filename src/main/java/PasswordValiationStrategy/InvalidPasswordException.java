package PasswordValiationStrategy;

import java.util.Set;

public class InvalidPasswordException extends RuntimeException {

    private final Set<ErrorCode> errorCodes;

    public InvalidPasswordException(Set<ErrorCode> errorCode) {
        this.errorCodes = errorCode;
    }

    public Set<ErrorCode> getErrors(){
        return errorCodes;
    }
}
