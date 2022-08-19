package passwordValidationSolution;

public class PasswordMoreThan8Characters extends PasswordValidator {


    public PasswordMoreThan8Characters(String password) {
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
