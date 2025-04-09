package Logic;

import Data.User;
import Data.UserDataActions;
import Exceptions.WrongInputException;

public class UserLogicActions {

    public static User login(String userID, String password) throws WrongInputException {
        try {
            User user = UserDataActions.getUser(userID);

            if (UserDataActions.checkPassword(user, password)) {
                return user; 
            }else {
                throw new WrongInputException("");
            }

        } catch (WrongInputException e) {
            throw e;
        }
    }
}
