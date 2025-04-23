package Pages.UserPages;

import Exceptions.ModelNotFoundException;
import Exceptions.RepositoryNotFoundException;
import Logic.UserLogicActions;
import Pages.Components.Back;
import Pages.Components.HomepageView;
import Pages.Components.Seperator;
import Pages.Components.UserView;
import Util.ClearCMD;

import java.util.Scanner;

public class UserPage {
    public static void start(String userID){
        Scanner scanner = new Scanner(System.in);
        int input;
        System.out.println(Seperator.seperate());

        try {
            System.out.println(UserView.profileView(userID));
        } catch (ModelNotFoundException e) {
            System.out.println("Could not find user");
            return;
        }
        System.out.println(Back.back());
        System.out.println("2. Change Password");

        try {
            input = Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException e){
            input = -1;//pass to default handler
        }
        ClearCMD.clear();

        if(input == 1){

        }else if(input == 2){
            changePassword(userID);
        }else{
            System.out.println("Invalid Input");
            start(userID);
        }
    }

    public static void changePassword(String userID){
        Scanner scanner = new Scanner(System.in);
        System.out.println(Seperator.seperate());
        System.out.println("New Password:");
        String newPassword = scanner.nextLine();

        try {
            UserLogicActions.getInstance().changePassword(userID,newPassword);
            System.out.println("Password Changed!");
        } catch (ModelNotFoundException | RepositoryNotFoundException e) {
            System.out.println("Password not changed!");
        }

        ClearCMD.clear();
    }
}
