package Data;

import java.util.ArrayList;

import Exceptions.WrongInputException;
import Util.CSVReader;

public class UserDataActions {

    public static boolean checkPassword(User user, String password) throws WrongInputException{//Temp, plan to add password hashing
        return true;
        //return user.getHashedPassword().equals(PasswordHashManager.hashPassword(password));
    }

    public static ArrayList<User> tempReader() {//Temp
        String INPUT_APPLICANT_PATH = "../InputData/ApplicantList.csv";
        String INPUT_MANAGER_PATH = "../InputData/Manager.csv";
        String INPUT_OFFICER_PATH = "../InputData/OfficerList.csv";

        ArrayList<User> userArr = new ArrayList<>();

        ArrayList<ArrayList<String>> lines = CSVReader.readCSV(INPUT_APPLICANT_PATH);
        for (int x = 0; x < lines.size(); x++) {
            ArrayList<String> currUser = lines.get(x);
            userArr.add(new Applicant(currUser.get(1), currUser.get(0), Integer.parseInt(currUser.get(2)), currUser.get(3).charAt(0), currUser.get(4)));
        }

        lines = CSVReader.readCSV(INPUT_MANAGER_PATH);
        for (int x = 0; x < lines.size(); x++) {
            ArrayList<String> currUser = lines.get(x);
            userArr.add(new HDBManager(currUser.get(1), currUser.get(0), Integer.parseInt(currUser.get(2)), currUser.get(3).charAt(0), currUser.get(4)));
        }

        lines = CSVReader.readCSV(INPUT_OFFICER_PATH);
        for (int x = 0; x < lines.size(); x++) {
            ArrayList<String> currUser = lines.get(x);
            userArr.add(new HDBOfficer(currUser.get(1), currUser.get(0), Integer.parseInt(currUser.get(2)), currUser.get(3).charAt(0), currUser.get(4)));
        }

        return userArr;
    }

    public static User getUser(String userID) throws WrongInputException{
        ArrayList<User> userArr = tempReader();

        for(User user : userArr){
            if(user.getUserID().equals(userID))
                return user;
        }

        throw new WrongInputException("");
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> userArr = tempReader();

        return userArr;
    }
}
