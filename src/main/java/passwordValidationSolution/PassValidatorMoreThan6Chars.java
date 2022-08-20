package passwordValidationSolution;

public class PassValidatorMoreThan6Chars extends PasswordValidator {

    public PassValidatorMoreThan6Chars(String password) {
        super(password,6);
    }

    public boolean isValid() {
        return matchesAllRequirements();
    }

    @Override
    protected boolean matchesAllRequirements() {
        return super.matchesAllRequirements() && rules.containsAtLeastOneNumber();
    }

}
