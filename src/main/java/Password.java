import java.util.LinkedHashMap;
import java.util.Map;

public class Password {

    private final String password;

    public Password(String password) {
        this.password = password;
    }

    public boolean isValid() {
        if (password.length() < 8) return false;
        Map<String,Integer> check = new LinkedHashMap<>();
        check.put("lc",0);
        check.put("uc",0);
        check.put("d",0);
        check.put("us",0);
        for (int i = 0; i < password.length(); i++) {

            final char ch = password.charAt(i);
            if (Character.isUpperCase(ch))
                check.put("uc",1);
            else if (Character.isLowerCase(ch))
                check.put("lc",1);
            else if (Character.isDigit(ch))
                check.put("d",1);
            else if (ch == '_')
                check.put("us",1);
        }

        return check.get("lc").equals(1) && check.get("uc").equals(1)
                && check.get("d").equals(1) && check.get("us").equals(1);

    }
}
