package Screen;

import java.util.ArrayList;
import java.util.Scanner;

import Classes.User;

public class HomeScreen extends Screen<User,String,String> {

    Scanner scanner;
    StringBuilder sb;
    Integer 

    public HomeScreen() {
    }

    @Override
    public Integer start(User user, Scanner sc) {
        this.scanner = sc;
        this.sb = new StringBuilder();

        return this.displayHomescreen();
    };

    private Integer displayHomescreen(){
        this.sb
        .append("1.Exit \n")
        .append("2.View Projects\n")
        .append("3.View Project Applied for\n")
        .append("4.View Enquiries\n");

        System.out.print(this.sb.toString());
        this.sb.setLength(0);
        
        String UserInput = scanner.nextLine();

        if(UserInput.length() == 1){
            


        }else{
            displayHomescreen();
        }
    }
;
}
