package Classes;

import java.util.Random;

public class randomGen {

    int enquiryIDLength;
    int projectIDLength;

    public randomGen(int enquiryIDLength, int projectIDLength) {
        this.enquiryIDLength = enquiryIDLength;
        this.projectIDLength = projectIDLength;
    }

    protected String getSaltString(int len) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        while (salt.length() < len) { 
            salt.append(SALTCHARS.charAt(rnd.nextInt(SALTCHARS.length())));
        }

        String saltStr = salt.toString();
        return saltStr;
    }

    public String genEnquiryID(){
        return getSaltString(this.enquiryIDLength);
    }

    public String genProjectID(){
        return getSaltString(this.projectIDLength);
    }
}
