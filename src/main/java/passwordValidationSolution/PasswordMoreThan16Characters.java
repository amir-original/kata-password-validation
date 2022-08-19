package passwordValidationSolution;

public class PasswordMoreThan16Characters extends PasswordValidator {

    public PasswordMoreThan16Characters(String password) {
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
