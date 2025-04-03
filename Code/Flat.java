
public class Flat {
    private String type;
    private int totalUnits;
    //private int availableUnits;
    //Is there available units?
    //Testing pushing to GitHub
    private float price;

    public Flat(String type, int totalUnits, float price) {
        this.type = type;
        this.totalUnits = totalUnits;
        //this.availableUnits = availableUnits;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public int getTotalUnits() {
        return totalUnits;
    }

    // public int getAvailableUnits() {
    //     return availableUnits;
    // }

    public void setType(String type) {
        this.type = type;
    }

    public void setTotalUnits(int totalUnits) {
        this.totalUnits = totalUnits;
    }

    // public void setAvailableUnits(int availableUnits) {
    //     this.availableUnits = availableUnits;
    // }

    @Override
    public String toString() {
        return "TypeFlat{" +
                "type='" + type + '\'' +
                ", totalUnits=" + totalUnits +
                ", price=" + price +
                '}';
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
