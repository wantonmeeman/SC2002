package Screen;

import java.util.Scanner;

import Classes.User;

public class HomeScreen extends Screen<User,Integer> {

    Scanner scanner;
    StringBuilder sb;

    public HomeScreen() {
    }

    @Override
    public Integer start(User user, Scanner sc) {
        this.scanner = sc;
        this.sb = new StringBuilder();

        return this.displayHomescreen();
    };

    private Integer displayHomescreen(){
        sb
        .append("1.Exit \n")
        .append("2.View Projects\n")
        //.append("3.View Project Applied for\n")
        .append("3.View Enquiries\n");

        System.out.print(sb.toString());
        sb.setLength(0);
        
        String userInput = scanner.nextLine();
        int userInputInteger = -1;

        if(userInput.length() == 1){
            try{
                userInputInteger = Integer.parseInt(userInput);
            }catch(NumberFormatException e){
                System.out.print("Error");//ERROR
            }
            return userInputInteger;    
        }else{
            return displayHomescreen();
        }
    }
;
}
