package passwordValidationSolution;

public class PasswordValidatorStrategy {

    private final String password;
    private final PasswordType type;

    public PasswordValidatorStrategy(String password, PasswordType type) {
        this.password = password;
        this.type = type;
    }

    public boolean isValid() {
        PasswordValidator passwordValidator;

        switch (type) {
            case VALIDATION1:
                passwordValidator = new PassValidatorMoreThan8Chars(password);
                break;
            case VALIDATION2:
                passwordValidator = new PassValidatorMoreThan6Chars(password);
                break;
            case VALIDATION3:
                passwordValidator = new PassValidatorMoreThan16Chars(password);
                break;
            case VALIDATION4:
                passwordValidator = new PassValidatorWithAWeakerRules(password);
                break;
            default:
                throw new IllegalStateException();
        }
        return passwordValidator.isValid();
    }
}
