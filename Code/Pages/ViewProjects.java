
import java.util.List;

/**
 * Class that handles viewing projects based on different criteria.
 */
public class ViewProjects {

    /**
     * Shows projects by location.
     *
     * @param location the location to filter by
     */
    public void viewByLocation(String location) {
        System.out.println("Projects in: " + location);
    }

    /**
     * Shows projects by flat type.
     *
     * @param type the flat type (e.g. 3-Room, 4-Room)
     */
    public void viewByFlatType(String type) {
        System.out.println("Projects with type: " + type);
    }

    /**
     * Shows all projects in alphabetical order.
     */
    public void viewByAlphabetical() {
        System.out.println("Projects listed alphabetically.");
    }

    /**
     * Displays projects available for a user type.
     *
     * @param userType The type of user (e.g., Applicant, Officer)
     */
    public void viewAvailableProject(String userType) {
        System.out.println("Available projects for: " + userType);
    }

    /**
     * Displays the list of projects applied by a specific user type.
     *
     * @param userType The type of user (e.g., Applicant)
     */
    public void viewAppliedProject(String userType) {
        System.out.println("Applied projects for: " + userType);
    }
}
