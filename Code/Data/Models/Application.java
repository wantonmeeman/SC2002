package Data.Models;

/**
 * The type Application.
 */
public class Application extends Model {

    private String userID;
    private String flatID;
    private String status;
    private String officerID;

    /**
     * Instantiates a new Application.
     *
     * @param applicationID the application id
     * @param userID        the user id
     * @param flatID        the flat id
     * @param status        the status
     * @param officerID     the officer id
     */
    public Application(String applicationID, String userID, String flatID, String status, String officerID) {
        super(applicationID);
        this.userID = userID;
        this.flatID = flatID;
        this.status = status;
        this.officerID = officerID;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets user id.
     *
     * @param userID the user id
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Gets flat id.
     *
     * @return the flat id
     */
    public String getFlatID() {
        return flatID;
    }

    /**
     * Sets flat id.
     *
     * @param flatID the flat id
     */
    public void setFlatID(String flatID) {
        this.flatID = flatID;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets officer id.
     *
     * @return the officer id
     */
    public String getOfficerID() {
        return officerID;
    }

    /**
     * Sets officer id.
     *
     * @param officerID the officer id
     */
    public void setOfficerID(String officerID) {
        this.officerID = officerID;
    }
}
