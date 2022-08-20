package passwordValidationSolution;

public class PassValidatorMoreThan8Chars extends PasswordValidator {


    public PassValidatorMoreThan8Chars(String password) {
        super(password,8);
    }

    public boolean isValid() {
        return matchesAllRequirements();
    }

    @Override
    protected boolean matchesAllRequirements() {
        return super.matchesAllRequirements() &&
                rules.containsAtLeastOneNumber() &&
                rules.containsAtLeastOneUnderscore();
    }

}
