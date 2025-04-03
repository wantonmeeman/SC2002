package Classes;
import java.util.Date;

public class FilterSettings{
    private String location;
    private Boolean[] flatTypes;
    private String neighborhood;
    private Date openingDate;
    private Date closingDate;

    public FilterSettings(){
        //These are the default values and we will use these to check if they are in use or not
        this.location = "";
        this.flatTypes = new Boolean[] {true, true};
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

    public Boolean[] getFlatTypes() {
        return flatTypes;
    }

    public void Boolean(Boolean[] flatTypes) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Location: ").append(location.isEmpty() ? "Not Set" : location).append("\n");
        sb.append("Neighborhood: ").append(neighborhood.isEmpty() ? "Not Set" : neighborhood).append("\n");

        sb.append("Opening Date: ").append(openingDate != null ? openingDate.toString() : "Not Set").append("\n");
        sb.append("Closing Date: ").append(closingDate != null ? closingDate.toString() : "Not Set").append("\n");

        sb.append("Flat Types: ");
        if (flatTypes != null && flatTypes.length > 0) {
            for (Boolean type : flatTypes) {
                sb.append(type ? "Available " : "Not Available ");
            }
        } else {
            sb.append("None");
        }
        sb.append("\n");

        return sb.toString();
    }

}
