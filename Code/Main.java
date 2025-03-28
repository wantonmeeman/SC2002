
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static String INPUT_APPLICANT_PATH = "../InputData/ApplicantList.txt";
    static String INPUT_MANAGER_PATH = "../InputData/Manager.txt";
    static String INPUT_OFFICER_PATH = "../InputData/OfficerList.txt";
    static String INPUT_PROJECT_PATH = "../InputData/ProjectList.txt";

    public static void main(String[] args) {
        /*TODO
         * -Do the downcasting and the storing and loading of user data in a more effecient manner
         * -use instanceOf
         * 
         */
        InputReader reader = new InputReader();
        ArrayList<User> userArr = new ArrayList<>();
        String lines[];

        lines = reader.readText(INPUT_APPLICANT_PATH).split("\\r?\\n");//gets \n or \r\n
        for (int x = 1; x < lines.length; x++) {
            String tabs[] = lines[x].split("\\t");

            userArr.add(new Applicant(tabs[1],tabs[0],Integer.parseInt(tabs[2]),tabs[3].charAt(0),tabs[4]));
        }

        lines = reader.readText(INPUT_MANAGER_PATH).split("\\r?\\n");
        for (int x = 1; x < lines.length; x++) {
            String tabs[] = lines[x].split("\\t");

            userArr.add(new HDBManager(tabs[1],tabs[0],Integer.parseInt(tabs[2]),tabs[3].charAt(0),tabs[4]));
        }


        lines = reader.readText(INPUT_OFFICER_PATH).split("\\r?\\n");
        for (int x = 1; x < lines.length; x++) {
            String tabs[] = lines[x].split("\\t");

            userArr.add(new HDBOfficer(tabs[1],tabs[0],Integer.parseInt(tabs[2]),tabs[3].charAt(0),tabs[4]));
        }

        // lines = reader.readText(INPUT_PROJECT_PATH).split("\\r?\\n");
        // for (int x = 1; x < lines.length; x++) {
        //     String tabs[] = lines[x].split("\\t");

        //     userArr.add(new HDBOfficer(tabs[1],tabs[0],Integer.parseInt(tabs[2]),tabs[3].charAt(0),tabs[4]));
        // }

        // for(int i = 0;i < userArr.size();i++){
        //     System.out.print(instanceof userArr.get(i));
        // }

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
