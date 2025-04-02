
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String INPUT_APPLICANT_PATH = "../InputData/ApplicantList.csv";
    static String INPUT_MANAGER_PATH = "../InputData/Manager.csv";
    static String INPUT_OFFICER_PATH = "../InputData/OfficerList.csv";
    static String INPUT_PROJECT_PATH = "../InputData/ProjectList.csv";

    public static void main(String[] args) {
        InputReader reader = new InputReader();

        ArrayList<User> userArr = reader.loadUsers(INPUT_APPLICANT_PATH, INPUT_MANAGER_PATH, INPUT_OFFICER_PATH);
        ArrayList<Project> projectArr = reader.loadProjects(INPUT_PROJECT_PATH, userArr);

        for(int x = 0;projectArr.size() > x;x++){
            System.out.print(projectArr.get(x).toString());
        }

        for(int x = 0;userArr.size() > x;x++){
            System.out.print(userArr.get(x).toString());
        }

        Scanner scanner = new Scanner(System.in);
        User loggedUserObj = null;
        
        Boolean exitCondition = false;
        String password,username;

        while (!exitCondition) {
            if (loggedUserObj == null) {
                System.out.println("Username: ");
                username = scanner.nextLine();

                System.out.println("Password: ");
                password = scanner.nextLine();

                for(User user: userArr){
                    if(user.login(username,password)){
                        loggedUserObj = user;
                        break;
                    }
                }

                if(loggedUserObj == null){
                    System.out.print("Invalid! Please try again.\n");
                }

            }else{


                System.out.print("Welcome "+loggedUserObj.getName()+"\n"
                +"1.Exit"+"\n");
                
                String input = scanner.nextLine();

                switch(input){
                    case "1":
                        System.out.print("Exiting");
                        exitCondition = true;
                    break;
                }   
            }
        }
    }
}
