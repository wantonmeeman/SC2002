
import java.util.Date;
import java.util.List;

public class Project {
    private String projectID;
    private String name;
    private String neighbourhood;
    private Date openingDate;
    private Date closingDate;
    private boolean visibility;
    private int officerSlots;
    private List<TypeFlat> typeFlats;

    public Project(String projectID, String name, String neighbourhood, Date openingDate, Date closingDate,
                   boolean visibility, int officerSlots, List<TypeFlat> typeFlats) {
        this.projectID = projectID;
        this.name = name;
        this.neighbourhood = neighbourhood;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.visibility = visibility;
        this.officerSlots = officerSlots;
        this.typeFlats = typeFlats;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public void toggleVisibility() {
        this.visibility = !this.visibility;
    }

    public int getOfficerSlots() {
        return officerSlots;
    }

    public void setOfficerSlots(int officerSlots) {
        this.officerSlots = officerSlots;
    }

    public List<TypeFlat> getTypeFlats() {
        return typeFlats;
    }

    public void setTypeFlats(List<TypeFlat> typeFlats) {
        this.typeFlats = typeFlats;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectID='" + projectID + '\'' +
                ", name='" + name + '\'' +
                ", neighbourhood='" + neighbourhood + '\'' +
                ", openingDate=" + openingDate +
                ", closingDate=" + closingDate +
                ", visibility=" + visibility +
                ", officerSlots=" + officerSlots +
                ", typeFlats=" + typeFlats +
                '}';
    }
}
