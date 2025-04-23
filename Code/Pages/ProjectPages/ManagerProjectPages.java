package Pages.ProjectPages;

import Data.Models.Model;
import Data.Models.User;
import Exceptions.ModelNotFoundException;
import Logic.*;
import Pages.Components.*;
import Pages.FilterSettingPages.ApplicantFilterSettingsPage;
import Pages.FilterSettingPages.ManagerProjectFilterSettingsPage;
import Pages.NeighbourhoodPages.NeighbourhoodPage;
import Util.ClearCMD;
import Util.Config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class ManagerProjectPages {
    public static void start(String userID){
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer,String> inputToIDMap = new HashMap<>();
        int input;

        System.out.println(Seperator.seperate());
        System.out.println(Back.back());
        System.out.println("2. Neighbourhoods");
        System.out.println("3. Filter Settings");

        ArrayList<HashMap<String,String>> pal = new ArrayList<>();
        try {
            pal = ProjectLogicActions.getInstance().getAllManagerFiltered(userID);
        } catch (ModelNotFoundException e) {
            System.out.println("Could not find user");
        }

        int x = 5;

        String projectsStr = "";
        String activeProjectStr = "";
        String activeProjectID = null;

        try {
            activeProjectID = ProjectLogicActions.getInstance().getActiveProjectByManagerID(userID).get("ID");
        } catch (ModelNotFoundException e) {
            //This just means that there is not active project
        }

        for(HashMap<String,String> phm: pal){
            try {
                HashMap<String,String> uhm = UserLogicActions.getInstance().get(phm.get("ManagerID"));

                String name = uhm.get("Name");
                if(activeProjectID == null || !activeProjectID.equals(phm.get("ID"))) {
                    projectsStr += x + ". " + ProjectView.simpleView(phm.get("ID")) + " - " + name + "\n";

                    inputToIDMap.put(x++,phm.get("ID"));
                }
            } catch (ModelNotFoundException e) {
                System.out.println("Could not find User");
            }
        }

        if(activeProjectID != null) {
            try{
                String activeName = UserLogicActions.getInstance().get(userID).get("Name");
                activeProjectStr = "4. "+ ProjectView.simpleView(activeProjectID)+" - "+activeName+" (Active)";
                inputToIDMap.put(4,activeProjectID);
            }catch(ModelNotFoundException e){
                System.out.println("Unable to find active project details");
            }

            System.out.println(activeProjectStr);

            System.out.print(projectsStr);

            try {
                input = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                input = -1;//pass to default handler
            }
            ClearCMD.clear();

            if(input == 1){
                return;
            }else if(input == 2){
                //Neighbourhoods
                NeighbourhoodPage.start();
            }else if(input == 3){
                //Filter Settings
                ManagerProjectFilterSettingsPage.start(userID);
            }else if(input > 0 && input < x){
                detailedProject(inputToIDMap.get(input),userID);
            }else{
                System.out.println("Invalid Input");
            }
            start(userID);
        }else{
            System.out.println("4. Create new Project");//Either create new project or active project

            System.out.print(projectsStr);

            try {
                input = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                input = -1;//pass to default handler
            }
            ClearCMD.clear();

            if(input == 1){
                return;
            }else if(input == 2){
                //Neighbourhoods
                NeighbourhoodPage.start();
            }else if(input == 3){
                //Filter Settings
                ManagerProjectFilterSettingsPage.start(userID);
            }else if(input == 4){
                createProject(userID);
            }else if(input > 0 && input < x){
                detailedProject(inputToIDMap.get(input),userID);
            }
            start(userID);
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
                System.out.println("5. Delete Project");
            }

            try {
                input = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                input = -1;//pass to default handler
            }
            ClearCMD.clear();

            if(input == 1){
                return;
            }else if(input == 2){
                viewOfficers(projectID,isManager);
            }else if(input == 3){
                viewApplicants(projectID,isManager,null);
            }else if(input == 4 && isManager){
                editProject(projectID);
            }else if(input == 5 && isManager) {
                deleteProject(projectID);
                return;
            } else{
                System.out.println("Invalid Input");
            }
            detailedProject(projectID, userID);
        } catch (ModelNotFoundException e) {
            System.out.println("Could not find object");
        }
    }
    public static void deleteProject(String projectID){
        try {
            ProjectLogicActions.getInstance().delete(projectID);
        }catch (ModelNotFoundException e){
            System.out.println("Could not delete");
        }
    }

    public static String createProjectName(Scanner scanner){
        System.out.println("Project Name:");
        return scanner.nextLine();
    }

    public static String createProjectNeighbourhood(Scanner scanner){
        System.out.println("Project Neighbourhood: ");
        int input;

        ArrayList<HashMap<String,String>> nal = NeighbourhoodLogicActions.getInstance().getAll();

        int x = 1;
        for(HashMap<String,String> nhm:nal){
            System.out.println((x++) + ". "+ NeighbourhoodView.simpleView(nhm.get("ID")));
        }

        try {
            input = Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException e){
            input = -1;//pass to default handler
        }

        if(input < x) {
            return nal.get(input - 1).get("ID");
        }else{
            System.out.println("Invalid Input");
            return createProjectNeighbourhood(scanner);
        }
    }

    public static String createProjectOpening(Scanner scanner){
        System.out.println("Opening Date(YYYY-MM-DD): ");
        try {
            return String.valueOf(
                    java.time.LocalDate.parse(scanner.nextLine())
                            .atStartOfDay()
                            .toEpochSecond(java.time.ZoneOffset.UTC)
            );
        }catch(Exception e){
            System.out.println("Invalid Input");
            return createProjectOpening(scanner);
        }
    }

    public static String createProjectClosing(Scanner scanner){
        System.out.println("Closing Date(YYYY-MM-DD): ");
        try {
        return String.valueOf(
                java.time.LocalDate.parse(scanner.nextLine())
                        .atStartOfDay()
                        .toEpochSecond(java.time.ZoneOffset.UTC)
        );        }catch(Exception e){
        System.out.println("Invalid Input");
        return createProjectClosing(scanner);
    }
    }

    public static String createProjectOfficerSlots(Scanner scanner){
        System.out.println("Officer Slots(Max 10):");
        String officerSlots = scanner.nextLine();
        int officerSlotsInt;
        try {
            officerSlotsInt = Integer.parseInt(officerSlots);
        }catch(NumberFormatException e){
            officerSlotsInt = -1;
        }
            if (officerSlotsInt >= 1 && officerSlotsInt <= 10){
                return officerSlots;
            }else{
                System.out.println("Invalid Input");
                return createProjectOfficerSlots(scanner);
            }

    }

    public static String createTwoRoomFlat(Scanner scanner){
        HashMap<String,String> twoFlathm = new HashMap<>();
        twoFlathm.put("Type","2Room");

        System.out.println("2 Room Flat Units: ");

        String units = scanner.nextLine();
        int unitsInt;
        try {
            unitsInt = Integer.parseInt(units);
        }catch(NumberFormatException e){
            unitsInt = -1;
        }

        if(unitsInt >= 0){
            twoFlathm.put("TotalUnits",units);
        }else{
            System.out.println("Invalid Input");
            return createTwoRoomFlat(scanner);
        }

        System.out.println("2 Room Flat Price: ");

        String price = scanner.nextLine();
        float priceFloat;
        try{
            priceFloat = Float.parseFloat(price);
        }catch(NumberFormatException e){
            priceFloat = -1;
        }

        if(priceFloat >= 0){
            twoFlathm.put("Price", price);
        }else{
            System.out.println("Invalid Input");
            return createTwoRoomFlat(scanner);
        }

        return FlatLogicActions.getInstance().create(twoFlathm);
    }

    public static String createThreeRoomFlat(Scanner scanner){
        HashMap<String,String> threeFlathm = new HashMap<>();
        threeFlathm.put("Type","3Room");

        System.out.println("3 Room Flat Units: ");

        String units = scanner.nextLine();
        int unitsInt;
        try {
            unitsInt = Integer.parseInt(units);
        }catch(NumberFormatException e){
            unitsInt = -1;
        }

        if(unitsInt >= 0){
            threeFlathm.put("TotalUnits",units);
        }else{
            System.out.println("Invalid Input");
            return createThreeRoomFlat(scanner);
        }

        System.out.println("3 Room Flat Price: ");

        String price = scanner.nextLine();
        float priceFloat;
        try{
            priceFloat = Float.parseFloat(price);
        }catch(NumberFormatException e){
            priceFloat = -1;
        }

        if(priceFloat >= 0){
            threeFlathm.put("Price", price);
        }else{
            System.out.println("Invalid Input");
            return createThreeRoomFlat(scanner);
        }

        return FlatLogicActions.getInstance().create(threeFlathm);
    }

    public static void createProject(String userID){
        Scanner scanner = new Scanner(System.in);

        HashMap<String,String> phm = new HashMap<>();

        phm.put("ManagerID",userID);
        phm.put("OfficerIDs","");
        phm.put("Visibility","true");
        //Validation

        phm.put("Name",createProjectName(scanner));

        phm.put("NeighbourhoodID",createProjectNeighbourhood(scanner));

        phm.put("OpeningDate",createProjectOpening(scanner));

        phm.put("ClosingDate",createProjectClosing(scanner));

        phm.put("OfficerSlots",createProjectOfficerSlots(scanner));
        //2 Room Flat
        phm.put("TwoRoomFlatID",createTwoRoomFlat(scanner));
        //3 Room Flat
        phm.put("ThreeRoomFlatID",createThreeRoomFlat(scanner));

        ProjectLogicActions.getInstance().create(phm);
    }

    public static void viewApplicants(String projectID,boolean isManager, HashMap<String,String> ashm){
        try {

            if(ashm == null){
                ashm = new HashMap<String,String>();
                ashm.put("Marital",null);
                ashm.put("FlatType",null);
            }

            Scanner scanner = new Scanner(System.in);
            int input;

            ArrayList<HashMap<String,String>> aal = ApplicationLogicActions.getInstance().getFilteredApplicationsByProjectID(projectID,ashm);

            System.out.println(Seperator.seperate());
            int x = 4;

            System.out.println(Back.back());
            System.out.println("2. Filter Settings");
            System.out.println("3. Print Receipt");

            for(HashMap<String,String> hm: aal){
                String userID = hm.get("UserID");
                String applicationID = hm.get("ID");
                String withdrawalStatus = "";

                try {
                    withdrawalStatus += " - " + WithdrawalLogicActions.getInstance().get(applicationID).get("Status");
                }catch (ModelNotFoundException e){

                }
                System.out.println((x++) + ". " + UserView.applicantView(userID,applicationID) + withdrawalStatus);

            }
            try {
                input = Integer.parseInt(scanner.nextLine());
                ClearCMD.clear();
            }catch(NumberFormatException e){
                input = -1;//pass to default handler
            }
            if(input == 1){
                return;
            }else if(input == 2){
                ApplicantFilterSettingsPage.start(ashm);
            }else if(input == 3){
                printReceipt(projectID,ashm);
            }else if(
                    input > 0 && input < x){
                detailedApplicant(aal.get(input-4).get("ID"),isManager);
            }else{
                System.out.println("Invalid Input");
            }
            viewApplicants(projectID,isManager,ashm);
        } catch (ModelNotFoundException e) {
            System.out.println("Could not find object");
        }
    }

    public static void printReceipt(String projectID, HashMap<String,String> ashm){
        Scanner scanner = new Scanner(System.in);
        int input;

        try {
            ArrayList<HashMap<String,String>> aal = ApplicationLogicActions.getInstance().getFilteredApplicationsByProjectID(projectID,ashm);
            System.out.println(ProjectView.detailedView(projectID));
            System.out.println(Seperator.seperate());

            for(HashMap<String,String> hm: aal){
                System.out.println(UserView.detailedView(hm.get("UserID")));
                System.out.println(ApplicationView.detailedView(hm.get("ID")));
                System.out.println(FlatView.applicantView(hm.get("FlatID")));
                System.out.println(Seperator.seperate());
            }
        } catch (ModelNotFoundException e) {
            System.out.println("Could not find object");
        }

        System.out.println(Back.back());

        try {
            input = Integer.parseInt(scanner.nextLine());
            ClearCMD.clear();
        }catch(NumberFormatException e){
            input = -1;//pass to default handler
        }
        if(input == 1){

        }else{
            System.out.println("Invalid Input");
            printReceipt(projectID,ashm);
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
            boolean canApproveWithdrawal = false;
            try{
                HashMap<String,String> whm = WithdrawalLogicActions.getInstance().get(applicationID);
                System.out.println(WithdrawalView.detailedView(applicationID));
                String approvalStatus =  whm.get("Status");
                canApproveWithdrawal = approvalStatus.equals("Pending");
            }catch(ModelNotFoundException e){
                //This means that no withdrawal was found
            }

            System.out.println(FlatView.detailedView(flatID));
            System.out.println(Back.back());

            boolean canApproveApplication = status.equals("Pending") && Integer.parseInt(fhm.get("TotalUnits")) > 0 && isManager;

            int x = 2;

            if(canApproveApplication){
                System.out.println((x++)+". Approve Application");
                System.out.println((x++)+". Reject Application");
            }

            if(canApproveWithdrawal){
                System.out.println((x++)+". Approve Withdrawal");
                System.out.println((x++)+". Reject Withdrawal");
            }

            try {
                input = Integer.parseInt(scanner.nextLine());
                ClearCMD.clear();
            }catch(NumberFormatException e){
                input = -1;//pass to default handler
            }

            if(input == 1){

            }else{
                if(canApproveApplication && canApproveWithdrawal){
                    if(input == 2){
                        ApplicationLogicActions.getInstance().approve(applicationID);
                    }else if(input == 3){
                        ApplicationLogicActions.getInstance().reject(applicationID);
                    }else if(input == 4){
                        WithdrawalLogicActions.getInstance().approve(applicationID);
                    }else if(input == 5){
                        WithdrawalLogicActions.getInstance().reject(applicationID);
                    }else{
                        System.out.println("Invalid Input");
                    }
                }else if(canApproveApplication){
                    if(input == 2){
                        ApplicationLogicActions.getInstance().approve(applicationID);
                    }else if(input == 3){
                        ApplicationLogicActions.getInstance().reject(applicationID);
                    }else{
                        System.out.println("Invalid Input");
                    }
                } else if(canApproveWithdrawal){
                    if(input == 2){
                        WithdrawalLogicActions.getInstance().approve(applicationID);
                    }else if(input == 3){
                        WithdrawalLogicActions.getInstance().reject(applicationID);
                    }else{
                        System.out.println("Invalid Input");
                    }
                }
                detailedApplicant(applicationID,isManager);
            }

        } catch (ModelNotFoundException e) {
            System.out.println("Could not find object");
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
        System.out.println("6. Edit 2 Room Price");
        System.out.println("7. Edit 3 Room Price");

        try {
            try {
                input = Integer.parseInt(scanner.nextLine());
                ClearCMD.clear();
            }catch(NumberFormatException e){
                input = -1;//pass to default handler
            }
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
                }else if(input == 6){
                    HashMap<String,String> phm = ProjectLogicActions.getInstance().get(projectID);
                    editFlat(phm.get("TwoRoomFlatID"));
                }else if(input == 7){
                    HashMap<String,String> phm = ProjectLogicActions.getInstance().get(projectID);
                    editFlat(phm.get("ThreeRoomFlatID"));
                }else{
                    System.out.println("Invalid Input");
                }
                editProject(projectID);
            }
        } catch (ModelNotFoundException e) {
            System.out.println("Could not find object");
        }
    }

    public static void editFlat(String flatID){
        System.out.println(Seperator.seperate());
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Price: ");

            String priceStr = scanner.nextLine();

            FlatLogicActions.getInstance().editPrice(flatID, Float.parseFloat(priceStr));
        } catch (ModelNotFoundException e) {
            System.out.println("Could not find flat");
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
            System.out.println("Could not find flat");
        }
    }

    public static void editNeighbourhood(String projectID){
        try {
            Scanner scanner = new Scanner(System.in);
            int input;

            System.out.println("Neighbourhood: ");

            ArrayList<HashMap<String,String>> nal = NeighbourhoodLogicActions.getInstance().getAll();

            int x = 1;
            for(HashMap<String,String> nhm:nal){
                System.out.println((x++) + ". "+ NeighbourhoodView.simpleView(nhm.get("ID")));
            }

            try {
                input = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                input = -1;//pass to default handler
            }

            ProjectLogicActions.getInstance().editNeighbourhood(projectID,nal.get(input-1).get("ID"));
        } catch (ModelNotFoundException e) {
            System.out.println("Could not find flat");
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
            System.out.println("Could not find flat");
        }
    }

    public static void viewOfficers(String projectID,boolean isManager){
        try {
            Scanner scanner = new Scanner(System.in);
            int input;

            String[] officerArr = ProjectLogicActions.getInstance().get(projectID).get("OfficerIDs").split(",");
            ArrayList<String> registrationIDArr = new ArrayList<>();

            System.out.println(Seperator.seperate());
            System.out.println(Back.back());
            int x = 2;

            for(String officerID: officerArr){
                if(!officerID.equals("")) {
                    String Name = UserLogicActions.getInstance().get(officerID).get("Name");
                    ArrayList<HashMap<String, String>> ral = RegistrationLogicActions.getInstance().getByOfficerID(officerID);

                    for (HashMap<String, String> rhm : ral) {
                        String rProjectID = rhm.get("ProjectID");
                        if (projectID.equals(rProjectID)) {
                            registrationIDArr.add(rhm.get("ID"));
                            System.out.println((x++) + ". " + Name + " - " + rhm.get("Status"));
                            break;
                        }
                    }
                }
            }
            try {
                input = Integer.parseInt(scanner.nextLine());

                ClearCMD.clear();
            }catch(NumberFormatException e){
                input = -1;//pass to default handler
            }
            if(input == 1){
                return;
            }else if(
                    input > 0 && input < x){
                detailedOfficer(registrationIDArr.get(input-2),isManager);
            }else{
                System.out.println("Invalid Input");
            }
            viewOfficers(projectID,isManager);
        } catch (ModelNotFoundException e) {
            System.out.println("Could not find object");
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
                System.out.println("3. Reject");
            }

            try {
                input = Integer.parseInt(scanner.nextLine());

                ClearCMD.clear();
            }catch(NumberFormatException e){
                input = -1;//pass to default handler
            }
            if(input == 1){
                return;
            }else if(input == 2 && canApprove){
                RegistrationLogicActions.getInstance().approve(registrationID);
            }else if(input == 3&& canApprove){
                RegistrationLogicActions.getInstance().reject(registrationID);
            }else{
                System.out.println("Invalid Input");
            }
            detailedOfficer(registrationID,isManager);

        } catch (ModelNotFoundException e) {
            System.out.println("Could not find object");
        }
    }
}
