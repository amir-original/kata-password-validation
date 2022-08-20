package passwordValidationSolution;

import static passwordValidationSolution.ErrorCode.MISSING_NUMBER;

public class PassValidatorMoreThan6Chars extends PasswordValidator {

    public PassValidatorMoreThan6Chars(String password) {
        super(password,6);
    }

    public boolean isValid() {
        if(!rules.containsAtLeastOneNumber()) errorCodes.add(MISSING_NUMBER);

        return hasMatchAllValidationRules();
    }

}
