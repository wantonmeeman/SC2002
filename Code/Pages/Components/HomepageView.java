package Pages.Components;

/**
 * The interface Homepage view.
 */
public interface HomepageView {
    /**
     * Applicant homepage string.
     *
     * @return the string
     */
    static String applicantHomepage(){
        return Seperator.seperate()+"\n"+
        "1. Logout"+"\n"+
        "2. Projects"+"\n"+
        "3. Enquiries"+"\n"+
        "4. Applications"+"\n"+
        "5. Profile";
    }

    /**
     * Officer homepage string.
     *
     * @return the string
     */
    static String officerHomepage(){
        return Seperator.seperate()+"\n"+
                "1. Logout"+"\n"+
                "2. Projects"+"\n"+
                "3. Enquiries"+"\n"+
                "4. Applications" + "\n" +
                "5. Registration"  + "\n" +
                "6. Profile";
    }

    /**
     * Manager homepage string.
     *
     * @return the string
     */
    static String managerHomepage(){
        return Seperator.seperate() + "\n" +
                "1. Logout"+ "\n"+
                "2. Projects(Admin)"+"\n"+
                "3. Enquiries(Admin)"+"\n"+
                "4. Profile";
    }
}
