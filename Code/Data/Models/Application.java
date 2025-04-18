package Data.Models;

public class Application extends Model {

    private String userID;
    private String projectID;
    private String status;
    private String officerID;
    private int type;

    public Application(String applicationID, String userID, String projectID, String status, String officerID, int type) {
        super(applicationID);
        this.userID = userID;
        this.projectID = projectID;
        this.status = status;
        this.officerID = officerID;
        this.type = type;
    }

    public Application(String applicationID, String userID, String projectID, int type) {
        super(applicationID);
        this.userID = userID;
        this.projectID = projectID;
        this.type = type;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOfficerID() {
        return officerID;
    }

    public void setOfficerID(String officerID) {
        this.officerID = officerID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
