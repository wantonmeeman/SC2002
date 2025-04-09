package Models;
import Models.User;

public class HDBManager extends User {

    private String managedProjectID;

    public HDBManager(String userID, String name, int age, char maritalStatus, String password) {
        super(userID, name, age, maritalStatus, password); 
        this.managedProjectID = null;
    }

    public String getManagedProjectID() {
        return managedProjectID;
    }

    public void setManagedProjectID(String managedProjectID) {
        this.managedProjectID = managedProjectID;
    }

}
