package Data.Models;

public class Manager extends User {

    private String projectID;

    public Manager(String id, String name, int age, char maritalStatus, String password, String projectID) {
        super(id, name, age, maritalStatus, password);
        this.projectID = projectID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

}
