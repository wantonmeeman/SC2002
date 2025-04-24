package Data.Models;

/**
 * The type Neighbourhood.
 */
public class Neighbourhood extends Model{
    private String name;

    /**
     * Instantiates a new Neighbourhood.
     *
     * @param ID   the id
     * @param name the name
     */
    public Neighbourhood(String ID, String name) {
        super(ID);
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
