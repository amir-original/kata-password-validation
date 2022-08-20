package passwordValidationSolution;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordValidatorShould {

    @Test
    void valid_password_matches_all_requirements() {
        assertThat(new PassValidatorMoreThan8Chars("_abcdAf1998").isValid()).isTrue();
        assertThat(new PassValidatorMoreThan8Chars("gfhiAbc_1919").isValid()).isTrue();
        assertThat(new PassValidatorMoreThan8Chars("oipWe1201_").isValid()).isTrue();
        assertThat(new PassValidatorMoreThan8Chars("oiuyAwer145789_dj").isValid()).isTrue();

        assertThat(new PassValidatorMoreThan6Chars("abcDe78").isValid()).isTrue();
        assertThat(new PassValidatorMoreThan6Chars("@wsfsDe78").isValid()).isTrue();
        assertThat(new PassValidatorMoreThan16Chars("_Aabcdefghij12345678").isValid()).isTrue();
    }

    @Test
    void invalid_password_misses_a_number() {
        try {
            new PassValidatorMoreThan8Chars("AbCdEfG_hIj").isValid();
            new PassValidatorMoreThan8Chars("_aSbcdefghij").isValid();
            new PassValidatorMoreThan6Chars("abcdEf____").isValid();
        }catch (InvalidPasswordException e){
            assertThat(e.getErrorCode()).isEqualTo(ErrorCode.MISSING_NUMBER);
        }
    }

    @Test
    void invalid_password_missed_a_lowercase() {
        try {
            new PassValidatorMoreThan6Chars("ABCJ1245_").isValid();
            new PassValidatorMoreThan8Chars("ABCDEFGHIJ1245_").isValid();
            new PassValidatorMoreThan16Chars("ABCDEFGHIJ12447sfe__5_").isValid();
        }catch (InvalidPasswordException e){
            assertThat(e.getErrorCode()).isEqualTo(ErrorCode.MISSING_LOWERCASE);
        }

    }

    @Test
    void invalid_password_misses_an_uppercase_char() {
       try {
           new PassValidatorMoreThan6Chars("__abcde56").isValid();
           new PassValidatorMoreThan8Chars("__abcdefghij123456").isValid();
           new PassValidatorMoreThan16Chars("cvscdefghijsr556_____").isValid();
       }catch (InvalidPasswordException e){
           assertThat(e.getErrorCode()).isEqualTo(ErrorCode.MISSING_UPPERCASE);
       }

    }

    @Test
    void invalid_password_misses_an_underscore() {
        try {
            new PassValidatorMoreThan8Chars("abcdeAVfghij123456").isValid();
            new PassValidatorMoreThan16Chars("7fytsstghASD123TGD6").isValid();
        }catch (InvalidPasswordException e){
            assertThat(e.getErrorCode()).isEqualTo(ErrorCode.MISSING_UNDERSCORE);
        }
    }

    @Test
    void invalid_password_is_shorter_than_required_length() {
        try {
            new PassValidatorMoreThan6Chars("_Aab8").isValid();
            new PassValidatorMoreThan8Chars("abcD7_").isValid();
            new PassValidatorMoreThan16Chars("Ab_cDeUYR7").isValid();
        }catch (InvalidPasswordException e){
            assertThat(e.getErrorCode()).isEqualTo(ErrorCode.INVALID_PASSWORD_LENGTH);
        }
    }

    @Test
    void return_true_if_only_misses_a_rule_of_all_conditions_in_new_password_validator() {
        assertThat(new PassValidatorWithAWeakerRules("Acabcd124537").isValid()).isTrue();
    }

    @Test
    void return_false_if_misses_more_than_one_rule_of_all_condition_in_new_password_validator() {
        try {
            new PassValidatorWithAWeakerRules("cabcd124537").isValid();
        }catch (InvalidPasswordException e){
            assertThat(e.getErrorCode()).isEqualTo(ErrorCode.ILLEGAL_NUMBER_OF_FAILURES_ALLOWED);
        }
    }
}
