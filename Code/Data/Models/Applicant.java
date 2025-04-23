package Data.Models;

public class Applicant extends User{

    private String applicationID;

    public Applicant(String id, String name, int age, char maritalStatus, String password, String applicationID) {
        super(id, name, age, maritalStatus, password);

        this.applicationID = applicationID;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

}
