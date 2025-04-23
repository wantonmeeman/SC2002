package Pages.Components;

public interface HomepageView {
    static String applicantHomepage(){
        return Seperator.seperate()+"\n"+
        "1. Logout"+"\n"+
        "2. Projects"+"\n"+
        "3. Enquiries"+"\n"+
        "4. Applications";
    }

    static String officerHomepage(){
        return applicantHomepage() + "\n" +
                "5. Registration";
    }

    static String managerHomepage(){
        return Seperator.seperate() + "\n" +
                "1. Logout"+ "\n"+
                "2. Projects(Admin)"+"\n"+
                "3. Enquiries(Admin)";
    }
}
