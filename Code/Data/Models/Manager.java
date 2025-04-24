package Data.Models;

/**
 * The type Manager.
 */
public class Manager extends User {

    /**
     * Instantiates a new Manager.
     *
     * @param id            the id
     * @param name          the name
     * @param age           the age
     * @param maritalStatus the marital status
     * @param password      the password
     */
    public Manager(String id, String name, int age, char maritalStatus, String password) {
        super(id, name, age, maritalStatus, password);
    }

}
