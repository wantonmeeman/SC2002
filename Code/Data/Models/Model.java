package Data.Models;

/**
 * The type Model.
 */
abstract public class Model {
    private String ID;

    /**
     * Instantiates a new Model.
     *
     * @param ID the id
     */
// Constructor to initialize ID
    public Model(String ID) {
        this.ID = ID;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
// Getter for ID
    public String getID() {
        return ID;
    }

    /**
     * Sets id.
     *
     * @param ID the id
     */
// Setter for ID
    public void setID(String ID) {
        this.ID = ID;
    }
}