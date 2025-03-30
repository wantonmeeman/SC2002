
public class TypeFlat {
    private String type;
    private int totalUnits;
    private int availableUnits;

    public TypeFlat(String type, int totalUnits, int availableUnits) {
        this.type = type;
        this.totalUnits = totalUnits;
        this.availableUnits = availableUnits;
    }

    public String getType() {
        return type;
    }

    public int getTotalUnits() {
        return totalUnits;
    }

    public int getAvailableUnits() {
        return availableUnits;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTotalUnits(int totalUnits) {
        this.totalUnits = totalUnits;
    }

    public void setAvailableUnits(int availableUnits) {
        this.availableUnits = availableUnits;
    }

    @Override
    public String toString() {
        return "TypeFlat{" +
                "type='" + type + '\'' +
                ", totalUnits=" + totalUnits +
                ", availableUnits=" + availableUnits +
                '}';
    }
}
