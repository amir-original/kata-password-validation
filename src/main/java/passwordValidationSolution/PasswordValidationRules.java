package passwordValidationSolution;

public class PasswordValidationRules {

    private final String password;
    private final int minRequiredLength;

    public PasswordValidationRules(String password, int minRequiredLength) {
        this.password = password;
        this.minRequiredLength = minRequiredLength;
    }

    public boolean containsAtLeastOneLowercaseChar() {
        return password.matches(".*[a-z].*");
    }

    public boolean containsAtLeastOneUppercaseChar() {
        return password.matches(".*[A-Z].*");
    }

    public  boolean containsAtLeastOneNumber() {
        return password.matches(".*[\\d].*");
    }

    public boolean containsAtLeastOneUnderscore() {
        return password.matches(".*[_].*");
    }

    public boolean hasTheMinRequirementLength() {
        return password.length() > minRequiredLength;
    }
}
