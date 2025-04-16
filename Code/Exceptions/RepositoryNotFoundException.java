package Exceptions;

public class RepositoryNotFoundException extends Exception {
    public RepositoryNotFoundException() {
        super("Repository Not Found");
    }
}
