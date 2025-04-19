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
import Exceptions.ModelAlreadyExistsException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;
import java.time.Instant;

public class EnquiryLogicActions extends DataLogicActions<Enquiry>{
    private static EnquiryLogicActions instance;

    @Override
    protected HashMap<String,String> toMap(Enquiry enquiry){
        HashMap<String,String> enquiryMap = new HashMap<String, String>();

        enquiryMap.put("ID",enquiry.getID());
        enquiryMap.put("ProjectID", enquiry.getProjectID());
        enquiryMap.put("UserID", enquiry.getUserID());
        enquiryMap.put("Message", enquiry.getMessage());
        enquiryMap.put("Reply", enquiry.getReply());
        enquiryMap.put("Timestamp", String.valueOf(enquiry.getTimestamp()));

        return enquiryMap;
    }

    public String create(HashMap<String,String> hm){
        String EnquiryID = null;

        try {
            EnquiryID = generateID();

            EnquiryRepository.getInstance().create(new Enquiry(
                    EnquiryID,
                    hm.get("ProjectID"),
                    hm.get("UserID"),
                    hm.get("Message"),
                    Instant.now().getEpochSecond()
            ));
        }catch(ModelAlreadyExistsException e){
            create(hm);//Try again
        }

        return EnquiryID;
    }

    @Override
    protected Stream<Enquiry> getAllObject(){
        return EnquiryRepository.getInstance().getAll()
                .stream()
                .map(model -> (Enquiry) model);
    }

    @Override
    public void delete(String ID) throws ModelNotFoundException{
        EnquiryRepository.getInstance().delete(ID);
    }

    public ArrayList<HashMap<String,String>> getEnquiriesByUserID(String UserID){
        ArrayList<HashMap<String, String>> enquiryList = new ArrayList<>();
        getAllObject()
                .filter(enquiry -> enquiry.getUserID().equals(UserID))
                .forEach(enquiry -> {
                    enquiryList.add(toMap(enquiry));
                });
        return enquiryList;
    }

    public ArrayList<HashMap<String,String>> getEnquiriesByProjectID(String ProjectID){
        ArrayList<HashMap<String, String>> enquiryList = new ArrayList<>();
        getAllObject()
                .filter(enquiry -> enquiry.getProjectID().equals(ProjectID))
                .forEach(enquiry -> {
                    enquiryList.add(toMap(enquiry));
                });
        return enquiryList;
    }

    public void editMessage(String ID, String newMessage) throws ModelNotFoundException{
        Enquiry newEnquiry = getObject(ID);

        newEnquiry.setMessage(newMessage);

        EnquiryRepository.getInstance().update();
    }

    public void reply(String enquiryID, String reply) throws ModelNotFoundException {
        Enquiry newEnquiry = getObject(enquiryID);

        newEnquiry.setReply(reply);

        EnquiryRepository.getInstance().update();
    }

    public static EnquiryLogicActions getInstance() {
        if (instance == null)
            instance = new EnquiryLogicActions();
        return instance;
    }
}
