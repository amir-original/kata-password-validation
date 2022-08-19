package passwordValidationSolution;

import static passwordValidationSolution.ErrorCode.*;

public class PasswordValidationRules {

    private final String password;
    private final int minRequiredLength;

    public PasswordValidationRules(String password, int minRequiredLength) {
        this.password = password;
        this.minRequiredLength = minRequiredLength;
    }

    public boolean containsAtLeastOneLowercaseChar() {
        if (password.matches(".*[a-z].*")) return true;

        throw new InvalidPasswordException(MISSING_LOWERCASE);
    }

    public boolean containsAtLeastOneUppercaseChar() {
        if (password.matches(".*[A-Z].*")) return true;

        throw new InvalidPasswordException(MISSING_UPPERCASE);
    }

    public  boolean containsAtLeastOneNumber() {
        return password.matches(".*[\\d].*");
    }

    public boolean containsAtLeastOneUnderscore() {
        if(password.matches(".*[_].*")) return true;

        throw new InvalidPasswordException(MISSING_UNDERSCORE);
    }

    public boolean hasTheMinRequirementLength() {
        if (password.length() > minRequiredLength) return true;

        throw new InvalidPasswordException(INVALID_PASSWORD_LENGTH);
    }
}
