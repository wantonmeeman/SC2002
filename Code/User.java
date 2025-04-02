
abstract public class User {

    private String userID;//Not using NRIC field as they it is basically userID
    private String name;
    private String password;
    private char maritalStatus;
    public FilterSettings filterSettings;
    private int age;

    public User(String userID, String name, int age, char maritalStatus, String password) {
        this.userID = userID;
        this.name = name;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.password = password;

        this.filterSettings = new FilterSettings();
    }

    public boolean login(String userid, String password) {
        return userid.equals(this.userID) && password.equals(this.password);
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User ID: ").append(userID).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Age: ").append(age).append("\n");
        sb.append("Marital Status: ").append(maritalStatus).append("\n");
        sb.append("Instance of: ").append(this.getClass().getSimpleName()).append("\n");
        sb.append("Filter Settings: ").append(filterSettings != null ? "\n"+(filterSettings.toString()) : "None").append("\n");
        return sb.toString();
    }
}
