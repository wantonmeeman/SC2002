package Data.Models;

/**
 * The type Officer.
 */
public class Officer extends Applicant {

    /**
     * Instantiates a new Officer.
     *
     * @param id            the id
     * @param name          the name
     * @param age           the age
     * @param maritalStatus the marital status
     * @param password      the password
     * @param applicationID the application id
     */
    public Officer(String id, String name, int age, char maritalStatus, String password, String applicationID) {
        super(id, name, age, maritalStatus, password,applicationID);
    }

}
