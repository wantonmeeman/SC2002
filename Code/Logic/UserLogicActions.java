package Logic;

import Data.Models.Applicant;
import Data.Models.User;
import Data.Repository.ApplicantRepository;
import Data.Repository.ManagerRepository;
import Data.Repository.OfficerRepository;
import Exceptions.ModelNotFoundException;
import Exceptions.WrongInputException;
import java.util.Optional;
import java.util.stream.Stream;

public class UserLogicActions {

    public static User login(String userID, String password) throws WrongInputException {
        Optional<User> userOpt = Stream.of(
                        ApplicantRepository.getInstance().getAll(),
                        ManagerRepository.getInstance().getAll(),
                        OfficerRepository.getInstance().getAll()
                )
                .flatMap(
                        list -> list.stream().map(
                                model -> (User) model)
                ).filter(
                        user -> user.login(userID, password))//Do we want the user to handle authentication
                .findFirst();
        if (userOpt.isPresent()) {
            return userOpt.get();
        }else{
            throw new WrongInputException();
        }
    }
}
