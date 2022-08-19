package anotherSoluton;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLowerCase;

public class PasswordValidator{

    private final String password;

    protected int UPPERCASE = 0;
    protected int LOWERCASE = 0;
    protected int DIGIT_NUMBER = 0;
    protected int UNDERSCORE = 0;

    public PasswordValidator(String password) {
        this.password = password;
    }

    public boolean isValid() {
        if (!(password.length() > 6)) return false;

        checkChars();

        return isValidPassword();
    }

    private void checkChars() {
        for (int i = 0; i < password.length(); i++)
            checkChar(i);
    }

    private void checkChar(int i) {
        final char ch = password.charAt(i);
        if (Character.isUpperCase(ch))
            UPPERCASE++;
        else if (isLowerCase(ch))
            LOWERCASE++;
        else if (isDigit(ch))
            DIGIT_NUMBER++;
        else if (haveContainUnderScore(ch))
            UNDERSCORE++;
    }

    private boolean isValidPassword() {
        return haveContainLowercase() && haveContainUppercase()
                && haveContainNumber() && haveContainUnderscore();
    }

    private boolean haveContainUnderscore() {
        return UNDERSCORE >= 1;
    }

    private boolean haveContainNumber() {
        return DIGIT_NUMBER >= 1;
    }

    private boolean haveContainUppercase() {
        return UPPERCASE >= 1;
    }

    private boolean haveContainLowercase() {
        return LOWERCASE >= 1;
    }

    private boolean haveContainUnderScore(char ch) {
        return ch == '_';
    }
}
