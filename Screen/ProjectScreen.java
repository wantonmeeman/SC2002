package Screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Classes.Flat;
import Classes.Project;
import Classes.User;

public class ProjectScreen extends Screen<ArrayList<Project>, ArrayList<Project>> {

    StringBuilder sb;
    Scanner sc;
    User user;
    ArrayList<Project> projectArr;
    HashMap<Integer, Integer> optionToMenuMap;
    HashMap<Integer, Integer> roomToMenuMap;

    public ProjectScreen(User user) {
        this.user = user;
    }

    @Override
    public ArrayList<Project> start(ArrayList<Project> projectArr, Scanner sc) {
        this.sb = new StringBuilder();
        this.sc = sc;
        this.projectArr = projectArr;
        this.optionToMenuMap = new HashMap<>();
        this.roomToMenuMap = new HashMap<>();

        displayProjects();
        return projectArr;
    }

    private void displayProject(int index) {
        Project proj = projectArr.get(optionToMenuMap.get(index));
        Flat flat = (proj.getFlats()[roomToMenuMap.get(index) - 2]);
        sb.append(proj.getName())
            .append(" - ")
            .append(roomToMenuMap.get(index) == 2 ? "Two Room" : "Three Room")
            .append("\n")
            .append("Price: ")
            .append(flat.getPriceAsString())
            .append(" - Total Rooms: ")
            .append(flat.getTotalUnits())
            .append("\n")
            .append("1.Exit")
            .append("\n")
            .append("2.Apply")
            .append("\n");

        System.out.print(sb.toString());
        sb.setLength(0);

        String userInput = sc.nextLine();
        int userInputInteger = -1;

        try {
            userInputInteger = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.print("Error");//ERROR
        }

        if (userInputInteger == -1) {
            //Error handling
        } else if (userInputInteger == 1) {
            
        } else if (userInputInteger == 2) {
            
        }
    }

    private void displayProjects() {
        sb.append("1.Exit").append("\n");

        int menuOption = 2;
        for (int x = 0; x < projectArr.size(); x++) {
            Project proj = projectArr.get(x);
            if (proj.getVisibility()) {
                if (user.getMaritalStatus() == 'M' && user.getAge() >= 21) {//Show all rooms
                    Flat twoRoom = (proj.getFlats()[0]);
                    Flat threeRoom = (proj.getFlats()[1]);

                    roomToMenuMap.put(menuOption, 2);
                    optionToMenuMap.put(menuOption, x);
                    sb.append(menuOption++)
                            .append(".")
                            .append(proj.getName())
                            .append(" - Two Room - Price: ")
                            .append(twoRoom.getPriceAsString())
                            .append(" - Total Rooms: ")
                            .append(twoRoom.getTotalUnits())
                            .append("\n");

                    roomToMenuMap.put(menuOption, 3);
                    optionToMenuMap.put(menuOption, x);
                    sb.append(menuOption++)
                            .append(".")
                            .append(proj.getName())
                            .append(" - Three Room - Price: ")
                            .append(threeRoom.getPriceAsString())
                            .append(" - Total Rooms: ")
                            .append(threeRoom.getTotalUnits())
                            .append("\n");

                } else if (user.getMaritalStatus() == 'S' && user.getAge() >= 35) {
                    Flat twoRoom = (proj.getFlats()[0]);

                    roomToMenuMap.put(menuOption, 2);
                    optionToMenuMap.put(menuOption, x);
                    sb.append(menuOption)
                            .append(".")
                            .append(proj.getName())
                            .append(" - Two Room - Price: ")
                            .append(twoRoom.getPriceAsString())
                            .append(" - Total Rooms: ")
                            .append(twoRoom.getTotalUnits())
                            .append("\n");

                }//Show 2 room only
            }
        }

        System.out.print(sb.toString());
        sb.setLength(0);

        String userInput = sc.nextLine();
        int userInputInteger = -1;

        try {
            userInputInteger = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.print("Error");//ERROR
        }

        if (userInputInteger == -1) {
            //Error handling
        } else if (userInputInteger == 1) {
            
        } else {
            displayProject(userInputInteger);
        }
    }
};
