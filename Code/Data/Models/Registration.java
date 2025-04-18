package Data.Models;

public class Registration extends Model {

    private String officerID;
    private String projectID;
    private String status;

    public Registration(String registrationID, String officerID, String projectID, String status) {
        super(registrationID);

        this.officerID = officerID;
        this.projectID = projectID;
        this.status = status;
    }

    public String getOfficerID() {
        return officerID;
    }

    public void setOfficerID(String officerID) {
        this.officerID = officerID;
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
}

