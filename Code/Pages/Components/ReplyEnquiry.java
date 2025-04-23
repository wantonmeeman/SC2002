package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.EnquiryLogicActions;
import Util.ClearCMD;

import java.util.Scanner;

public interface ReplyEnquiry {
    static void replyEnquiry(String enquiryID){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println(Seperator.seperate());
            System.out.println(EnquiryView.detailedView(enquiryID));
            System.out.println("Reply:");

            String reply = scanner.nextLine();
            EnquiryLogicActions.getInstance().reply(enquiryID,reply);

            ClearCMD.clear();
        } catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
