package Data.Models;

abstract public class User extends Model {
    private String name;
    private String password;
    private char maritalStatus;
    public SearchSettings searchSettings;
    private int age;

    public User(String id, String name, int age, char maritalStatus, String password) {
        super(id);
        this.name = name;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.password = password;

        this.searchSettings = new SearchSettings();
    }

    public boolean login(String id, String password) {//Remove this?
        return id.equals(getID()) && password.equals(this.password);
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

    @Override //Debugging! remove when done
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User ID: ").append(getID()).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Age: ").append(age).append("\n");
        sb.append("Marital Status: ").append(maritalStatus).append("\n");
        sb.append("Instance of: ").append(this.getClass().getSimpleName()).append("\n");
        sb.append("Filter Settings: ").append(searchSettings != null ? "\n"+(searchSettings.toString()) : "None").append("\n");
        return sb.toString();
    }
}
