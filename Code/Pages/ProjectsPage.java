package Pages;


import Data.Models.Applicant;
import Data.Models.Project;
import Data.Models.User;
import Logic.*;
import Pages.Components.*;
import Util.ClearCMD;

import Exceptions.*;
import Util.Config;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class ProjectsPage {
		public static void start(String userID) {
			Scanner scanner = new Scanner(System.in);
			int input;
			ArrayList<HashMap<String, String>> projList = new ArrayList<>();
			HashMap<Integer,String> inputToIDMap = new HashMap<>();

			try {
				projList = ProjectLogicActions.getInstance().getAllFiltered(userID);
			}catch(ModelNotFoundException e){
				System.out.println(e.toString());
			}

			System.out.println(Seperator.seperate());
			System.out.println(Back.back());

			int x = 2;
			for(HashMap<String,String> hm: projList){
				String threeRoomFlatID = hm.get("ThreeRoomFlatID");
				String twoRoomFlatID = hm.get("TwoRoomFlatID");
				String projectID = hm.get("ID");

                try {
					String projectSimpleView = ProjectView.simpleView(projectID);
					if(threeRoomFlatID != null){
						System.out.println(x+". "+projectSimpleView+" | "+FlatView.simpleView(threeRoomFlatID));
						inputToIDMap.put((x++),threeRoomFlatID);
					}

					if(twoRoomFlatID != null){
						System.out.println(x+". "+projectSimpleView+" | "+FlatView.simpleView(twoRoomFlatID));
						inputToIDMap.put((x++),twoRoomFlatID);
					}

                } catch (ModelNotFoundException e) {
                    throw new RuntimeException(e);
                }
			}

			input = Integer.parseInt(scanner.nextLine());

			ClearCMD.clear();
			if(input != 1){
				detailedFlat(inputToIDMap.get(input),userID);
			}
		}

		public static void detailedFlat(String flatID,String userID){
			String projectID;
			Scanner scanner = new Scanner(System.in);
			int input;

            try {
                projectID = ProjectLogicActions.getInstance().getProjectByFlatID(flatID).get("ID");
            } catch (ModelNotFoundException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Seperator.seperate());

			try {
				System.out.println(ProjectView.detailedView(projectID));
				System.out.println(FlatView.detailedView(flatID));
			}catch (Exception e){
				System.out.println(e);
			}

			System.out.println(Back.back());
			System.out.println("2. Apply");

			input = Integer.parseInt(scanner.nextLine());

			if(input != 1){
                try {
                    ApplicationLogicActions.getInstance().apply(userID,flatID);
                } catch (UnauthorizedActionException e) {
                    throw new RuntimeException(e);
                } catch (ModelAlreadyExistsException e) {
                    throw new RuntimeException(e);
                } catch (ModelNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (RepositoryNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
		}

		public static void adminStart(String userID){
			Scanner scanner = new Scanner(System.in);
			HashMap<Integer,String> inputToIDMap = new HashMap<>();
			int input;
            try {
                HashMap<String,String> uhm = UserLogicActions.getInstance().get(userID);
            } catch (ModelNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Seperator.seperate());
			System.out.println(Back.back());

			ArrayList<HashMap<String,String>> pal = ProjectLogicActions.getInstance().getAll();

			int x = 3;

			String projectsStr = "";
			String activeProjectID = null;
			String activeProjectStr = "";

            try {
                activeProjectID= ProjectLogicActions.getInstance().getActiveProjectByManagerID(userID).get("ID");
            } catch (ModelNotFoundException e) {
                throw new RuntimeException(e);
            }

			for(HashMap<String,String> phm: pal){

				try {
				HashMap<String,String> uhm = UserLogicActions.getInstance().get(phm.get("ManagerID"));

				String name = uhm.get("Name");

				if(activeProjectID.equals(phm.get("ID"))) {
					activeProjectStr = "2. "+ProjectView.simpleView(phm.get("ID"))+" - "+name+" (Active)";
					inputToIDMap.put(2,phm.get("ID"));
				}else{
					projectsStr += x + ". " + ProjectView.simpleView(phm.get("ID"))+" - "+name+"\n";
					inputToIDMap.put(x++,phm.get("ID"));
					}
				} catch (ModelNotFoundException e) {
					throw new RuntimeException(e);
				}


            }

			if(activeProjectStr.equals("")) {
				System.out.println("2. Create new Project");//Either create new project or active project

				System.out.print(projectsStr);

				input = Integer.parseInt(scanner.nextLine());

				if(input == 1){

				}else if(input == 2){
					createProject(userID);
					adminStart(userID);
				}
			}else{
				System.out.println(activeProjectStr);

				System.out.print(projectsStr);

				input = Integer.parseInt(scanner.nextLine());

				if(input == 1){

				}else if(input == 2){
					detailedProject(activeProjectID,userID);
					adminStart(userID);
				}else{
					detailedProject(inputToIDMap.get(input),userID);
					adminStart(userID);
				}
			}


		}

		public static void detailedProject(String projectID, String userID){
			Scanner scanner = new Scanner(System.in);

			int input;

			try {
				HashMap<String,String> phm = ProjectLogicActions.getInstance().get(projectID);
				boolean visible = Boolean.parseBoolean(phm.get("Visibility"));
			System.out.println(Seperator.seperate());
			System.out.println(ProjectView.detailedView(projectID));
			System.out.println("Visible: "+ (visible ? "Yes": "No"));
			System.out.println(Seperator.seperate());
			boolean isManager = userID.equals(phm.get("ManagerID"));

            System.out.println(FlatView.detailedView(phm.get("TwoRoomFlatID")));
			System.out.println(Seperator.seperate());
			System.out.println(FlatView.detailedView(phm.get("ThreeRoomFlatID")));
			System.out.println(Back.back());
			System.out.println("2. Officers");
			System.out.println("3. Applicants");

				if(isManager){
					System.out.println("4. Edit Project");
				}

			input = Integer.parseInt(scanner.nextLine());

				if(input == 1){

				}else if(input == 2){
					viewOfficers(projectID,isManager);
					detailedProject(projectID, userID);
				}else if(input == 3){
					viewApplicants(projectID,isManager);
					detailedProject(projectID, userID);
				}else if(input == 4 && isManager){
					editProject(projectID);
					detailedProject(projectID, userID);
				}

			} catch (ModelNotFoundException e) {
				throw new RuntimeException(e);
			}

		}

		public static void editProject(String projectID){
			//Can only edit Name, neighbourhood, Opening to ending
			Scanner scanner = new Scanner(System.in);
			int input;

			System.out.println(Seperator.seperate());
			System.out.println(Back.back());
			System.out.println("2. Toggle Visibility");
			System.out.println("3. Edit Project Name");
			System.out.println("4. Edit Project Neighbourhood");
			System.out.println("5. Edit Project Opening and Ending Date");

			try {
			input = Integer.parseInt(scanner.nextLine());
			if(input == 1){

			}else{
				if(input == 2){
					ProjectLogicActions.getInstance().toggle(projectID);
                }else if(input == 3){
					editName(projectID);
				}else if(input == 4){
					editNeighbourhood(projectID);
				}else if(input == 5){
					editOpeningClosing(projectID);
				}
				editProject(projectID);
			}
			} catch (ModelNotFoundException e) {
				throw new RuntimeException(e);
			}
		}

	public static void editName(String projectID){
		try {
			Scanner scanner = new Scanner(System.in);
			HashMap<String,String> phm = ProjectLogicActions.getInstance().get(projectID);

			System.out.println("Old Name: "+phm.get("Name"));
			System.out.println("New Name: ");

			String name = scanner.nextLine();

			ProjectLogicActions.getInstance().editName(projectID,name);
		} catch (ModelNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

		public static void editNeighbourhood(String projectID){
            try {
				Scanner scanner = new Scanner(System.in);
                HashMap<String,String> phm = ProjectLogicActions.getInstance().get(projectID);

				System.out.println("Old Neighbourhood: "+phm.get("Neighbourhood"));
				System.out.println("New Neighbourhood: ");

				String neighbourhood = scanner.nextLine();

				ProjectLogicActions.getInstance().editNeighbourhood(projectID,neighbourhood);
            } catch (ModelNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

		public static void editOpeningClosing(String projectID){
			try {
				Scanner scanner = new Scanner(System.in);
				SimpleDateFormat formatter = Config.DATE_FORMAT;

				HashMap<String,String> phm = ProjectLogicActions.getInstance().get(projectID);
				Date dateObject = new Date(Long.parseLong(phm.get("OpeningDate"))*1000L);
				String formattedDateTime = formatter.format(dateObject);

				System.out.println("Old Opening(YYYY-MM-DD): "+formattedDateTime);
				System.out.println("New Opening(YYYY-MM-DD): ");

				String openingDateString = scanner.nextLine();

				dateObject = new Date(Long.parseLong(phm.get("ClosingDate"))*1000L);
				formattedDateTime = formatter.format(dateObject);

				System.out.println("Old Closing(YYYY-MM-DD): "+formattedDateTime);
				System.out.println("New Closing(YYYY-MM-DD): ");

				String closingDateString = scanner.nextLine();

				ProjectLogicActions.getInstance().editOpeningClosing(projectID,openingDateString,closingDateString);
			} catch (ModelNotFoundException e) {
				throw new RuntimeException(e);
			}
		}

		public static void viewOfficers(String projectID,boolean isManager){
			try {
				Scanner scanner = new Scanner(System.in);
				int input;

				String[] officerArr = ProjectLogicActions.getInstance().get(projectID).get("OfficerIDs").split(",");
				ArrayList<String> registrationIDArr = new ArrayList<>();

				System.out.println(Seperator.seperate());
				int x = 2;

				System.out.println(Back.back());

				for(String officerID: officerArr){
					String Name = UserLogicActions.getInstance().get(officerID).get("Name");
					ArrayList<HashMap<String,String>> ral= RegistrationLogicActions.getInstance().getByOfficerID(officerID);

					for(HashMap<String,String> rhm:ral){
						String rProjectID = rhm.get("ProjectID");
						if(projectID.equals(rProjectID)) {
							registrationIDArr.add(rhm.get("ID"));
							System.out.println((x++) + ". " + Name + " - " + rhm.get("Status"));
							break;
						}
					}
				}
				input = Integer.parseInt(scanner.nextLine());
				if(input == 1){

				}else{
					detailedOfficer(registrationIDArr.get(input-2),isManager);
					viewOfficers(projectID,isManager);
				}
			} catch (ModelNotFoundException e) {
				throw new RuntimeException(e);
			}
		}

		public static void detailedOfficer(String registrationID, boolean isManager){
			try {
				Scanner scanner = new Scanner(System.in);
				int input;
				HashMap<String,String> rhm = RegistrationLogicActions.getInstance().get(registrationID);
				String officerID = rhm.get("OfficerID");
				String status = rhm.get("Status");

				System.out.println(Seperator.seperate());
				System.out.println(UserView.detailedView(officerID));
				System.out.println(RegistrationView.detailedView(registrationID));
				System.out.println(Back.back());

				boolean canApprove = status.equals("Pending") && isManager;

				if(canApprove){
					System.out.println("2. Approve");
				}

				input = Integer.parseInt(scanner.nextLine());
				if(input == 1){

				}else if(input == 2 && canApprove){
					RegistrationLogicActions.getInstance().approve(registrationID);
				}

			} catch (ModelNotFoundException e) {
				throw new RuntimeException(e);
			}
		}

		public static void viewApplicants(String projectID,boolean isManager){
            try {
				Scanner scanner = new Scanner(System.in);
				int input;

				ArrayList<HashMap<String,String>> aal = ApplicationLogicActions.getInstance().getApplicationsByProjectID(projectID);

				System.out.println(Seperator.seperate());
				int x = 2;

				System.out.println(Back.back());

				for(HashMap<String,String> hm: aal){
					String Name = UserLogicActions.getInstance().get(hm.get("UserID")).get("Name");

					System.out.println((x++)+". "+ Name +" - "+hm.get("Status"));
				}
				input = Integer.parseInt(scanner.nextLine());
				if(input == 1){

				}else{
					detailedApplicant(aal.get(input-2).get("ID"),isManager);
					viewApplicants(projectID,isManager);
				}
            } catch (ModelNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

		public static void detailedApplicant(String applicationID,boolean isManager){
            try {
				Scanner scanner = new Scanner(System.in);
				int input;

				HashMap<String,String> ahm = ApplicationLogicActions.getInstance().get(applicationID);
				String flatID = ahm.get("FlatID");
				HashMap<String,String> fhm = FlatLogicActions.getInstance().get(flatID);

				String status = ahm.get("Status");
				System.out.println(Seperator.seperate());
                System.out.println(UserView.detailedView(ahm.get("UserID")));
				System.out.println(ApplicationView.detailedView(applicationID));
				System.out.println(FlatView.detailedView(flatID));
				System.out.println(Back.back());

				boolean canApprove = status.equals("Pending") && Integer.parseInt(fhm.get("TotalUnits")) > 0 && isManager;

				if(canApprove){
					System.out.println("2. Approve");
				}

				input = Integer.parseInt(scanner.nextLine());
				if(input == 1){

				}else if(input == 2 && canApprove){
					ApplicationLogicActions.getInstance().approve(applicationID);
				}

            } catch (ModelNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

		public static void createProject(String userID){
			Scanner scanner = new Scanner(System.in);

			HashMap<String,String> phm = new HashMap<>();
			HashMap<String,String> twoFlathm = new HashMap<>();
			twoFlathm.put("Type","2Room");
			HashMap<String,String> threeFlathm = new HashMap<>();
			threeFlathm.put("Type","3Room");

			phm.put("ManagerID",userID);
			phm.put("OfficerIDs","");
			phm.put("Visibility","true");
			//Validation

			System.out.println("Project Name:");
			phm.put("Name",scanner.nextLine());

			System.out.println("Project Neighbourhood: ");
			phm.put("Neighbourhood", scanner.nextLine());

			System.out.println("Opening Date(YYYY-MM-DD): ");

			String openingDateString = String.valueOf(
					java.time.LocalDate.parse(scanner.nextLine())
							.atStartOfDay()
							.toEpochSecond(java.time.ZoneOffset.UTC)
			);

			phm.put("OpeningDate",openingDateString);

			System.out.println("Closing Date(YYYY-MM-DD): ");

			String closingDateString = String.valueOf(
					java.time.LocalDate.parse(scanner.nextLine())
							.atStartOfDay()
							.toEpochSecond(java.time.ZoneOffset.UTC)
			);

			phm.put("ClosingDate",closingDateString);

			System.out.println("Officer Slots(Max 10):");
			String officerSlots = scanner.nextLine();
			phm.put("OfficerSlots",officerSlots);

			//2 Room Flat
			System.out.println("2 Room Flat Units: ");
			twoFlathm.put("TotalUnits",scanner.nextLine());

			System.out.println("2 Room Flat Price: ");
			twoFlathm.put("Price",scanner.nextLine());

			phm.put("TwoRoomFlatID",FlatLogicActions.getInstance().create(twoFlathm));
            //3 Room Flat
			System.out.println("3 Room Flat Units: ");
			threeFlathm.put("TotalUnits",scanner.nextLine());

			System.out.println("3 Room Flat Price: ");
			threeFlathm.put("Price",scanner.nextLine());

			phm.put("ThreeRoomFlatID",FlatLogicActions.getInstance().create(threeFlathm));

			ProjectLogicActions.getInstance().create(phm);
        }
}


