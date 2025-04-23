package Pages.EnquiryPages;

import Exceptions.ModelNotFoundException;
import Logic.EnquiryLogicActions;
import Logic.ProjectLogicActions;
import Pages.Components.Back;
import Pages.Components.EnquiryView;
import Pages.Components.Seperator;
import Util.ClearCMD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ApplicantEnquiryPage {
    public static void start(String userID){
        Scanner scanner = new Scanner(System.in);
        int input;
        ArrayList<HashMap<String, String>> enquiries = EnquiryLogicActions.getInstance().getEnquiriesByUserID(userID);

        System.out.println(Seperator.seperate());
        System.out.println(Back.back());
        System.out.println("2. Create new Enquiry");

        int x = 3;
        for(HashMap<String,String> ehm: enquiries){
            try {
                System.out.println((x++)+". "+ EnquiryView.simpleView(ehm.get("ID")));
            }catch(ModelNotFoundException e){
                System.out.println("Couldn't get an enquiry's information, ID:"+ehm.get("ID"));
            }
        }

        try {
            input = Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException e){
            input = -1;//pass to default handler
        }
        ClearCMD.clear();
        if (input == 1) {
            // Do nothing
            return;
        } else if (input == 2) {
            createEnquiry(userID);
        } else if(x > 0 && input < x){
            detailedEnquiry(enquiries.get(input - 3).get("ID"));
        }   else{
            System.out.println("Invalid Input");
        }
        start(userID);
    }

    private static void createEnquiry(String userID){
        HashMap<String,String> hm = new HashMap<String,String>();

        hm.put("ProjectID",chooseProject());
        hm.put("Message",writeMessage());
        hm.put("UserID", userID);

        EnquiryLogicActions.getInstance().create(hm);
        ClearCMD.clear();
    }

    private static String chooseProject(){
        Scanner scanner = new Scanner(System.in);
        int input;

        System.out.println(Seperator.seperate());
        System.out.println("Choose which project you wish to enquire about:");

        ArrayList<HashMap<String,String>> projList = ProjectLogicActions.getInstance().getAll();

        int x = 1;
        for(HashMap<String,String> hm: projList){
            System.out.println((x++) +". "+hm.get("Name"));
        }

        try {
            input = Integer.parseInt(scanner.nextLine());
        }catch (Exception e){
            input = -1;
        }

        if(x > 0 && input < x){
            return projList.get(input-1).get("ID");
        }else{
            System.out.println("Invalid Input");
            chooseProject();
        }
        return null;
    }

    private static String writeMessage(){
        Scanner scanner = new Scanner(System.in);

        System.out.println(Seperator.seperate());

        System.out.println("Write your enquiry:");

        return scanner.nextLine();
    }

    private static void detailedEnquiry(String enquiryID){
        Scanner scanner = new Scanner(System.in);
        int input;
        try {

            System.out.println(Seperator.seperate());
            System.out.println(EnquiryView.detailedView(enquiryID));

            System.out.println(Back.back());
            System.out.println("2. Edit");
            System.out.println("3. Delete");

            try {
                input = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                input = -1;//pass to default handler
            }

            ClearCMD.clear();

            if (input == 1) {
                // Do nothing
            } else if (input == 2) {
                editEnquiry(enquiryID);
                detailedEnquiry(enquiryID);
            } else if (input == 3) {
                deleteEnquiry(enquiryID);
            } else {
                System.out.println("Invalid Input");
                detailedEnquiry(enquiryID);
            }
        }catch(ModelNotFoundException e){
            System.out.println("Could not get Enquiry Information");
        }
    }

    private static void editEnquiry(String enquiryID){
        try {
            Scanner scanner = new Scanner(System.in);

            HashMap<String,String> enquiry = EnquiryLogicActions.getInstance().get(enquiryID);
            String message = enquiry.get("Message");

            System.out.println(Seperator.seperate());
            System.out.println("Old Message:");
            System.out.println(message);
            System.out.println("New Message:");
            String NewMsg = scanner.nextLine();

            EnquiryLogicActions.getInstance().editMessage(enquiryID,NewMsg);

            ClearCMD.clear();
            System.out.println("Changes Saved!");
        }catch(ModelNotFoundException e){
            System.out.println("Could not perform action");
        }
    }

    private static void deleteEnquiry(String enquiryID) {
        try {
            EnquiryLogicActions.getInstance().delete(enquiryID);
            System.out.println("Changes Saved!");
        } catch (ModelNotFoundException e) {
            System.out.println("Could not perform action");
        }
        //ClearCMD.clear(); don't need to clear
    }
}
