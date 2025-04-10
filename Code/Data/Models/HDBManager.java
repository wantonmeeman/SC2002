package Data.Models;

public class HDBManager extends User {

    private String managedProjectID;

    public HDBManager(String id, String name, int age, char maritalStatus, String password) {
        super(id, name, age, maritalStatus, password);
        this.managedProjectID = null;
    }

    public HDBManager(String id, String name, int age, char maritalStatus, String password, String managedProjectID) {
        super(id, name, age, maritalStatus, password);
        this.managedProjectID = managedProjectID;
    }

    public String getManagedProjectID() {
        return managedProjectID;
    }

    public void setManagedProjectID(String managedProjectID) {
        this.managedProjectID = managedProjectID;
    }

}
