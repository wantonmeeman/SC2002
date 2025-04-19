package Classes;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner; // Import the Scanner class to read text files

public class InputReader {

    public String loadText(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            String returnString = "";

            while (scanner.hasNextLine()) {
                returnString += (scanner.nextLine() + (scanner.hasNextLine() ? "\n" : ""));
            }

            scanner.close();

            return returnString;
        } catch (FileNotFoundException e) {
            System.out.println("Error with reading the file!");
            return "Error!";
        }
    }

    public ArrayList<Project> loadProjects(String projectPath, ArrayList<User> userArr){//No constraint for projects size, otherwise wld be a static arr
        ArrayList<Project> projectArr = new ArrayList<>();
        String lines[];

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        lines = this.loadText(projectPath).split("\\r?\\n");//gets \n or \r\n
        for (int x = 1; x < lines.length; x++) {
            String cols[] = lines[x].split(",");

            String name = cols[0];
            String neighbourhood = cols[1];

            Flat[] flats = new Flat[] {
                new Flat(cols[2], Integer.parseInt(cols[3]), Float.parseFloat(cols[4])),
                new Flat(cols[5], Integer.parseInt(cols[6]), Float.parseFloat(cols[7]))
            };
            
            Date openingDate;
            Date closingDate;

            try {
                openingDate = sdf.parse(cols[8]);
                closingDate = sdf.parse(cols[9]);
            }catch (ParseException ex) {
                System.out.println("Error Parsing Date");
                
                openingDate = new Date();
                closingDate = new Date();
            }
            
            String managerID = null;

            for(int y = 0; y < userArr.size();y++){
                User u = userArr.get(y);
                if(cols[10].equals(u.getName())){
                    managerID = u.getUserID();
                    break;
                }
            }

            int officerSlots = Integer.parseInt(cols[11]);

            String[] officerIDArray = new String[officerSlots];
            
            for(int i = 12; i < cols.length;i++){
                String userID = "-1";

                for(int y = 0; y < userArr.size();y++){
                    User u = userArr.get(y);
                    if(cols[12].equals(u.getName())){
                        userID = u.getUserID();
                        break;
                    }
                }

                if(userID.equals("-1")){
                    officerIDArray[i-12] = null;
                }else{
                    officerIDArray[i-12] = userID;
                }
            }

            projectArr.add(new Project(
            name, neighbourhood, closingDate, openingDate, officerSlots, officerIDArray, managerID, flats));
        }

        return projectArr;
    }

    public ArrayList<User> loadUsers(String applicantPath, String managerPath, String officerPath){//No constraint for user size, otherwise wld be a static arr
        ArrayList<User> userArr = new ArrayList<>();
        String lines[];
        
        lines = this.loadText(applicantPath).split("\\r?\\n");//gets \n or \r\n
        for (int x = 1; x < lines.length; x++) {
            String cols[] = lines[x].split(",");

            userArr.add(new Applicant(cols[1],cols[0],Integer.parseInt(cols[2]),cols[3].charAt(0),cols[4]));
        }

        lines = this.loadText(managerPath).split("\\r?\\n");
        for (int x = 1; x < lines.length; x++) {
            String cols[] = lines[x].split(",");

            userArr.add(new HDBManager(cols[1],cols[0],Integer.parseInt(cols[2]),cols[3].charAt(0),cols[4]));
        }

        lines = this.loadText(officerPath).split("\\r?\\n");
        for (int x = 1; x < lines.length; x++) {
            String cols[] = lines[x].split(",");

            userArr.add(new HDBOfficer(cols[1],cols[0],Integer.parseInt(cols[2]),cols[3].charAt(0),cols[4]));
        }

        return userArr;
    }
}


