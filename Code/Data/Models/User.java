package Data.Models;
import Data.Models.SearchSetting;

abstract public class User extends Model {
    private String name;
    private String password;
    private char maritalStatus;
    private int age;

    public User(String id, String name, int age, char maritalStatus, String password) {
        super(id);
        this.name = name;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.password = password;
    }

    public char getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(char maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User {" +
                "ID='" + getID() + '\'' +
                ", Name='" + name + '\'' +
                ", Age=" + age +
                ", Marital Status=" + maritalStatus;
    }
}
