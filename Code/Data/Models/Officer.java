package Data.Models;

public class Officer extends Applicant {

    private String assignedProjectID;
    private char registrationStatus;//null , pending, approved, rejected

    public Officer(String id, String name, int age, char maritalStatus, String password) {
        super(id, name, age, maritalStatus, password);
        
        this.assignedProjectID = null;
        this.registrationStatus = '\0';//null char
    }

    public Officer(String id, String name, int age, char maritalStatus, String password, String assignedProjectID, char registrationStatus) {
        super(id, name, age, maritalStatus, password);

        this.assignedProjectID = assignedProjectID;//change name
        this.registrationStatus = registrationStatus;//
    }

    public String getAssignedProjectID() {
        return assignedProjectID;
    }

    public void setAssignedProjectID(String assignedProjectID) {
        this.assignedProjectID = assignedProjectID;
    }

    public char getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(char registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}
