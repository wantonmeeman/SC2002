package Data.Models;

public class Applicant extends User{

    private String currentApplicationID;

    public Applicant(String id, String name, int age, char maritalStatus, String password) {
        super(id, name, age, maritalStatus, password);
        
        this.currentApplicationID = null;
    }

    public String getCurrentApplicationID() {
        return currentApplicationID;
    }

    public void setCurrentApplicationID(String currentApplicationID) {
        this.currentApplicationID = currentApplicationID;
    }
}
