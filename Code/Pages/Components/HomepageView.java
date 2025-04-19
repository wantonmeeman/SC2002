package Pages.Components;

public class HomepageView {
    public static String applicantHomepage(){
        return Seperator.seperate()+"\n"+
        "1. Logout"+"\n"+
        "2. Projects"+"\n"+
        "3. Enquiries"+"\n"+
        "4. Applications";
    }

    public static String officerHomepage(){
        return applicantHomepage() + "\n" +
                "5. Registration";
    }

//    public static String managerHomepage(){
//        return applicantHomepage() +
//                "5. Registration" + "\n";
//    }

    public static String getWelcomeMessage(String role,String name){
        String returnStr = "Welcome "+name;

        switch(role) {
            case "Officer":
                returnStr += " (Officer)";
                break;
            case "Manager":
                returnStr += " (Manager)";
                break;
            case "Applicant":
                returnStr += " (Applicant)";
                break;
        }

        return returnStr;
    }
}
