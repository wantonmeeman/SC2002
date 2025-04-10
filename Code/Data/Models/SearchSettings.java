package Data.Models;

import java.util.Date;

public class SearchSettings {
    private String location;
    private Boolean[] flatTypes;
    private String neighborhood;
    private Date openingDate;
    private Date closingDate;
    private Boolean ascending;
    private int sortBy;

    public SearchSettings(){
        //These are the default values and we will use these to check if they are in use or not
        this.location = ""; // 0
        this.flatTypes = new Boolean[] {true, true}; //1
        this.neighborhood = ""; // 2
        this.openingDate = null;// 3
        this.closingDate = null; // 4
        this.ascending = true;
        this.sortBy = 0;
    }

    public SearchSettings(String location, Boolean[] flatTypes, String neighborhood, Date openingDate, Date closingDate, Boolean ascending, int sortBy){
        this.location = location;
        this.flatTypes = flatTypes;
        this.neighborhood = neighborhood;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.ascending = ascending;
        this.sortBy = sortBy;
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

    public Boolean getAscending() {
        return ascending;
    }

    public void setAscending(Boolean ascending) {
        this.ascending = ascending;
    }

    public int getSortBy() {
        return sortBy;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }
}
