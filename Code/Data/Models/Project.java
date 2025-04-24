package Data.Models;

import Data.Models.Flat;

import java.util.Date;


public class Project extends Model {

    private String name;
    private String neighbourhoodID;
    private long openingDate;
    private long closingDate;
    private boolean visibility;
    private int officerSlots;
    private String managerID;
    private String twoRoomFlatID;
    private String threeRoomFlatID;

    public Project(String projectID,
                    String name, String neighbourhoodID,
                   long openingDate, long closingDate,
                   boolean visible,
                   int officerSlots,
                   String managerID, String twoRoomFlatID,
                    String threeRoomFlatID) {
        super(projectID);//Generate random
        this.name = name;
        this.neighbourhoodID = neighbourhoodID;

        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.visibility = visible;

        this.officerSlots = officerSlots;
        this.managerID = managerID;
        this.twoRoomFlatID = twoRoomFlatID;
        this.threeRoomFlatID = threeRoomFlatID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNeighbourhoodID() {
        return neighbourhoodID;
    }

    public void setNeighbourhoodID(String neighbourhoodID) {
        this.neighbourhoodID = neighbourhoodID;
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

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public int getOfficerSlots() {
        return officerSlots;
    }

    public void setOfficerSlots(int officerSlots) {
        this.officerSlots = officerSlots;
    }

    public String getThreeRoomFlatID() {
        return threeRoomFlatID;
    }

    public void setThreeRoomFlatID(String threeRoomFlatID) {
        this.threeRoomFlatID = threeRoomFlatID;
    }

    public String getTwoRoomFlatID() {
        return twoRoomFlatID;
    }

    public void setTwoRoomFlatID(String twoRoomFlatID) {
        this.twoRoomFlatID = twoRoomFlatID;
    }
}