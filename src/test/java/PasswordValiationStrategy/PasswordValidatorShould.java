package PasswordValiationStrategy;

import org.junit.jupiter.api.Test;

import static PasswordValiationStrategy.ErrorCode.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordValidatorShould {

    @Test
    void valid_password_matches_all_requirements() {
        assertThat(new PasswordValidator("_abcdAf1998",
                new PassValidatorMoreThan8Chars()).isValid()).isTrue();

        assertThat(new PasswordValidator("gfhiAbc_1919",
                new PassValidatorMoreThan8Chars()).isValid()).isTrue();

        assertThat(new PasswordValidator("oipWe1201_",
                new PassValidatorMoreThan8Chars()).isValid()).isTrue();

        assertThat(new PasswordValidator("oiuyAwer145789_dj",
                new PassValidatorMoreThan8Chars()).isValid()).isTrue();

        assertThat(new PasswordValidator("abcDe78",
                new PassValidatorMoreThan6Chars()).isValid()).isTrue();

        assertThat(new PasswordValidator("@wsfsDe78",
                new PassValidatorMoreThan6Chars()).isValid()).isTrue();

        assertThat(new PasswordValidator("_Aabcdefghij12345678",
                new PassValidatorMoreThan16Chars()).isValid()).isTrue();
    }

    @Test
    void invalid_password_misses_a_number() {
        try {
            new PasswordValidator("AbCdEfG_hIj", new PassValidatorMoreThan8Chars()).isValid();
            new PasswordValidator("_aSbcdefghij", new PassValidatorMoreThan6Chars()).isValid();
            new PasswordValidator("abcdEf____", new PassValidatorMoreThan6Chars()).isValid();
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(MISSING_NUMBER);
        }
    }

    @Test
    void invalid_password_missed_a_lowercase() {
        try {
            new PasswordValidator("ABCJ1245_", new PassValidatorMoreThan6Chars()).isValid();
            new PasswordValidator("ABCDEFGHIJ1245_", new PassValidatorMoreThan8Chars()).isValid();
            new PasswordValidator("ABCDEFGHIJ12447sfe__5_", new PassValidatorMoreThan16Chars()).isValid();
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(ErrorCode.MISSING_LOWERCASE);
        }

    }

    @Test
    void invalid_password_misses_an_uppercase_char() {
        try {
            new PasswordValidator("__abcde56", new PassValidatorMoreThan6Chars()).isValid();
            new PasswordValidator("__abcdefghij123456", new PassValidatorMoreThan8Chars()).isValid();
            new PasswordValidator("cvscdefghijsr556_____", new PassValidatorMoreThan16Chars()).isValid();
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(ErrorCode.MISSING_UPPERCASE);
        }

    }

    @Test
    void invalid_password_misses_an_underscore() {
        try {
            new PasswordValidator("abcdeAVfghij123456", new PassValidatorMoreThan8Chars()).isValid();
            new PasswordValidator("7fytsstghASD123TGD6", new PassValidatorMoreThan16Chars()).isValid();
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(MISSING_UNDERSCORE);
        }
    }

    @Test
    void invalid_password_is_shorter_than_required_length() {
        try {
            new PasswordValidator("_Aab8", new PassValidatorMoreThan6Chars()).isValid();
            new PasswordValidator("abcD7_", new PassValidatorMoreThan8Chars()).isValid();
            new PasswordValidator("Ab_cDeUYR7", new PassValidatorMoreThan16Chars()).isValid();
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors()).containsOnly(INVALID_PASSWORD_LENGTH);
        }
    }

    @Test
    void return_true_if_only_misses_a_rule_of_all_conditions_in_new_password_validator() {
        assertThat(new PasswordValidator("Acabcd124537",
                new PassValidatorWithAWeakerRules()).isValid()).isTrue();
    }

    @Test
    void return_false_if_misses_more_than_one_rule_of_all_condition_in_new_password_validator() {
        try {
            new PasswordValidator("cabcd124537", new PassValidatorWithAWeakerRules()).isValid();
        } catch (InvalidPasswordException e) {
            assertThat(e.getErrors())
                    .containsOnly(MISSING_UPPERCASE,
                            MISSING_UNDERSCORE,
                            ILLEGAL_NUMBER_OF_FAILURES_ALLOWED);

        }
    }

/*    @Test
    void throw_invalid_password_type_exception_when_a_password_of_different_type_is_entered_as_input() {
        assertThatExceptionOfType(InvalidPasswordTypeException.class)
                .isThrownBy(() -> new PasswordValidatorFactory("cabcd124537", VALIDATION5).isValid());

    }*/

}
