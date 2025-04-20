package Data.Models;

public class Application extends Model {

    private String userID;
    private String flatID;
    private String status;
    private String officerID;

    public Application(String applicationID, String userID, String flatID, String status, String officerID) {
        super(applicationID);
        this.userID = userID;
        this.flatID = flatID;
        this.status = status;
        this.officerID = officerID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFlatID() {
        return flatID;
    }

    public void setFlatID(String flatID) {
        this.flatID = flatID;
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
}
