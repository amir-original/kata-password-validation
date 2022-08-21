package PasswordValiationStrategy;

import java.util.LinkedHashSet;
import java.util.Set;

import static passwordValidationSolution.ErrorCode.*;

public class PasswordValidator {

    protected Set<ErrorCode> errorCodes = new LinkedHashSet<>();
    private final String password;
    private final PasswordValidatorStrategy passwordValidator;

    public PasswordValidator(String password, PasswordValidatorStrategy passwordValidator) {
        this.password = password;
        this.passwordValidator = passwordValidator;
    }

    public boolean isValid(){
        return passwordValidator.isValid(password);
    }




}
