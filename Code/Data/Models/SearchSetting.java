package Data.Models;

/**
 * The type Search setting.
 */
public class SearchSetting extends Model {
    private String projectName;
    private boolean projectAscending;
    private String projectNeighbourhoodID;
    private boolean projectThreeRoomFlat;
    private boolean projectTwoRoomFlat;
    private String projectManagerID;

    /**
     * Instantiates a new Search setting.
     *
     * @param ID                     the id
     * @param projectName            the project name
     * @param projectAscending       the project ascending
     * @param projectNeighbourhoodID the project neighbourhood id
     * @param projectThreeRoomFlat   the project three room flat
     * @param projectTwoRoomFlat     the project two room flat
     * @param projectManagerID       the project manager id
     */
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

    /**
     * Gets project name.
     *
     * @return the project name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets project name.
     *
     * @param projectName the project name
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Gets project ascending.
     *
     * @return the project ascending
     */
    public boolean getProjectAscending() {
        return projectAscending;
    }

    /**
     * Sets project ascending.
     *
     * @param projectAscending the project ascending
     */
    public void setProjectAscending(boolean projectAscending) {
        this.projectAscending = projectAscending;
    }

    /**
     * Gets project neighbourhood id.
     *
     * @return the project neighbourhood id
     */
    public String getProjectNeighbourhoodID() {
        return projectNeighbourhoodID;
    }

    /**
     * Sets project neighbourhood id.
     *
     * @param projectNeighbourhood the project neighbourhood
     */
    public void setProjectNeighbourhoodID(String projectNeighbourhood) {
        this.projectNeighbourhoodID = projectNeighbourhood;
    }

    /**
     * Gets project three room flat.
     *
     * @return the project three room flat
     */
    public boolean getProjectThreeRoomFlat() {
        return projectThreeRoomFlat;
    }

    /**
     * Sets project three room flat.
     *
     * @param projectThreeRoomFlat the project three room flat
     */
    public void setProjectThreeRoomFlat(boolean projectThreeRoomFlat) {
        this.projectThreeRoomFlat = projectThreeRoomFlat;
    }

    /**
     * Gets project two room flat.
     *
     * @return the project two room flat
     */
    public boolean getProjectTwoRoomFlat() {
        return projectTwoRoomFlat;
    }

    /**
     * Sets project two room flat.
     *
     * @param projectTwoRoomFlat the project two room flat
     */
    public void setProjectTwoRoomFlat(boolean projectTwoRoomFlat) {
        this.projectTwoRoomFlat = projectTwoRoomFlat;
    }

    /**
     * Gets project manager id.
     *
     * @return the project manager id
     */
    public String getProjectManagerID() {
        return projectManagerID;
    }

    /**
     * Sets project manager id.
     *
     * @param projectManagerID the project manager id
     */
    public void setProjectManagerID(String projectManagerID) {
        this.projectManagerID = projectManagerID;
    }
}