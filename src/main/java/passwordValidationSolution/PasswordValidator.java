package passwordValidationSolution;

import java.util.*;

import static passwordValidationSolution.ErrorCode.*;

public abstract class PasswordValidator {

    protected PasswordValidationRules rules;
    protected Set<ErrorCode> errorCodes = new LinkedHashSet<>();

    public PasswordValidator(String password, int minRequiredLength) {
        rules = new PasswordValidationRules(password, minRequiredLength);
    }

    public abstract boolean isValid();

    protected boolean hasMatchAllValidationRules() {
        if (hasMatchWithDefaultValidationRules()) return true;

        throw new InvalidPasswordException(errorCodes);
    }

    protected boolean hasMatchWithDefaultValidationRules() {
        if (!rules.hasTheMinRequirementLength()) errorCodes.add(INVALID_PASSWORD_LENGTH);
        if (!rules.containsAtLeastOneLowercaseChar()) errorCodes.add(MISSING_LOWERCASE);
        if (!rules.containsAtLeastOneUppercaseChar()) errorCodes.add(MISSING_UPPERCASE);

        return errorCodes.isEmpty();
    }
}
