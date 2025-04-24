package Data.Models;

/**
 * The type Withdrawal.
 */
public class Withdrawal extends Model {
    private String status;

    /**
     * Instantiates a new Withdrawal.
     *
     * @param withdrawalID the withdrawal id
     * @param status       the status
     */
    public Withdrawal(String withdrawalID, String status) {
        super(withdrawalID);
        this.status = status;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}

