package Data.Models;

/**
 * The type Flat.
 */
public class Flat extends Model{
    private String type;
    private int totalUnits;
    private float price;

    /**
     * Instantiates a new Flat.
     *
     * @param ID         the id
     * @param type       the type
     * @param totalUnits the total units
     * @param price      the price
     */
    public Flat(String ID,String type, int totalUnits, float price) {
        super(ID);
        this.type = type;
        this.totalUnits = totalUnits;
        this.price = price;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets total units.
     *
     * @return the total units
     */
    public int getTotalUnits() {
        return totalUnits;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets total units.
     *
     * @param totalUnits the total units
     */
    public void setTotalUnits(int totalUnits) {
        this.totalUnits = totalUnits;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(float price) {
        this.price = price;
    }
}