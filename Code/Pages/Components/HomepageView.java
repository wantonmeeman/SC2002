package Pages.Components;

public interface HomepageView {
    static String applicantHomepage(){
        return Seperator.seperate()+"\n"+
        "1. Logout"+"\n"+
        "2. Projects"+"\n"+
        "3. Enquiries"+"\n"+
        "4. Applications"+"\n"+
        "5. Profile";
    }

    static String officerHomepage(){
        return Seperator.seperate()+"\n"+
                "1. Logout"+"\n"+
                "2. Projects"+"\n"+
                "3. Enquiries"+"\n"+
                "4. Applications" + "\n" +
                "5. Registration"  + "\n" +
                "6. Profile";
    }

    static String managerHomepage(){
        return Seperator.seperate() + "\n" +
                "1. Logout"+ "\n"+
                "2. Projects(Admin)"+"\n"+
                "3. Enquiries(Admin)"+"\n"+
                "4. Profile";
    }
}
