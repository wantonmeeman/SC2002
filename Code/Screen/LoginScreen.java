package Screen;

import java.util.ArrayList;
import java.util.Scanner;

import Classes.User;

public class LoginScreen extends Screen<ArrayList<User>,User> {

    Scanner scanner;
    ArrayList<User> userArr;

    public LoginScreen() {
    }

    @Override
    public User start(ArrayList<User> userArr, Scanner sc) {
        this.userArr = userArr;

        return this.login();
    };

    private User login() {
        String userid;
        String password;

        while (true) {
            System.out.print("Username: \n");
            userid = scanner.nextLine();

            System.out.print("Password: \n");
            password = scanner.nextLine();

            for (User user : userArr) {
                if (user.login(userid, password)) {
                    return user;
                }
            }

            System.out.println("Invalid credentials. Please try again.");
        }
    }
}
