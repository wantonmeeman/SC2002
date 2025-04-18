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
    private String twoRoomFlatID;
    private String threeRoomFlatID;

    public Project(String projectID,
                    String name, String neighbourhood,
                   long openingDate, long closingDate,
                   boolean visible,
                   int officerSlots, String[] officerIDs,
                   String managerID, String twoRoomFlatID,
                    String threeRoomFlatID) {
        super(projectID);//Generate random
        this.name = name;
        this.neighbourhood = neighbourhood;

        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.visibility = visible;

        this.officerSlots = officerSlots;
        this.officersIDs = officerIDs;
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