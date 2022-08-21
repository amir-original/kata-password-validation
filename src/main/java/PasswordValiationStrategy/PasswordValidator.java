package PasswordValiationStrategy;

public class PasswordValidator {

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
