package Screen;

import java.util.ArrayList;
import java.util.Scanner;

import Classes.Enquiry;
import Classes.User;

public class EnquiryScreen extends Screen<ArrayList<Enquiry>, ArrayList<Enquiry>> {

    StringBuilder sb;
    Scanner sc;
    User user;
    ArrayList<Enquiry> userEnquiryArr;
    ArrayList<Enquiry> enquiryArr;

    public EnquiryScreen(User user) {
        this.user = user;
    }

    @Override
    public ArrayList<Enquiry> start(ArrayList<Enquiry> enquiryArr, Scanner sc) {
        this.sb = new StringBuilder();
        this.sc = sc;
        this.userEnquiryArr = new ArrayList<Enquiry>();
        this.enquiryArr = enquiryArr;

        getUserEnq(enquiryArr);
        displayEnquiries();

        return enquiryArr;
    }

    private void getUserEnq(ArrayList<Enquiry> enquiryArr) {
        for (int x = 0; x < enquiryArr.size(); x++) {
            Enquiry enq = enquiryArr.get(x);
            if (enq.getUserID().equals(user.getUserID())) {
                userEnquiryArr.add(enq);
            }
        }
    }

    private void displayEnquiry(int index) {
        int userInputInteger = 0;
        while (userInputInteger != 1) {
            Enquiry enq = userEnquiryArr.get(index);

            sb.append(enq.toString());
            sb
                    .append("1. Exit")
                    .append("\n")
                    .append("2. Edit Message")
                    .append("\n")
                    .append("3. Delete")
                    .append("\n");

            System.out.print(sb.toString());
            sb.setLength(0);

            String userInput = sc.nextLine();
            userInputInteger = -1;

            try {
                userInputInteger = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.print("Error");//ERROR
            }

            if (userInputInteger == -1) {
                //Error handling
            } else if (userInputInteger == 2) {
                editMessage(index);
            } else if (userInputInteger == 3) {
                deleteMessage(index);
            }
        }
    }

    private void deleteMessage(int index) {
        Enquiry removedEnquiry = userEnquiryArr.remove(index);

        for (int x = 0; x < enquiryArr.size(); x++) {
            Enquiry enq = enquiryArr.get(x);
            if (enq.getEnquiryID().equals(removedEnquiry.getEnquiryID())) {
                enquiryArr.remove(x);
                break;
            }
        }
    }

    private void editMessage(int index) {
        sb
                .append("New Message: ")
                .append("\n");

        System.out.print(sb.toString());
        sb.setLength(0);

        String newMessage = sc.nextLine();

        Enquiry editedEnquiry = userEnquiryArr.get(index);

        for (int x = 0; x < enquiryArr.size(); x++) {
            Enquiry enq = enquiryArr.get(x);
            if (enq.getEnquiryID().equals(editedEnquiry.getEnquiryID())) {
                enq.setMessage(newMessage);
                editedEnquiry.setMessage(newMessage);
                break;
            }
        }
    }

    private void displayEnquiries() {
        sb
        .append("1. Exit")
        .append("\n");

        for (int x = 0; x < userEnquiryArr.size(); x++) {
            Enquiry enq = userEnquiryArr.get(x);
            sb
                    .append(x + 2)
                    .append(". ")
                    .append(enq.toString());
        }

        //User input to create new or choose which one to edit/delete
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
            displayEnquiry(userInputInteger-2);
        }
    }
};
