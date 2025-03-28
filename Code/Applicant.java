public class Applicant extends User{

    private String currentApplicationID;

    public Applicant(String userID, String name, int age, char maritalStatus, String password) {
        super(userID, name, age, maritalStatus, password);
        
        this.currentApplicationID = null;
    }

    public String getCurrentApplicationID() {
        return currentApplicationID;
    }

    public void setCurrentApplicationID(String currentApplicationID) {
        this.currentApplicationID = currentApplicationID;
    }
}
