package Data;

public class HDBOfficer extends Applicant {

    private String assignedProject;
    private char registrationStatus;//null , pending, approved, rejected

    public HDBOfficer(String userID, String name, int age, char maritalStatus, String password) {
        super(userID, name, age, maritalStatus, password);
        
        this.assignedProject = null;
        this.registrationStatus = '\0';//null char
    }

    public String getAssignedProject() {
        return assignedProject;
    }

    public void setAssignedProject(String assignedProject) {
        this.assignedProject = assignedProject;
    }

    public char getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(char registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}
