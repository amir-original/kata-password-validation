package passwordValidationSolution;

import static passwordValidationSolution.ErrorCode.*;

public class PassValidatorMoreThan8Chars extends PasswordValidator {

    public PassValidatorMoreThan8Chars(String password) {
        super(password, 8);
    }

    public boolean isValid() {
        if (!rules.containsAtLeastOneNumber()) errorCodes.add(MISSING_NUMBER);
        if (!rules.containsAtLeastOneUnderscore()) errorCodes.add(MISSING_UNDERSCORE);

        return hasMatchAllValidationRules();
    }

}
