package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.EnquiryLogicActions;
import Util.ClearCMD;

import java.util.Scanner;

/**
 * The interface Reply enquiry.
 */
public interface ReplyEnquiry {
    /**
     * Reply enquiry.
     *
     * @param enquiryID the enquiry id
     */
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
            System.out.println("Could not find enquiry");
        }
    }
}
