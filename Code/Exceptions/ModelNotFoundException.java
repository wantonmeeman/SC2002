package Exceptions;

public class ModelNotFoundException extends Exception {
    public ModelNotFoundException() {
        super("Model Not Found!");
    }
}
