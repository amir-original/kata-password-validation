package passwordValidationSolution;

public class PasswordMoreThan6Characters extends PasswordValidator {

    public PasswordMoreThan6Characters(String password) {
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
