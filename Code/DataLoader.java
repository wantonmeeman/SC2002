
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataLoader {
    public static List<Project> loadProjects(String filename) {
        List<Project> projects = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String projectID = data[0];
                String name = data[1];
                String neighbourhood = data[2];
                Date openingDate = sdf.parse(data[3]);
                Date closingDate = sdf.parse(data[4]);
                boolean visibility = Boolean.parseBoolean(data[5]);
                int officerSlots = Integer.parseInt(data[6]);

                List<TypeFlat> typeFlats = new ArrayList<>();
                for (int i = 7; i < data.length; i += 3) {
                    String type = data[i];
                    int totalUnits = Integer.parseInt(data[i + 1]);
                    int availableUnits = Integer.parseInt(data[i + 2]);
                    typeFlats.add(new TypeFlat(type, totalUnits, availableUnits));
                }

                Project project = new Project(projectID, name, neighbourhood, openingDate, closingDate,
                        visibility, officerSlots, typeFlats);
                projects.add(project);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projects;
    }
}
