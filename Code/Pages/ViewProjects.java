package Pages;

public class ViewProjects {
    public void viewByLocation(String location) {
        System.out.println("Projects in: " + location);
    }

    public void viewByFlatType(String type) {
        System.out.println("Projects with type: " + type);
    }

    public void viewByAlphabetical() {
        System.out.println("Projects listed alphabetically.");
    }

    public void viewAvailableProject(String userType) {
        System.out.println("Available projects for: " + userType);
    }

    public void viewAppliedProject(String userType) {
        System.out.println("Applied projects for: " + userType);
    }
}
