package Data.Models;
import java.util.Date;

/**
 * The type Enquiry.
 */
public class Enquiry extends Model{
    private String projectID;
    private String userID;
    private String message;
    private String reply;
    private long timestamp;

    /**
     * Instantiates a new Enquiry.
     *
     * @param EnquiryID the enquiry id
     * @param projectID the project id
     * @param userID    the user id
     * @param message   the message
     * @param timestamp the timestamp
     */
    public Enquiry(String EnquiryID, String projectID, String userID, String message, long timestamp) {
        super(EnquiryID);
        this.projectID = projectID;
        this.userID = userID;
        this.message = message;
        this.reply = null;
        this.timestamp = timestamp;
    }

    /**
     * Instantiates a new Enquiry.
     *
     * @param EnquiryID the enquiry id
     * @param projectID the project id
     * @param userID    the user id
     * @param message   the message
     * @param reply     the reply
     * @param timestamp the timestamp
     */
    public Enquiry(String EnquiryID, String projectID, String userID, String message, String reply, long timestamp) {
        super(EnquiryID);
        this.projectID = projectID;
        this.userID = userID;
        this.message = message;
        this.reply = reply;
        this.timestamp = timestamp;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets timestamp.
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets reply.
     *
     * @return the reply
     */
    public String getReply() {
        return reply;
    }

    /**
     * Sets reply.
     *
     * @param reply the reply
     */
    public void setReply(String reply) {
        this.reply = reply;
    }

    /**
     * Gets project id.
     *
     * @return the project id
     */
    public String getProjectID() {
        return projectID;
    }

    /**
     * Sets project id.
     *
     * @param projectID the project id
     */
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets user id.
     *
     * @param userID the user id
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

}