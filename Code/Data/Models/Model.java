package Data.Models;

abstract public class Model {
    private String ID;

    // Constructor to initialize ID
    public Model(String ID) {
        this.ID = ID;
    }

    // Getter for ID
    public String getID() {
        return ID;
    }

    // Setter for ID
    public void setID(String ID) {
        this.ID = ID;
    }
}