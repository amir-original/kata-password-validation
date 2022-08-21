package passwordValidationSolution;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static passwordValidationSolution.ErrorCode.*;
import static passwordValidationSolution.PasswordType.*;

public class PasswordValidatorShould {

    @Test
    void valid_password_matches_all_requirements() {
        assertThat(new PasswordValidatorStrategy("_abcdAf1998", VALIDATION1).isValid()).isTrue();
        assertThat(new PasswordValidatorStrategy("gfhiAbc_1919", VALIDATION1).isValid()).isTrue();
        assertThat(new PasswordValidatorStrategy("oipWe1201_", VALIDATION1).isValid()).isTrue();
        assertThat(new PasswordValidatorStrategy("oiuyAwer145789_dj", VALIDATION1).isValid()).isTrue();

        assertThat(new PasswordValidatorStrategy("abcDe78", VALIDATION2).isValid()).isTrue();
        assertThat(new PasswordValidatorStrategy("@wsfsDe78", VALIDATION2).isValid()).isTrue();
        assertThat(new PasswordValidatorStrategy("_Aabcdefghij12345678", VALIDATION3).isValid()).isTrue();
    }

    @Test
    void invalid_password_misses_a_number() {
        try {
            new PasswordValidatorStrategy("AbCdEfG_hIj", VALIDATION1).isValid();
            new PasswordValidatorStrategy("_aSbcdefghij", VALIDATION2).isValid();
            new PasswordValidatorStrategy("abcdEf____", VALIDATION2).isValid();
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(ErrorCode.MISSING_NUMBER);
        }
    }

    @Test
    void invalid_password_missed_a_lowercase() {
        try {
            new PasswordValidatorStrategy("ABCJ1245_", VALIDATION2).isValid();
            new PasswordValidatorStrategy("ABCDEFGHIJ1245_", VALIDATION1).isValid();
            new PasswordValidatorStrategy("ABCDEFGHIJ12447sfe__5_", VALIDATION3).isValid();
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(ErrorCode.MISSING_LOWERCASE);
        }

    }

    @Test
    void invalid_password_misses_an_uppercase_char() {
        try {
            new PasswordValidatorStrategy("__abcde56", VALIDATION2).isValid();
            new PasswordValidatorStrategy("__abcdefghij123456", VALIDATION1).isValid();
            new PasswordValidatorStrategy("cvscdefghijsr556_____", VALIDATION3).isValid();
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(ErrorCode.MISSING_UPPERCASE);
        }

    }

    @Test
    void invalid_password_misses_an_underscore() {
        try {
            new PasswordValidatorStrategy("abcdeAVfghij123456", VALIDATION1).isValid();
            new PasswordValidatorStrategy("7fytsstghASD123TGD6", VALIDATION3).isValid();
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(ErrorCode.MISSING_UNDERSCORE);
        }
    }

    @Test
    void invalid_password_is_shorter_than_required_length() {
        try {
            new PasswordValidatorStrategy("_Aab8", VALIDATION2).isValid();
            new PasswordValidatorStrategy("abcD7_", VALIDATION1).isValid();
            new PasswordValidatorStrategy("Ab_cDeUYR7", VALIDATION3).isValid();
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(INVALID_PASSWORD_LENGTH);
        }
    }

    @Test
    void return_true_if_only_misses_a_rule_of_all_conditions_in_new_password_validator() {
        assertThat(new PasswordValidatorStrategy("Acabcd124537", VALIDATION4).isValid()).isTrue();
    }

    @Test
    void return_false_if_misses_more_than_one_rule_of_all_condition_in_new_password_validator() {
        try {
            new PasswordValidatorStrategy("cabcd124537", VALIDATION4).isValid();
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors())
                    .containsOnly(MISSING_UPPERCASE,
                            MISSING_UNDERSCORE,
                            ILLEGAL_NUMBER_OF_FAILURES_ALLOWED);

        }
    }

}
