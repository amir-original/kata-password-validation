package passwordValidationSolution;

public class PassValidatorMoreThan8Chars extends PasswordValidator {


    public PassValidatorMoreThan8Chars(String password) {
        super(password,8);
    }

    public boolean isValid() {
        return hasMatchWithAllValidationRules();
    }

    @Override
    protected boolean hasMatchWithAllValidationRules() {
        return super.hasMatchWithAllValidationRules() &&
                rules.containsAtLeastOneNumber() &&
                rules.containsAtLeastOneUnderscore();
    }

}
