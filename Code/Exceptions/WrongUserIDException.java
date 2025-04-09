package Exceptions;

public class WrongUserIDException extends Exception {
    public WrongUserIDException() {
        super("Wrong UserID");
    }
}
