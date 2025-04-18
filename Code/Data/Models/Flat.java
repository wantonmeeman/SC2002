package Data.Models;

public class Flat extends Model{
    private String type;
    private int totalUnits;
    private float price;

    public Flat(String ID,String type, int totalUnits, float price) {
        super(ID);
        this.type = type;
        this.totalUnits = totalUnits;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public int getTotalUnits() {
        return totalUnits;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTotalUnits(int totalUnits) {
        this.totalUnits = totalUnits;
    }

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