package Data.Models;
import Data.Models.SearchSetting;

/**
 * The type User.
 */
abstract public class User extends Model {
    private String name;
    private String password;
    private char maritalStatus;
    private int age;

    /**
     * Instantiates a new User.
     *
     * @param id            the id
     * @param name          the name
     * @param age           the age
     * @param maritalStatus the marital status
     * @param password      the password
     */
    public User(String id, String name, int age, char maritalStatus, String password) {
        super(id);
        this.name = name;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.password = password;
    }

    /**
     * Gets marital status.
     *
     * @return the marital status
     */
    public char getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Sets marital status.
     *
     * @param maritalStatus the marital status
     */
    public void setMaritalStatus(char maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(int age) {
        this.age = age;
    }
}
