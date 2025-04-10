package Data.Models;

public class HDBOfficer extends Applicant {

    private String assignedProject;
    private char registrationStatus;//null , pending, approved, rejected

    public HDBOfficer(String id, String name, int age, char maritalStatus, String password) {
        super(id, name, age, maritalStatus, password);
        
        this.assignedProject = null;
        this.registrationStatus = '\0';//null char
    }

    public HDBOfficer(String id, String name, int age, char maritalStatus, String password, String assignedProject, char registrationStatus) {
        super(id, name, age, maritalStatus, password);

        this.assignedProject = assignedProject;//change name
        this.registrationStatus = registrationStatus;//
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
