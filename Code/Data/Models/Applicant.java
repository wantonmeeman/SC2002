package Data.Models;

public class Applicant extends User{

    private String applicationID;

    /**
     *
     * @param id
     * @param name
     * @param age
     * @param maritalStatus
     * @param password
     * @param applicationID
     */
    public Applicant(String id, String name, int age, char maritalStatus, String password, String applicationID) {
        super(id, name, age, maritalStatus, password);

        this.applicationID = applicationID;
    }

    /**
     *
     * @return
     */
    public String getApplicationID() {
        return applicationID;
    }

    /**
     * 
     * @param applicationID
     */
    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

}
