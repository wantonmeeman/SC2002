package Exceptions;

public class WrongPasswordException extends Exception {
    public WrongPasswordException() {
        super("Wrong Password");
    }
}
