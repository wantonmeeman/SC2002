package Data.Models;

import Data.Models.Flat;

import java.util.Date;


/**
 * The type Project.
 */
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

    /**
     * Instantiates a new Project.
     *
     * @param projectID       the project id
     * @param name            the name
     * @param neighbourhoodID the neighbourhood id
     * @param openingDate     the opening date
     * @param closingDate     the closing date
     * @param visible         the visible
     * @param officerSlots    the officer slots
     * @param managerID       the manager id
     * @param twoRoomFlatID   the two room flat id
     * @param threeRoomFlatID the three room flat id
     */
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

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets neighbourhood id.
     *
     * @return the neighbourhood id
     */
    public String getNeighbourhoodID() {
        return neighbourhoodID;
    }

    /**
     * Sets neighbourhood id.
     *
     * @param neighbourhoodID the neighbourhood id
     */
    public void setNeighbourhoodID(String neighbourhoodID) {
        this.neighbourhoodID = neighbourhoodID;
    }

    /**
     * Gets opening date.
     *
     * @return the opening date
     */
    public long getOpeningDate() {
        return openingDate;
    }

    /**
     * Sets opening date.
     *
     * @param openingDate the opening date
     */
    public void setOpeningDate(long openingDate) {
        this.openingDate = openingDate;
    }

    /**
     * Gets closing date.
     *
     * @return the closing date
     */
    public long getClosingDate() {
        return closingDate;
    }

    /**
     * Sets closing date.
     *
     * @param closingDate the closing date
     */
    public void setClosingDate(long closingDate) {
        this.closingDate = closingDate;
    }

    /**
     * Is visible boolean.
     *
     * @return the boolean
     */
    public boolean isVisible() {
        return visibility;
    }

    /**
     * Toggle visibility.
     */
    public void toggleVisibility() {
        this.visibility = !this.visibility;
    }

    /**
     * Gets manager id.
     *
     * @return the manager id
     */
    public String getManagerID() {
        return managerID;
    }

    /**
     * Sets manager id.
     *
     * @param managerID the manager id
     */
    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    /**
     * Gets officer slots.
     *
     * @return the officer slots
     */
    public int getOfficerSlots() {
        return officerSlots;
    }

    /**
     * Sets officer slots.
     *
     * @param officerSlots the officer slots
     */
    public void setOfficerSlots(int officerSlots) {
        this.officerSlots = officerSlots;
    }

    /**
     * Gets three room flat id.
     *
     * @return the three room flat id
     */
    public String getThreeRoomFlatID() {
        return threeRoomFlatID;
    }

    /**
     * Sets three room flat id.
     *
     * @param threeRoomFlatID the three room flat id
     */
    public void setThreeRoomFlatID(String threeRoomFlatID) {
        this.threeRoomFlatID = threeRoomFlatID;
    }

    /**
     * Gets two room flat id.
     *
     * @return the two room flat id
     */
    public String getTwoRoomFlatID() {
        return twoRoomFlatID;
    }

    /**
     * Sets two room flat id.
     *
     * @param twoRoomFlatID the two room flat id
     */
    public void setTwoRoomFlatID(String twoRoomFlatID) {
        this.twoRoomFlatID = twoRoomFlatID;
    }
}