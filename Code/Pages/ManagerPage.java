package Pages;

import java.util.Scanner;

import Data.Models.HDBManager;

public class ManagerPage {
	private HDBManager manager;
	private Scanner scanner;
	
	public ManagerPage(HDBManager manager) {
		this.manager = manager;
		this.scanner = new Scanner(System.in);
	}
	
	public void displayManagerMenu() {
		boolean logout = false;
		
		while(!logout) {
			ClearCMD.clear();
			
            System.out.println("===== HDB Manager Portal =====");
            System.out.println("Welcome, Manager " + manager.getName() + "!");
            System.out.println("1. Project Listing Management");
            System.out.println("2. Officer Registration Management");
            System.out.println("3. Application Approval");
            System.out.println("4. Logout");
            System.out.print("Please select an option: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        projectListingMenu();
                        break;
                    case 2:
                        officerRegistrationMenu();
                        break;
                    case 3:
                        applicationApprovalMenu();
                        break;
                    case 4:
                        logout = true;
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Invalid option! Please try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
            
            if (!logout) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }

    private void projectListingMenu() {
        boolean back = false;
        
        while (!back) {
            ClearCMD.clear();
            
            System.out.println("===== Project Listing Management =====");
            System.out.println("1. Create New Project Listing");
            System.out.println("2. Edit Existing Project Listing");
            System.out.println("3. Delete Project Listing");
            System.out.println("4. Toggle Project Visibility");
            System.out.println("5. View Created Projects");
            System.out.println("6. Back to Main Menu");
            System.out.print("Please select an option: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        managerCreateListing();
                        break;
                    case 2:
                        managerEditListing();
                        break;
                    case 3:
                        managerDeleteListing();
                        break;
                    case 4:
                        managerToggleProjectVisibility();
                        break;
                    case 5:
                        managerViewCreatedProjects();
                        break;
                    case 6:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option! Please try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
            
            if (!back) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }

    private void officerRegistrationMenu() {
        boolean back = false;
        
        while (!back) {
            ClearCMD.clear();
            
            System.out.println("===== Officer Registration Management =====");
            System.out.println("1. View Pending Officer Registrations");
            System.out.println("2. View Approved Officers");
            System.out.println("3. Approve/Reject Officer Application");
            System.out.println("4. Back to Main Menu");
            System.out.print("Please select an option: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        managerViewPendingOfficerRegistration();
                        break;
                    case 2:
                        managerViewApprovedOfficers();
                        break;
                    case 3:
                        managerApproveOrRejectOfficerApplication();
                        break;
                    case 4:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option! Please try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
            
            if (!back) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }

    private void applicationApprovalMenu() {
        boolean back = false;
        
        while (!back) {
            ClearCMD.clear();
            
            System.out.println("===== Application Approval =====");
            System.out.println("1. Approve/Reject Applications");
            System.out.println("2. Back to Main Menu");
            System.out.print("Please select an option: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        managerApproveOrRejectApplication();
                        break;
                    case 2:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option! Please try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
            
            if (!back) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }

    // Implementation of all manager methods
    private void managerCreateListing() {
        System.out.println("\n=== Create New Project Listing ===");
        // Implementation logic here
        System.out.println("Creating new project listing...");
    }

    private void managerEditListing() {
        System.out.println("\n=== Edit Project Listing ===");
        // Implementation logic here
        System.out.println("Editing project listing...");
    }

    private void managerDeleteListing() {
        System.out.println("\n=== Delete Project Listing ===");
        // Implementation logic here
        System.out.println("Deleting project listing...");
    }

    private void managerToggleProjectVisibility() {
        System.out.println("\n=== Toggle Project Visibility ===");
        // Implementation logic here
        System.out.println("Toggling project visibility...");
    }

    private void managerViewCreatedProjects() {
        System.out.println("\n=== View Created Projects ===");
        // Implementation logic here
        System.out.println("Displaying created projects...");
    }

    private void managerViewPendingOfficerRegistration() {
        System.out.println("\n=== View Pending Officer Registrations ===");
        // Implementation logic here
        System.out.println("Displaying pending officer registrations...");
    }

    private void managerViewApprovedOfficers() {
        System.out.println("\n=== View Approved Officers ===");
        // Implementation logic here
        System.out.println("Displaying approved officers...");
    }

    private void managerApproveOrRejectOfficerApplication() {
        System.out.println("\n=== Approve/Reject Officer Application ===");
        // Implementation logic here
        System.out.println("Processing officer application...");
    }

    private void managerApproveOrRejectApplication() {
        System.out.println("\n=== Approve/Reject Application ===");
        // Implementation logic here
        System.out.println("Processing applicant application...");
    }
}

