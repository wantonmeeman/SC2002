package Logic;

import Data.Models.Applicant;
import Data.Models.User;
import Data.Models.Officer;
import Data.Models.Manager;
import Data.Models.SearchSetting;

import Data.Repository.ApplicantRepository;
import Data.Repository.ManagerRepository;
import Data.Repository.OfficerRepository;
import Data.Repository.DataRepository;

import Exceptions.RepositoryNotFoundException;
import Exceptions.ModelNotFoundException;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.WrongInputException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserLogicActions extends DataLogicActions<User>{
    private static UserLogicActions instance;

    protected HashMap<String,String> toMap(User user){
        HashMap<String,String> userMap = new HashMap<String, String>();

        userMap.put("ID",user.getID());
        userMap.put("Name",user.getName());
        userMap.put("Password",user.getPassword());
        userMap.put("MaritalStatus",String.valueOf(user.getMaritalStatus()));
        userMap.put("Age",String.valueOf(user.getAge()));

        if (user instanceof Officer) {
            userMap.put("Role","Officer");

            userMap.put("ApplicationID",((Applicant) user).getApplicationID());
            userMap.put("RegistrationID", ((Officer) user).getRegistrationID());
        } else if (user instanceof Manager) {
            userMap.put("Role","Manager");

            userMap.put("ProjectID", ((Manager) user).getProjectID());
        } else if (user instanceof Applicant) {
            userMap.put("Role","Applicant");

            userMap.put("ApplicationID",((Applicant) user).getApplicationID());
        }

        return userMap;
    }

    public String create(HashMap<String, String> hm) throws ModelAlreadyExistsException {
        HashMap<String, String> userMap = new HashMap<String, String>();

        String userID = generateID();
        String name = hm.get("Name");
        String password = hm.get("Password");
        char maritalStatus = hm.get("MaritalStatus").charAt(0);
        int age = Integer.parseInt(hm.get("Age"));
        String role = hm.get("Role");

        if (role.equals("Officer")) {
            try {
                OfficerRepository.getInstance().create(
                        new Officer(
                                userID,
                                name,
                                age,
                                maritalStatus,
                                password
                        )
                );
            } catch (ModelAlreadyExistsException e) {
                create(hm); // Try again
            }
            // "AssignedProjectID"
            // "RegistrationStatus"
        }
        else if (role.equals("Manager")) {
            try {
                ManagerRepository.getInstance().create(
                        new Manager(
                                userID,
                                name,
                                age,
                                maritalStatus,
                                password
                        )
                );
            } catch (ModelAlreadyExistsException e) {
                create(hm); // Try again
            }
            // "ProjectID"
        }
        else if (role.equals("Applicant")) {
            try {
                ApplicantRepository.getInstance().create(
                        new Applicant(
                                userID,
                                name,
                                age,
                                maritalStatus,
                                password
                        )
                );
            } catch (ModelAlreadyExistsException e) {
                create(hm); // Try again
            }
            // "ApplicationID"
        }

        return userID;
    }

    @Override
    protected Stream<User> getAllObject(){
        return Stream.of(
                        ApplicantRepository.getInstance().getAll(),
                        ManagerRepository.getInstance().getAll(),
                        OfficerRepository.getInstance().getAll()
                )
                .flatMap(list -> list.stream())  // Flatten the list of lists to a stream of users
                .map(model -> (User) model);
    }

    private DataRepository getDataRepository(User user) throws RepositoryNotFoundException{
        if (user instanceof Officer) {
            return OfficerRepository.getInstance();
        } else if (user instanceof Manager){
            return ManagerRepository.getInstance();
        } else if (user instanceof Applicant) {
            return ApplicantRepository.getInstance();
        }
        throw new RepositoryNotFoundException();
    }

    @Override
    public void delete(String ID) throws ModelNotFoundException{
        try {
            getDataRepository(getObject(ID)).delete(ID);
        }catch(RepositoryNotFoundException e){
            throw new ModelNotFoundException();//Better
        }
    }

    public void apply(String ID,String applicationID) throws ModelNotFoundException, RepositoryNotFoundException {
        Applicant applicant = (Applicant) getObject(ID);
        applicant.setApplicationID(applicationID);

        getDataRepository((User) applicant).update(ID,applicant);
    }

//    public HashMap<String,String> searchSettingToMap(SearchSetting ss){
//        HashMap<String,String> hm = new HashMap<String, String>();
//
//        hm.put("Location",ss.getLocation());
//        hm.put("2Room",String.valueOf(ss.getFlatTypes()[0]));
//        hm.put("3Room",String.valueOf(ss.getFlatTypes()[1]));
//        hm.put("Neighbourhood",ss.getNeighbourhood());
//        hm.put("OpeningDate",String.valueOf(ss.getOpeningDate()));
//        hm.put("ClosingDate",String.valueOf(ss.getClosingDate()));
//        hm.put("Ascending",String.valueOf(ss.getAscending()));
//        hm.put("SortBy",String.valueOf(ss.getSortBy()));
//
//        return hm;
//    }
//
//    public SearchSetting mapToSearchSetting(HashMap<String,String> hm){
//        return new SearchSetting(
//                hm.get("Location"),
//                new Boolean[] {
//                        Boolean.parseBoolean(hm.get("2Room")),
//                        Boolean.parseBoolean(hm.get("3Room"))
//                },
//                hm.get("Neighbourhood"),
//                Long.parseLong(hm.get("OpeningDate")),
//                Long.parseLong(hm.get("ClosingDate")),
//                Boolean.parseBoolean(hm.get("Ascending")),
//                Integer.parseInt(hm.get("SortBy"))
//        );
//    }

//    public HashMap<String,String> getSearchSetting(String userID) throws ModelNotFoundException{
//         return searchSettingToMap(getObject(userID).getSearchSetting());
//    }

//    public void updateSearchSetting(String userID,HashMap<String,String> hm) throws ModelNotFoundException, RepositoryNotFoundException{
//        User user = getObject(userID);
//
//        user.setSearchSetting(mapToSearchSetting(hm));
//
//        getDataRepository(user).update(userID,user);
//    }

    public HashMap<String, String> login(String userID, String password) throws WrongInputException {
        Optional<User> userOpt = getAllObject().filter(
                        user -> user.login(userID, password))//Do we want the user to handle authentication
                .findFirst();

        if (userOpt.isPresent()) {
            return toMap(userOpt.get());
        }else{
            throw new WrongInputException();
        }
    }

    public static UserLogicActions getInstance() {
        if (instance == null)
            instance = new UserLogicActions();
        return instance;
    }

}
