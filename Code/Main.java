
import Pages.StartPage;



// import java.util.ArrayList;
// import java.util.Date;
// import java.util.Scanner;

// import Classes.Enquiry;
// import Classes.InputReader;
// import Classes.Project;
// import Classes.User;
// import Screen.EnquiryScreen;
// import Screen.ExitScreen;
// import Screen.HomeScreen;
// import Screen.LoginScreen;
// import Screen.ProjectScreen;

import Pages.*;

public class Main {
<<<<<<< HEAD

=======
>>>>>>> 658316984a214edaae8c9d8f2cc14eac7675d265
    public static void main(String[] args) {
        StartPage.start();
    }
}

// public class Main {

//     static String INPUT_APPLICANT_PATH = "../InputData/ApplicantList.csv";
//     static String INPUT_MANAGER_PATH = "../InputData/Manager.csv";
//     static String INPUT_OFFICER_PATH = "../InputData/OfficerList.csv";
//     static String INPUT_PROJECT_PATH = "../InputData/ProjectList.csv";

//     public static void main(String[] args) {
//         InputReader reader = new InputReader();
//         Scanner sc = new Scanner(System.in);
//         User loggedUser;

//         ArrayList<User> userArr = reader.loadUsers(INPUT_APPLICANT_PATH, INPUT_MANAGER_PATH, INPUT_OFFICER_PATH);

//         LoginScreen loginScreen = new LoginScreen();
//         loggedUser = loginScreen.start(userArr,sc);

//         ArrayList<Project> projectArr = reader.loadProjects(INPUT_PROJECT_PATH, userArr);
//         ArrayList<Enquiry> enquiryArr = new ArrayList<Enquiry>();
//         enquiryArr.add(new Enquiry("12345678","S1234567A","LAWLAWLLAWLAWLAWL",new Date()));

//         HomeScreen homeScreen = new HomeScreen();
//         Integer userInput;

//         while(true){
//             userInput = homeScreen.start(loggedUser,sc);

//             switch(userInput){
//                 case 1:
//                     ExitScreen escreen = new ExitScreen();
//                     escreen.start(null,sc);
//                     return;
//                 case 2:
//                     ProjectScreen pscreen = new ProjectScreen(loggedUser);
//                     pscreen.start(projectArr,sc);
//                     break;
//                 case 3:
//                     EnquiryScreen enqscreen = new EnquiryScreen(loggedUser);
//                     enqscreen.start(enquiryArr,sc);
//                 break;
            
//             }

//         }




//         // Scanner scanner = new Scanner(System.in);
//         // User loggedUserObj = null;

//         // Boolean exitCondition = false;
//         // String password, username;
//         // StringBuilder sb = new StringBuilder();

//         // while (!exitCondition) {
//         //     if (loggedUserObj == null) {
//         //         System.out.println("Username: ");
//         //         username = scanner.nextLine();

//         //         System.out.println("Password: ");
//         //         password = scanner.nextLine();

//         //         for (User user : userArr) {
//         //             if (user.login(username, password)) {
//         //                 loggedUserObj = user;
//         //                 System.out.print("Welcome " + loggedUserObj.getName() + "\n");
//         //                 break;
//         //             }
//         //         }

//         //         if (loggedUserObj == null) {
//         //             System.out.print("Invalid! Please try again.\n");
//         //         }

//         //     } else {
//         //         sb.setLength(0);
//         //         sb.append("\n");

//                 // System.out.print("1.Exit" + "\n"
//                 //         + "2.View Projects" + "\n"
//                 //         + "3.View Project Applied for" + "\n"
//                 //         + "4.View Enquiries" + "\n");

//         //         String input = scanner.nextLine();

//         //         switch (input) {
//         //             case "1":
//         //                 sb.append("Exiting");
//         //                 exitCondition = true;
//         //                 break;
//         //             case "2":
//         //                 sb.append("Projects: ").append("\n");

//         //                 for (int x = 0; x < projectArr.size(); x++) {
//         //                     Project proj = projectArr.get(x);
//         //                     if (proj.getVisibility()) {
//         //                         if (loggedUserObj.getMaritalStatus() == 'M' && loggedUserObj.getAge() >= 21) {//Show all rooms
//         //                             Flat twoRoom = (proj.getFlats()[0]);
//         //                             Flat threeRoom = (proj.getFlats()[1]);

//         //                             sb.append(proj.getName())
//         //                             .append("\n");

//         //                             sb.append("Two Room - Price: ")
//         //                             .append(twoRoom.getPriceAsString())
//         //                             .append(" - Total Rooms: ")
//         //                             .append(twoRoom.getTotalUnits())
//         //                             .append("\n");

//         //                             sb.append("Three Room - Price: ")
//         //                             .append(threeRoom.getPriceAsString())
//         //                             .append(" - Total Rooms: ")
//         //                             .append(threeRoom.getTotalUnits())
//         //                             .append("\n");
                                    
//         //                             sb.append("\n");
//         //                         } else if (loggedUserObj.getMaritalStatus() == 'S' && loggedUserObj.getAge() >= 35) {
//         //                             Flat twoRoom = (proj.getFlats()[0]);

//         //                             sb.append(proj.getName())
//         //                             .append("\n");

//         //                             sb.append("Two Room - Price: ")
//         //                             .append(twoRoom.getPriceAsString())
//         //                             .append(" - Total Rooms: ")
//         //                             .append(twoRoom.getTotalUnits())
//         //                             .append("\n");

//         //                             sb.append("\n");
//         //                         }//Show 2 room only
//         //                     }
//         //                 }

//         //                 break;
//         //             case "3":
//         //                 //Check if successful, if successful, prompt user to book a flat through HDB officer
//         //                 break;
//         //             case "4":
//         //                 sb.append("Enquiries: ").append("\n");

//         //                 ArrayList<Enquiry> userEnquiryArr = new ArrayList<Enquiry>();

//         //                 for (int x = 0; x < enquiryArr.size(); x++) {
//         //                     Enquiry enq = enquiryArr.get(x);
//         //                     if (enq.getUserID().equals(loggedUserObj.getUserID())) {
//         //                         userEnquiryArr.add(enq);

//         //                         sb.append(x+1).append(enq.toString());
//         //                     }
//         //                 }

//         //                 //User input to create new or choose which one to edit/delete
//         //                 System.out.print(sb.toString());

//         //                 input = scanner.nextLine();
//         //                 if(input.equals("+")){

//         //                 }else{//TODO error handling
//         //                     int integerInput = Integer.parseInt(input);

//         //                     userEnquiryArr.get(integerInput);
//         //                 }

//         //                 break;
//         //         }
//         //         System.out.print(sb.toString());
//         //     }
//         // }
//     }
// }
