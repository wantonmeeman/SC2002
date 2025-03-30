
import java.util.List;

public class ProjectTest {
    public static void main(String[] args) {
        List<Project> projects = DataLoader.loadProjects("InputData/ProjectList.csv");

        for (Project p : projects) {
            System.out.println(p);
        }
    }
}
