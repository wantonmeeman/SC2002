package Classes;
import java.util.Date;

public class Enquiry {
	private String enquiryID;
	private String projectID;
	private String userID;
	private String message;
	private String reply;
	private Date timestamp;
	
	public Enquiry(String projectID, String userID, String message, Date timestamp) {
		this.enquiryID = "";
		this.projectID = projectID;
		this.userID = userID;
		this.message = message;
		this.reply = null;
		this.timestamp = timestamp;
	}
	
	public String getEnquiryID() {
		return enquiryID;
	}
	
	public void setEnquiryID(String enquiryID) {
		this.enquiryID = enquiryID;
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

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();

		sb.append("Enquiry ID: ").append(this.getEnquiryID()).append("\n");

		sb.append("Message: ").append(this.getMessage()).append("\n");

		if (this.getReply() != null) {
			sb.append("Reply: ").append(this.getReply()).append("\n");
		}

		return sb.toString();
	}
}
