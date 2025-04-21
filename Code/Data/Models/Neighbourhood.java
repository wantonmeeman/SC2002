package Data.Models;

public class Neighbourhood extends Model{
    private String name;

    public Neighbourhood(String ID, String name) {
        super(ID);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
