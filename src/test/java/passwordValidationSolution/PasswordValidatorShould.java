package passwordValidationSolution;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static passwordValidationSolution.ErrorCode.*;
import static passwordValidationSolution.PasswordType.*;

public class PasswordValidatorShould {

    @Test
    void valid_password_matches_all_requirements() {
        assertThat(isValidPassword("_abcdAf1998", VALIDATION1)).isTrue();
        assertThat(isValidPassword("gfhiAbc_1919", VALIDATION1)).isTrue();
        assertThat(isValidPassword("oipWe1201_", VALIDATION1)).isTrue();
        assertThat(isValidPassword("oiuyAwer145789_dj", VALIDATION1)).isTrue();

        assertThat(isValidPassword("abcDe78", VALIDATION2)).isTrue();
        assertThat(isValidPassword("@wsfsDe78", VALIDATION2)).isTrue();
        assertThat(isValidPassword("_Aabcdefghij12345678", VALIDATION3)).isTrue();
    }

    @Test
    void invalid_password_misses_a_number() {
        try {
            isValidPassword("AbCdEfG_hIj", VALIDATION1);
            isValidPassword("_aSbcdefghij", VALIDATION2);
            isValidPassword("abcdEf____", VALIDATION2);
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(ErrorCode.MISSING_NUMBER);
        }
    }

    @Test
    void invalid_password_missed_a_lowercase() {
        try {
            isValidPassword("ABCJ1245_", VALIDATION2);
            isValidPassword("ABCDEFGHIJ1245_", VALIDATION1);
            isValidPassword("ABCDEFGHIJ12447sfe__5_", VALIDATION3);
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(ErrorCode.MISSING_LOWERCASE);
        }

    }

    @Test
    void invalid_password_misses_an_uppercase_char() {
        try {
            isValidPassword("__abcde56", VALIDATION2);
            isValidPassword("__abcdefghij123456", VALIDATION1);
            isValidPassword("cvscdefghijsr556_____", VALIDATION3);
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(ErrorCode.MISSING_UPPERCASE);
        }

    }

    @Test
    void invalid_password_misses_an_underscore() {
        try {
            isValidPassword("abcdeAVfghij123456", VALIDATION1);
            isValidPassword("7fytsstghASD123TGD6", VALIDATION3);
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(ErrorCode.MISSING_UNDERSCORE);
        }
    }

    @Test
    void invalid_password_is_shorter_than_required_length() {
        try {
            isValidPassword("_Aab8", VALIDATION2);
            isValidPassword("abcD7_", VALIDATION1);
            isValidPassword("Ab_cDeUYR7", VALIDATION3);
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(INVALID_PASSWORD_LENGTH);
        }
    }

    @Test
    void return_true_if_only_misses_a_rule_of_all_conditions_in_new_password_validator() {
        assertThat(isValidPassword("Acabcd124537", VALIDATION4)).isTrue();
    }

    @Test
    void return_false_if_misses_more_than_one_rule_of_all_condition_in_new_password_validator() {
        try {
            isValidPassword("cabcd124537", VALIDATION4);
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors())
                    .containsOnly(MISSING_UPPERCASE,
                            MISSING_UNDERSCORE,
                            ILLEGAL_NUMBER_OF_FAILURES_ALLOWED);

        }
    }

    @Test
    void throw_invalid_password_type_exception_when_a_password_of_different_type_is_entered_as_input() {
        assertThatExceptionOfType(InvalidPasswordTypeException.class)
                .isThrownBy(()-> isValidPassword("cabcd124537", VALIDATION5));

    }

    private boolean isValidPassword(String password, PasswordType type) {
        return new PasswordValidatorFactory(password, type).getInstance().isValid();
    }
}
