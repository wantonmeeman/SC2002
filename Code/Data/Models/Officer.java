package Data.Models;

public class Officer extends Applicant {

    private String registrationID;

    public Officer(String id, String name, int age, char maritalStatus, String password) {
        super(id, name, age, maritalStatus, password);
        
        this.registrationID = null;
    }

    public Officer(String id, String name, int age, char maritalStatus, String password, String registrationID) {
        super(id, name, age, maritalStatus, password);

        this.registrationID = registrationID;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }
}
