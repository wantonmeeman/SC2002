package Data.Models;

/**
 * The type Registration.
 */
public class Registration extends Model {

    private String officerID;
    private String projectID;
    private String status;

    /**
     * Instantiates a new Registration.
     *
     * @param registrationID the registration id
     * @param officerID      the officer id
     * @param projectID      the project id
     * @param status         the status
     */
    public Registration(String registrationID, String officerID, String projectID, String status) {
        super(registrationID);

        this.officerID = officerID;
        this.projectID = projectID;
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

    /**
     * Gets project id.
     *
     * @return the project id
     */
    public String getProjectID() {
        return projectID;
    }

    /**
     * Sets project id.
     *
     * @param projectID the project id
     */
    public void setProjectID(String projectID) {
        this.projectID = projectID;
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
}

