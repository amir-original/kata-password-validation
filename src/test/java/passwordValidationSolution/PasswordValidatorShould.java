package passwordValidationSolution;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordValidatorShould {

    @Test
    void valid_password_matches_all_requirements() {
        assertThat(new PasswordMoreThan8Characters("_abcdAf1998").isValid()).isTrue();
        assertThat(new PasswordMoreThan8Characters("gfhiAbc_1919").isValid()).isTrue();
        assertThat(new PasswordMoreThan8Characters("oipWe1201_").isValid()).isTrue();
        assertThat(new PasswordMoreThan8Characters("oiuyAwer145789_dj").isValid()).isTrue();

        assertThat(new PasswordMoreThan6Characters("abcDe78").isValid()).isTrue();
        assertThat(new PasswordMoreThan6Characters("@wsfsDe78").isValid()).isTrue();
        assertThat(new PasswordMoreThan16Characters("_Aabcdefghij12345678").isValid()).isTrue();
    }

    @Test
    void invalid_password_misses_a_number() {
        try {
            new PasswordMoreThan8Characters("AbCdEfG_hIj").isValid();
            new PasswordMoreThan8Characters("_aSbcdefghij").isValid();
            new PasswordMoreThan6Characters("abcdEf____").isValid();
        }catch (InvalidPasswordException e){
            assertThat(e.getErrorCode()).isEqualTo(ErrorCode.MISSING_NUMBER);
        }
    }

    @Test
    void invalid_password_missed_a_lowercase() {
        try {
            new PasswordMoreThan8Characters("ABCDEFGHIJ1245_").isValid();
        }catch (InvalidPasswordException e){
            assertThat(e.getErrorCode()).isEqualTo(ErrorCode.MISSING_LOWERCASE);
        }

    }

    @Test
    void invalid_password_misses_an_uppercase_char() {
       try {
           new PasswordMoreThan8Characters("__abcdefghij123456").isValid();
       }catch (InvalidPasswordException e){
           assertThat(e.getErrorCode()).isEqualTo(ErrorCode.MISSING_UPPERCASE);
       }

    }

    @Test
    void invalid_password_misses_an_underscore() {
        try {
            new PasswordMoreThan8Characters("abcdeAVfghij123456").isValid();
        }catch (InvalidPasswordException e){
            System.out.println(e.getErrorCode());
            assertThat(e.getErrorCode()).isEqualTo(ErrorCode.MISSING_UNDERSCORE);
        }
    }

    @Test
    void invalid_password_is_shorter_than_required() {
        try {
            new PasswordMoreThan8Characters("abcDe8").isValid();
        }catch (InvalidPasswordException e){
            assertThat(e.getErrorCode()).isEqualTo(ErrorCode.INVALID_PASSWORD_LENGTH);
        }
    }

    @Test
    void return_true_if_only_misses_a_rule_of_all_conditions_in_new_password_validator() {
        assertThat(new NewPasswordValidator("Acabcd124537").isValid()).isTrue();
    }

    @Test
    void return_false_if_misses_more_than_one_rule_of_all_condition_in_new_password_validator() {
        try {
            new NewPasswordValidator("cabcd124537").isValid();
        }catch (InvalidPasswordException e){
            assertThat(e.getErrorCode()).isEqualTo(ErrorCode.NUMBER_OF_FAILURES_ALLOWED);
        }
    }
}
