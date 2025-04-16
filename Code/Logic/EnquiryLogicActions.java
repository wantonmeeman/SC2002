package Logic;

import Data.Models.Applicant;
import Data.Models.Manager;
import Data.Models.Officer;
import Data.Models.User;
import Data.Models.Enquiry;
import Data.Repository.ApplicantRepository;
import Data.Repository.ManagerRepository;
import Data.Repository.OfficerRepository;
import Data.Repository.EnquiryRepository;
import Exceptions.ModelNotFoundException;
import Exceptions.WrongInputException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

public class EnquiryLogicActions {
//Can we use the DataRepository base functions?
    private static HashMap<String,String> convertEnquiryToMap(Enquiry enquiry){
        HashMap<String,String> enquiryMap = new HashMap<String, String>();

        enquiryMap.put("ID",enquiry.getID());
        enquiryMap.put("ProjectID", enquiry.getProjectID());
        enquiryMap.put("UserID", enquiry.getUserID());
        enquiryMap.put("Message", enquiry.getMessage());
        enquiryMap.put("Reply", enquiry.getReply());
        enquiryMap.put("Timestamp", String.valueOf(enquiry.getTimestamp().getTime()));

        return enquiryMap;
    }

    private static Stream<Enquiry> getEnquiries() {
        return EnquiryRepository.getInstance().getAll()
                .stream()
                .map(model -> (Enquiry) model);
    }

    public static HashMap<String,String> getEnquiry(String ID) throws ModelNotFoundException{
        Optional<Enquiry> enquiryOpt = getEnquiries().filter(
                Enquiry -> Enquiry.getID().equals(ID)
                ).findFirst();

        if (enquiryOpt.isPresent()) {
            return convertEnquiryToMap(enquiryOpt.get());
        }else{
            throw new ModelNotFoundException();
        }
    }

    public static ArrayList<HashMap<String,String>> getUserEnquiries(String UserID){
        ArrayList<HashMap<String, String>> enquiryList = new ArrayList<>();
        getEnquiries()
                //.filter(enquiry -> enquiry.getUserID().equals(UserID))
                .forEach(enquiry -> {
                    enquiryList.add(convertEnquiryToMap(enquiry));
                });
        return enquiryList;
    }

    public static void editEnquiry(String EnquiryID,String newMessage) throws ModelNotFoundException{
        Optional<Enquiry> enquiryOpt = getEnquiries().filter(
                Enquiry -> Enquiry.getID().equals(EnquiryID)
        ).findFirst();

        if (enquiryOpt.isPresent()) {
            enquiryOpt.get().setMessage(newMessage);
        }else{
            throw new ModelNotFoundException();
        }
    }
    public static void deleteEnquiry(String enquiryID) throws ModelNotFoundException{
        Optional<Enquiry> enquiryOpt = getEnquiries().filter(
                enquiry -> enquiry.getID().equals(enquiryID)
        ).findFirst();

        if (enquiryOpt.isPresent()) {
            // Assuming you have an EnquiryRepository or some collection to remove it from
                EnquiryRepository.getInstance().delete(enquiryOpt.get().getID());
        } else {
            throw new ModelNotFoundException();
        }
    }
}
