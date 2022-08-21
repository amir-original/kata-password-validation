package PasswordValiationStrategy;

public class PasswordValidationRules {

    private final PasswordLength minRequiredLength;

    public PasswordValidationRules(PasswordLength minRequiredLength) {
        this.minRequiredLength = minRequiredLength;
    }

    public boolean containsAtLeastOneLowercaseChar(String password) {
        return password.matches(".*[a-z].*");
    }

    public boolean containsAtLeastOneUppercaseChar(String password) {
        return password.matches(".*[A-Z].*");
    }

    public  boolean containsAtLeastOneNumber(String password) {
        return password.matches(".*[\\d].*");
    }

    public boolean containsAtLeastOneUnderscore(String password) {
        return password.matches(".*[_].*");
    }

    public boolean hasTheMinRequirementLength(String password) {
        return password.length() > minRequiredLength.toInt();
    }
}
