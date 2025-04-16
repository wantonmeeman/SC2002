package Logic;

import Data.Models.Applicant;
import Data.Models.User;
import Data.Models.Officer;
import Data.Models.Manager;

import Data.Repository.ApplicantRepository;
import Data.Repository.ManagerRepository;
import Data.Repository.OfficerRepository;
import Exceptions.ModelNotFoundException;
import Exceptions.WrongInputException;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

public class UserLogicActions {

    private static HashMap<String,String> convertUserToMap(User user){
        HashMap<String,String> userMap = new HashMap<String, String>();

        userMap.put("ID",user.getID());
        userMap.put("Name",user.getName());
        userMap.put("Password",user.getPassword());
        userMap.put("MaritalStatus",String.valueOf(user.getMaritalStatus()));
        userMap.put("Age",String.valueOf(user.getAge()));

        if (user instanceof Officer) {
            userMap.put("Role","Officer");
            userMap.put("AssignedProjectID", ((Officer) user).getAssignedProjectID());
            userMap.put("RegistrationStatus", String.valueOf(((Officer) user).getRegistrationStatus()));
        } else if (user instanceof Manager) {
            userMap.put("Role","Manager");
            userMap.put("ProjectID", ((Manager) user).getProjectID());
        } else if (user instanceof Applicant) {
            userMap.put("Role","Applicant");
            userMap.put("ApplicationID",((Applicant) user).getApplicationID());
        }

        return userMap;
    }

    private static Stream<User> getUsers() {
        return Stream.of(
                        ApplicantRepository.getInstance().getAll(),
                        ManagerRepository.getInstance().getAll(),
                        OfficerRepository.getInstance().getAll()
                )
                .flatMap(list -> list.stream())  // Flatten the list of lists to a stream of users
                .map(model -> (User) model);    // Cast each model to User
    }

    public static HashMap<String,String> getUser(String userID) throws ModelNotFoundException{
        Optional<User> userOpt = getUsers().filter(
                        user -> user.getID().equals(userID)
                ).findFirst();

        if (userOpt.isPresent()) {
            return convertUserToMap(userOpt.get());
        }else{
            throw new ModelNotFoundException();
        }
    }

    public static HashMap<String, String> login(String userID, String password) throws WrongInputException {
        Optional<User> userOpt = getUsers().filter(
                        user -> user.login(userID, password))//Do we want the user to handle authentication
                .findFirst();

        if (userOpt.isPresent()) {
            return convertUserToMap(userOpt.get());
        }else{
            throw new WrongInputException();
        }
    }


}
