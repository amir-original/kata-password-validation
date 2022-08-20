package passwordValidationSolution;

public class PassValidatorMoreThan16Chars extends PasswordValidator {

    public PassValidatorMoreThan16Chars(String password) {
        super(password,16);
    }

    public boolean isValid() {
        return matchesAllRequirements();
    }

    @Override
    protected boolean matchesAllRequirements() {
        return super.matchesAllRequirements() && rules.containsAtLeastOneUnderscore();
    }

}
