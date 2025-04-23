package Pages.EnquiryPages;

import Exceptions.ModelNotFoundException;
import Logic.EnquiryLogicActions;
import Logic.ProjectLogicActions;
import Pages.Components.Back;
import Pages.Components.EnquiryView;
import Pages.Components.ReplyEnquiry;
import Pages.Components.Seperator;
import Util.ClearCMD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ManagerEnquiryPage implements ReplyEnquiry {
    public static void start(String userID){
        Scanner scanner = new Scanner(System.in);
        int input;
        ArrayList<HashMap<String, String>> enquiries = EnquiryLogicActions.getInstance().getAll();

        System.out.println(Seperator.seperate());
        System.out.println(Back.back());

        int x = 2;
        for(HashMap<String,String> ehm: enquiries){
            try {
                System.out.println((x++)+". "+ EnquiryView.simpleView(ehm.get("ID")));
            }catch(ModelNotFoundException e){
                System.out.println("Could not find enquiry");
            }
        }

        try {
            input = Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException e){
            input = -1;//pass to default handler
        }
        ClearCMD.clear();

        if(input == 1){
            return;
        }else if(
                input > 0 && input < x){
            viewEnquiry(enquiries.get(input - 2).get("ID"), userID);
        }else{
            System.out.println("Invalid Input");
        }
        start(userID);
    }

    public static void viewEnquiry(String enquiryID,String userID){
        try {
            Scanner scanner = new Scanner(System.in);
            int input;

            System.out.println(Seperator.seperate());
            System.out.println(EnquiryView.detailedView(enquiryID));

            System.out.println(Back.back());

            String managerID = ProjectLogicActions.getInstance().get(
                    EnquiryLogicActions.getInstance().get(enquiryID).get("ProjectID")
            ).get("ManagerID");

            if(managerID.equals(userID)){
                System.out.println("2. Reply");//TODO do we need to remove this
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
                ReplyEnquiry.replyEnquiry(enquiryID);
            }else{
                System.out.println("Invalid Input");
            }

            viewEnquiry(enquiryID,userID);

        } catch (ModelNotFoundException e) {
            System.out.println("Could not find enquiry");
        }
    }
}
