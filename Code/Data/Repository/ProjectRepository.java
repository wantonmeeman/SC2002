package Data.Repository;

import Data.Models.Model;
import Data.Models.Project;

import static Util.Config.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * The type Project repository.
 */
public class ProjectRepository extends DataRepository {
    private static ProjectRepository instance;

    /**
     * Instantiates a new Project repository.
     */
    public ProjectRepository() {
        setFilepath(DATA_PATH + PROJECT_CSV);
        fetch();
    }

    @Override
    protected ArrayList<Model> toModelList(ArrayList<ArrayList<String>> csv) {
        ArrayList<Model> projectArr = new ArrayList<>();

        for (ArrayList<String> strArr : csv) {

            projectArr.add(new Project(
                    strArr.get(0),
                    strArr.get(1),
                    strArr.get(2),
                    Long.parseLong(strArr.get(3)),
                    Long.parseLong(strArr.get(4)),
                    Boolean.parseBoolean(strArr.get(5)),
                    Integer.parseInt(strArr.get(6)),
                    strArr.get(7),
                    strArr.get(8),
                    strArr.get(9)
                    )
            );
        }

        return projectArr;
    }

    @Override
    protected ArrayList<ArrayList<String>> toCSV(ArrayList<Model> alm) {
        ArrayList<ArrayList<String>> csvData = new ArrayList<>();

        // Loop through each Model in alm
        alm.forEach(model -> {
            Project project = (Project) model;

            int officerSlots = project.getOfficerSlots();

            ArrayList<String> row = new ArrayList<>();
            row.add(project.getID());                    // Add project ID (assumed to exist)
            row.add(project.getName());                  // Add name
            row.add(project.getNeighbourhoodID());         // Add neighbourhood
            row.add(String.valueOf(project.getOpeningDate()));           // Add opening date
            row.add(String.valueOf(project.getClosingDate()));           // Add closing date
            row.add(String.valueOf(project.isVisible()));             // Add visibility (boolean)
            row.add(String.valueOf(officerSlots));          // Add number of officer slots
            row.add(project.getManagerID());             // Add manager ID
            row.add(project.getTwoRoomFlatID());         // Add two-room flat ID
            row.add(project.getThreeRoomFlatID());

            csvData.add(row);
        });

        return csvData;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ProjectRepository getInstance() {
        if (instance == null)
            instance = new ProjectRepository();
        return instance;
    }

}
