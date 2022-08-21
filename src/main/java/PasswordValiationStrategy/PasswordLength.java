package PasswordValiationStrategy;

public enum PasswordLength {
    MORE_THAN_6_CHARS {
        @Override
        int toInt() {
            return 6;
        }
    },
    MORE_THAN_8_CHARS {
        @Override
        int toInt() {
            return 6;
        }
    },
    MORE_THAN_16_CHARS {
        @Override
        int toInt() {
            return 16;
        }
    };

    abstract int toInt();
}
