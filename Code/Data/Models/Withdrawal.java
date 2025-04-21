package Data.Models;
public class Withdrawal extends Model {
    private String status;

    public Withdrawal(String withdrawalID, String status) {
        super(withdrawalID);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

