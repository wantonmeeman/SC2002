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
                System.out.println(e);
            }
        }

        input = Integer.parseInt(scanner.nextLine());
        ClearCMD.clear();
        switch (input) {
            case 1 -> {}
            case 2 -> {
                createEnquiry(userID);
                start(userID);
            }
            default -> {
                detailedEnquiry(enquiries.get(input - 3).get("ID"));
                start(userID);
            }
        }
    }

    private static void createEnquiry(String userID){
        HashMap<String,String> hm = new HashMap<String,String>();

        hm.put("ProjectID",chooseProject(userID));
        hm.put("Message",writeMessage());
        hm.put("UserID", userID);

        EnquiryLogicActions.getInstance().create(hm);
        ClearCMD.clear();
    }

    private static String chooseProject(String userID){
        Scanner scanner = new Scanner(System.in);
        int input;

        System.out.println(Seperator.seperate());
        System.out.println("Choose which project you wish to enquire about:");

        ArrayList<HashMap<String,String>> projList = ProjectLogicActions.getInstance().getAll();

        int x = 1;
        for(HashMap<String,String> hm: projList){
            System.out.println((x++) +". "+hm.get("Name"));
        }

        input = Integer.parseInt(scanner.nextLine());
        return projList.get(input-1).get("ID");
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

            input = Integer.parseInt(scanner.nextLine());

            ClearCMD.clear();

            switch (input) {
                case 1 -> {}
                case 2 -> {
                    editEnquiry(enquiryID);
                    detailedEnquiry(enquiryID);
                }
                case 3 -> deleteEnquiry(enquiryID);
            }
        }catch(ModelNotFoundException e){

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
        }catch(ModelNotFoundException e){
            System.out.println(e);
        }
        ClearCMD.clear();
        System.out.println("Changes Saved!");
    }

    private static void deleteEnquiry(String enquiryID) {
        try {
            EnquiryLogicActions.getInstance().delete(enquiryID);
        } catch (ModelNotFoundException e) {

        }
        //ClearCMD.clear(); don't need to clear
        System.out.println("Changes Saved!");
    }
}
