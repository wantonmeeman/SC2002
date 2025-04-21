package Data.Models;

public class SearchSetting extends Model {
    private String projectName;
    private boolean projectAscending;
    private String projectNeighbourhoodID;
    private boolean projectThreeRoomFlat;
    private boolean projectTwoRoomFlat;
    private String projectManagerID;

    public SearchSetting(String ID, String projectName, boolean projectAscending,
                         String projectNeighbourhoodID, boolean projectThreeRoomFlat,
                         boolean projectTwoRoomFlat, String projectManagerID) {
        super(ID);
        this.projectName = projectName;
        this.projectAscending = projectAscending;
        this.projectNeighbourhoodID = projectNeighbourhoodID;
        this.projectThreeRoomFlat = projectThreeRoomFlat;
        this.projectTwoRoomFlat = projectTwoRoomFlat;
        this.projectManagerID = projectManagerID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public boolean getProjectAscending() {
        return projectAscending;
    }

    public void setProjectAscending(boolean projectAscending) {
        this.projectAscending = projectAscending;
    }

    public String getProjectNeighbourhoodID() {
        return projectNeighbourhoodID;
    }

    public void setProjectNeighbourhoodID(String projectNeighbourhood) {
        this.projectNeighbourhoodID = projectNeighbourhood;
    }

    public boolean getProjectThreeRoomFlat() {
        return projectThreeRoomFlat;
    }

    public void setProjectThreeRoomFlat(boolean projectThreeRoomFlat) {
        this.projectThreeRoomFlat = projectThreeRoomFlat;
    }

    public boolean getProjectTwoRoomFlat() {
        return projectTwoRoomFlat;
    }

    public void setProjectTwoRoomFlat(boolean projectTwoRoomFlat) {
        this.projectTwoRoomFlat = projectTwoRoomFlat;
    }

    public String getProjectManagerID() {
        return projectManagerID;
    }

    public void setProjectManagerID(String projectManagerID) {
        this.projectManagerID = projectManagerID;
    }
}