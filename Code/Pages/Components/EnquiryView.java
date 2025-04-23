package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.EnquiryLogicActions;
import Logic.ProjectLogicActions;
import Util.Config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static Util.WrapText.wrap;
import static Util.TruncateText.truncate;

public interface EnquiryView {
    static String detailedView(String enquiryID) throws ModelNotFoundException {
            SimpleDateFormat formatter = Config.DATE_FORMAT;//TODO config
            String returnStr = "";
            HashMap<String,String> ehm = EnquiryLogicActions.getInstance().get(enquiryID);

            Date dateObject = new Date(Long.parseLong(ehm.get("Timestamp")) * 1000L);
            String formattedDateTime = formatter.format(dateObject);

            HashMap<String,String> phm = ProjectLogicActions.getInstance().get(ehm.get("ProjectID"));//TODO handle exception

            returnStr += "Enquiry Time and Date: " +formattedDateTime;
            returnStr += "\n" + "Enquiry Project Name: " +phm.get("Name");
            returnStr += "\n" + "Enquiry Message:\n" + wrap(ehm.get("Message"));

            if(ehm.get("Reply") != null) {
                    returnStr += "\n" + "Enquiry Reply:\n" + wrap(ehm.get("Reply"));
            }

            return returnStr;
    }

    static String simpleView(String enquiryID) throws ModelNotFoundException{
            SimpleDateFormat formatter = Config.DATE_FORMAT;//TODO config
            HashMap<String,String> ehm = EnquiryLogicActions.getInstance().get(enquiryID);

            Date dateObject = new Date(Long.parseLong(ehm.get("Timestamp"))*1000L);
            String formattedDateTime = formatter.format(dateObject);

            HashMap<String, String> hm = ProjectLogicActions.getInstance().get(ehm.get("ProjectID"));

            return formattedDateTime + " - " + hm.get("Name") + " - " + truncate(ehm.get("Message"));
    }
}
