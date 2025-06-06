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
import Util.DefaultGenerateID;
import Util.Interfaces.IDGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type User logic actions.
 */
public class UserLogicActions extends DataLogicActions<User>{
    private static UserLogicActions instance;

    /**
     * Instantiates a new User logic actions.
     *
     * @param idGenerator the id generator
     */
    public UserLogicActions(IDGenerator idGenerator) {
        super(idGenerator);
    }

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
        } else if (user instanceof Manager) {
            userMap.put("Role","Manager");
        } else if (user instanceof Applicant) {
            userMap.put("Role","Applicant");

            userMap.put("ApplicationID",((Applicant) user).getApplicationID());
        }

        return userMap;
    }

    public String create(HashMap<String, String> hm) {
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
                                password,
                                null
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
                                password,
                                null
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

    private DataRepository getDataRepository(String role) throws RepositoryNotFoundException{
        return switch(role){
            case "Officer"->OfficerRepository.getInstance();
            case "Manager"->ManagerRepository.getInstance();
            case "Applicant"->ApplicantRepository.getInstance();
            default->throw new RepositoryNotFoundException();
        };
    }

    private String getRole(User user) throws ModelNotFoundException {
        if (user instanceof Officer) {
            return "Officer";
        } else if (user instanceof Manager) {
            return "Manager";
        } else if (user instanceof Applicant) {
            return "Applicant";
        } else {
            throw new ModelNotFoundException();
        }
    }

    /**
     * Gets logout message.
     *
     * @param userID the user id
     * @return the logout message
     * @throws ModelNotFoundException the model not found exception
     */
    public String getLogoutMessage(String userID) throws ModelNotFoundException {
        User u = getObject(userID);

        String returnStr = "Logging out "+u.getName();

        switch(getRole(u)) {
            case "Officer":
                returnStr += " (Officer)";
                break;
            case "Manager":
                returnStr += " (Manager)";
                break;
            case "Applicant":
                returnStr += " (Applicant)";
                break;
        }

        return returnStr;
    }
    @Override
    public void delete(String ID) throws ModelNotFoundException{
        try {
            getDataRepository(getRole(getObject(ID))).delete(ID);
        }catch(RepositoryNotFoundException e){
            throw new ModelNotFoundException();//Better
        }
    }

    /**
     * Apply.
     *
     * @param ID            the id
     * @param applicationID the application id
     * @throws ModelNotFoundException      the model not found exception
     * @throws RepositoryNotFoundException the repository not found exception
     */
    public void apply(String ID,String applicationID) throws ModelNotFoundException, RepositoryNotFoundException {
        Applicant applicant = (Applicant) getObject(ID);
        applicant.setApplicationID(applicationID);

        getDataRepository(getRole(getObject(ID))).update();
    }

    /**
     * Change password.
     *
     * @param ID          the id
     * @param newPassword the new password
     * @throws ModelNotFoundException      the model not found exception
     * @throws RepositoryNotFoundException the repository not found exception
     */
    public void changePassword(String ID, String newPassword) throws ModelNotFoundException, RepositoryNotFoundException{
        User u = getObject(ID);
        u.setPassword(newPassword);

        getDataRepository(getRole(u)).update();
    }

    /**
     * Login hash map.
     *
     * @param userID   the user id
     * @param password the password
     * @return the hash map
     * @throws WrongInputException the wrong input exception
     */
    public HashMap<String, String> login(String userID, String password) throws WrongInputException {

        //System.out.println("[DEBUG] Attempting login for userID: " + userID);

        Optional<User> userOpt = getAllObject().filter(
                        user -> user.getID().equals(userID) && user.getPassword().equals(password)
                )//Do we want the user to handle authentication
                .findFirst();

        if (userOpt.isPresent()) {
            return toMap(userOpt.get());
        }else{
            throw new WrongInputException();
        }
    }

    /**
     * Remove applications.
     *
     * @param applicationID the application id
     */
    public void removeApplications(String applicationID){
        Stream.of(OfficerRepository.getInstance().getAll(),ApplicantRepository.getInstance().getAll())
                .flatMap(Collection::stream)
                .map(model->(Applicant)model)
                .filter(applicant -> applicationID.equals(applicant.getApplicationID()))
                .forEach(applicant -> {
                    applicant.setApplicationID(null);
                });

        OfficerRepository.getInstance().update();
        ApplicantRepository.getInstance().update();
    }

    /**
     * Get all manager array list.
     *
     * @return the array list
     */
    public ArrayList<HashMap<String,String>> getAllManager(){
        return Stream.of(ManagerRepository.getInstance().getAll())
                .flatMap(Collection::stream)  // Flatten the list of lists to a stream of users
                .map(model -> (Manager) model)
                .map(this::toMap)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UserLogicActions getInstance() {
        if (instance == null)
            instance = new UserLogicActions(new DefaultGenerateID());
        return instance;
    }

}
