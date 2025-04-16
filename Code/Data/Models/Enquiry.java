package Data.Models;
import java.util.Date;

public class Enquiry extends Model{
    private String projectID;
    private String userID;
    private String message;
    private String reply;
    private Date timestamp;

    public Enquiry(String projectID, String userID, String message, Date timestamp) {
        super("Test");
        this.projectID = projectID;
        this.userID = userID;
        this.message = message;
        this.reply = null;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}