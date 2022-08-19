import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordShould {


//    Have more than 8 characters
//    Contains a capital letter
//    Contains a lowercase
//    Contains a number
//    Contains an underscore

    @Test
    void valid_password() {
        assertThat(new Password("_abcdAf1998").isValid()).isTrue();
        assertThat(new Password("gfhiAbc_1919").isValid()).isTrue();
        assertThat(new Password("oipWe1201_").isValid()).isTrue();
        assertThat(new Password("oiuyAwer145789_dj").isValid()).isTrue();
    }

    @Test
    void not_valid() {
        assertThat(new Password("AbCdEfGhIj").isValid()).isFalse();
        assertThat(new Password("abcdefghij123456").isValid()).isFalse();
        assertThat(new Password("AbCdEfGhIj").isValid()).isFalse();
        assertThat(new Password("abcdefghij").isValid()).isFalse();
        assertThat(new Password("_abcdefghij123456").isValid()).isFalse();
    }
}
