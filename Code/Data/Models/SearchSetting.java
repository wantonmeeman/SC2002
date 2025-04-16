package Data.Models;

public class SearchSetting{
    private String location;
    private Boolean[] flatTypes;
    private String neighbourhood;
    private long openingDate;
    private long closingDate;
    private Boolean ascending;
    private int sortBy;

    public SearchSetting(){
        //These are the default values and we will use these to check if they are in use or not
        this.location = ""; // 0
        this.flatTypes = new Boolean[] {true, true}; //1
        this.neighbourhood = ""; // 2
        this.openingDate = 0;// 3
        this.closingDate = 0; // 4
        this.ascending = true;
        this.sortBy = 0;
    }

    public SearchSetting(String location, Boolean[] flatTypes, String neighbourhood, long openingDate, long closingDate, Boolean ascending, int sortBy){
        this.location = location;
        this.flatTypes = flatTypes;
        this.neighbourhood = neighbourhood;
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

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public long getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(long openingDate) {
        this.openingDate = openingDate;
    }

    public long getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(long closingDate) {
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
