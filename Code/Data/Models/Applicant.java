package Data.Models;

/**
 * The type Applicant.
 */
public class Applicant extends User{

    private String applicationID;

    /**
     * Instantiates a new Applicant.
     *
     * @param id            the id
     * @param name          the name
     * @param age           the age
     * @param maritalStatus the marital status
     * @param password      the password
     * @param applicationID the application id
     */
    public Applicant(String id, String name, int age, char maritalStatus, String password, String applicationID) {
        super(id, name, age, maritalStatus, password);

        this.applicationID = applicationID;
    }

    /**
     * Gets application id.
     *
     * @return application id
     */
    public String getApplicationID() {
        return applicationID;
    }

    /**
     * Sets application id.
     *
     * @param applicationID the application id
     */
    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

}
