package Data.Models;

import Data.Models.Flat;

import java.util.Date;


public class Project extends Model {

    private String name;
    private String neighbourhood;
    private long openingDate;
    private long closingDate;
    private boolean visibility;
    private int officerSlots;
    private String[] officersIDs;
    private String managerID;
    private Flat[] flats;

    public Project(String projectID,
                    String name, String neighbourhood,
                   long openingDate, long closingDate,
                   int officerSlots, String[] officerIDs,
                   String managerID, Flat[] flats) {
        super(projectID);//Generate random
        this.name = name;
        this.neighbourhood = neighbourhood;

        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.visibility = true;

        this.officerSlots = officerSlots;
        this.officersIDs = officerIDs;
        this.managerID = managerID;
        this.flats = flats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isVisible() {
        return visibility;
    }

    public void toggleVisibility() {
        this.visibility = !this.visibility;
    }

    public Flat[] getFlats() {
        return flats;
    }

    public void setFlats(Flat[] flats) {
        this.flats = flats;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String[] getOfficersIDs() {
        return officersIDs;
    }

    public void setOfficersIDs(String[] officersIDs) {
        this.officersIDs = officersIDs;
    }

    public int getOfficerSlots() {
        return officerSlots;
    }

    public void setOfficerSlots(int officerSlots) {
        this.officerSlots = officerSlots;
    }

}