package Pages.FilterSettingPages;

import Exceptions.ModelNotFoundException;
import Pages.Components.*;

import java.util.HashMap;
import java.util.Scanner;

public class ApplicantFilterSettingsPage {
    private static HashMap<String,String> maritalToggle(HashMap<String,String> ashm){
        String maritalStatus = ashm.get("MaritalStatus");
        if (maritalStatus == null) {
            ashm.put("MaritalStatus", "S"); // or whatever you want to default to
        } else {
            switch (maritalStatus) {
                case "M":
                    ashm.put("MaritalStatus", null);
                    break;

                case "S":
                    ashm.put("MaritalStatus", "M");
                    break;
            }
        }
        return ashm;
    }

    private static HashMap<String,String> flatTypeToggle(HashMap<String,String> ashm){
        String flatType = ashm.get("FlatType");
        if (flatType == null) {
            ashm.put("FlatType", "2Room"); // default value if null
        } else {
            switch (flatType) {
                case "3Room":
                    ashm.put("FlatType", null);
                    break;

                case "2Room":
                    ashm.put("FlatType", "3Room");
                    break;
            }
        }
        return ashm;
    }

    public static HashMap<String,String> start(HashMap<String,String> ashm){
        Scanner scanner = new Scanner(System.in);
        int input;

        try {
            System.out.println(Seperator.seperate());
            System.out.println(ApplicantSearchView.detailedView(ashm));
            System.out.println(Back.back());
            System.out.println("2. Marital Status Toggle");
            System.out.println("3. Flat Type Toggle");
        } catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        }
        input = Integer.parseInt(scanner.nextLine());

        try {
            if(input == 1){

            }else if(input == 2){
                start(maritalToggle(ashm));
            }else if(input == 3){
                start(flatTypeToggle(ashm));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ashm;
    }
}