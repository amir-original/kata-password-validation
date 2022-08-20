package passwordValidationSolution;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static passwordValidationSolution.ErrorCode.*;

public class PasswordValidatorShould {

    @Test
    void valid_password_matches_all_requirements() {
        assertThat(isValidPassword("_abcdAf1998", PasswordType.VALIDATION1)).isTrue();
        assertThat(isValidPassword("gfhiAbc_1919", PasswordType.VALIDATION1)).isTrue();
        assertThat(isValidPassword("oipWe1201_", PasswordType.VALIDATION1)).isTrue();
        assertThat(isValidPassword("oiuyAwer145789_dj", PasswordType.VALIDATION1)).isTrue();

        assertThat(isValidPassword("abcDe78", PasswordType.VALIDATION2)).isTrue();
        assertThat(isValidPassword("@wsfsDe78", PasswordType.VALIDATION2)).isTrue();
        assertThat(isValidPassword("_Aabcdefghij12345678", PasswordType.VALIDATION3)).isTrue();
    }

    @Test
    void invalid_password_misses_a_number() {
        try {
            isValidPassword("AbCdEfG_hIj", PasswordType.VALIDATION1);
            isValidPassword("_aSbcdefghij", PasswordType.VALIDATION2);
            isValidPassword("abcdEf____", PasswordType.VALIDATION2);
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(ErrorCode.MISSING_NUMBER);
        }
    }

    @Test
    void invalid_password_missed_a_lowercase() {
        try {
            isValidPassword("ABCJ1245_", PasswordType.VALIDATION2);
            isValidPassword("ABCDEFGHIJ1245_", PasswordType.VALIDATION1);
            isValidPassword("ABCDEFGHIJ12447sfe__5_", PasswordType.VALIDATION3);
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(ErrorCode.MISSING_LOWERCASE);
        }

    }

    @Test
    void invalid_password_misses_an_uppercase_char() {
        try {
            isValidPassword("__abcde56", PasswordType.VALIDATION2);
            isValidPassword("__abcdefghij123456", PasswordType.VALIDATION1);
            isValidPassword("cvscdefghijsr556_____", PasswordType.VALIDATION3);
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(ErrorCode.MISSING_UPPERCASE);
        }

    }

    @Test
    void invalid_password_misses_an_underscore() {
        try {
            isValidPassword("abcdeAVfghij123456", PasswordType.VALIDATION1);
            isValidPassword("7fytsstghASD123TGD6", PasswordType.VALIDATION3);
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(ErrorCode.MISSING_UNDERSCORE);
        }
    }

    @Test
    void invalid_password_is_shorter_than_required_length() {
        try {
            isValidPassword("_Aab8", PasswordType.VALIDATION2);
            isValidPassword("abcD7_", PasswordType.VALIDATION1);
            isValidPassword("Ab_cDeUYR7", PasswordType.VALIDATION3);
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(INVALID_PASSWORD_LENGTH);
        }
    }

    @Test
    void return_true_if_only_misses_a_rule_of_all_conditions_in_new_password_validator() {
        assertThat(new PasswordValidatorFactory("Acabcd124537", PasswordType.VALIDATION4).getInstance().isValid()).isTrue();
    }

    @Test
    void return_false_if_misses_more_than_one_rule_of_all_condition_in_new_password_validator() {
        try {
            isValidPassword("cabcd124537", PasswordType.VALIDATION4);
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors())
                    .containsOnly(MISSING_UPPERCASE,
                            MISSING_UNDERSCORE,
                            ILLEGAL_NUMBER_OF_FAILURES_ALLOWED);

        }
    }

    private boolean isValidPassword(String password, PasswordType type)  throws InvalidPasswordException{
        return new PasswordValidatorFactory(password, type).getInstance().isValid();
    }

}
