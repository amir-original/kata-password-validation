package passwordValidationSolution;

import static passwordValidationSolution.ErrorCode.MISSING_UNDERSCORE;

public class PassValidatorMoreThan16Chars extends PasswordValidator {

    public PassValidatorMoreThan16Chars(String password) {
        super(password,16);
    }

    public boolean isValid() {
        if(!rules.containsAtLeastOneUnderscore()) errorCodes.add(MISSING_UNDERSCORE);

        return hasMatchAllValidationRules();
    }


}
