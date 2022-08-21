package PasswordValiationStrategy;

@FunctionalInterface
public interface PasswordValidatorStrategy {
    boolean isValid(String password);
}
