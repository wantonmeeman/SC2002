import java.util.Date;

public class FilterSettings{
    private String location;
    private String[] flatTypes;
    private String neighborhood;
    private Date openingDate;
    private Date closingDate;

    public FilterSettings(){
        //These are the default values and we will use these to check if they are in use or not
        this.location = "";
        this.flatTypes = new String[2];
        this.neighborhood = "";
        this.openingDate = null;
        this.closingDate = null;
    }    

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String[] getFlatTypes() {
        return flatTypes;
    }

    public void setFlatTypes(String[] flatTypes) {
        this.flatTypes = flatTypes;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }
}
