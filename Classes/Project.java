package Classes;

import java.util.Date;

public class Project {

    private String projectID;
    private String name;
    private String neighbourhood;
    private Date openingDate;
    private Date closingDate;
    private boolean visibility;
    private int officerSlots;
    private String[] officersIDs;
    private String managerID;
    private Flat[] flats;

    public Project(String name, String neighbourhood,
            Date openingDate, Date closingDate,
            int officerSlots, String[] officerIDs, String managerID, Flat[] flats) {
        this.projectID = "12345678";//Generate random
        this.name = name;
        this.neighbourhood = neighbourhood;

        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.visibility = true;

        this.officerSlots = officerSlots;
        this.officersIDs = officerIDs;
        this.managerID = managerID;
        this.flats = flats;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Project ID: ").append(projectID).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Neighbourhood: ").append(neighbourhood).append("\n");
        sb.append("Opening Date: ").append(openingDate).append("\n");
        sb.append("Closing Date: ").append(closingDate).append("\n");
        sb.append("Visibility: ").append(visibility).append("\n");
        sb.append("Officer Slots: ").append(officerSlots).append("\n");
        sb.append("Manager ID: ").append(managerID).append("\n");

        sb.append("Officer IDs: ");
        if (officersIDs != null && officersIDs.length > 0) {
            for (String id : officersIDs) {
                sb.append(id).append(" ");
            }
        } else {
            sb.append("None");
        }
        sb.append("\n");

        sb.append("Flats: ");
        if (flats != null && flats.length > 0) {
            for (Flat flat : flats) {
                sb.append(flat.toString()).append("\n");
            }
        } else {
            sb.append("None");
        }

        return sb.toString();
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

    public Flat[] getFlats() {
        return flats;
    }

    public void setFlats(Flat[] flats) {
        this.flats = flats;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String[] getOfficersIDs() {
        return officersIDs;
    }

    public void setOfficersIDs(String[] officersIDs) {
        this.officersIDs = officersIDs;
    }

    public int getOfficerSlots() {
        return officerSlots;
    }

    public void setOfficerSlots(int officerSlots) {
        this.officerSlots = officerSlots;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
}
