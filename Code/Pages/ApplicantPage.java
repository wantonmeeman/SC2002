package Pages;

public class ApplicantPage {
	public class ApplicantPage {
	    private String applicationID;
	    private String userType;

	    public ApplicantPage(String userType) {
	        this.userType = userType;
	        this.applicationID = generateApplicationID(); // Initialize with a generated ID
	    }

	    public void applyProject(Applicant applicant) {
	        System.out.println("Applicant " + applicant.getName() + " (ID: " + applicant.getId() + 
	                         ") is applying for a project with application ID: " + applicationID);
	        // Implementation for project application logic
	    }

	    public void requestAppWithdraw(Applicant applicant) {
	        System.out.println("Applicant " + applicant.getName() + " is requesting to withdraw application " + applicationID);
	        // Implementation for withdrawal request
	    }

	    public void createEnquiries(Applicant applicant) {
	        System.out.println("Applicant " + applicant.getName() + " is creating an enquiry");
	        // Implementation for enquiry creation
	    }

	    public void viewEnquiries(Applicant applicant) {
	        System.out.println("Applicant " + applicant.getName() + " is viewing enquiries");
	        // Implementation to fetch and display enquiries
	    }

	    public void deleteEnquiries(Applicant applicant) {
	        System.out.println("Applicant " + applicant.getName() + " is deleting an enquiry");
	        // Implementation for enquiry deletion
	    }

	    // Getter for applicationID
	    public String getApplicationID() {
	        return applicationID;
	    }
	}
}

