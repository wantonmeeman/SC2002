public class Main {
    public static void main(String[] args) {
        InputReader reader = new InputReader();
        String lines[] = reader.readText("../InputData/ApplicantList.txt").split("\\r?\\n");//gets \n or \r\n

        User[] userArr = new User[100];

        for(int x = 1;x < lines.length;x++){
            String tabs[] = lines[x].split("\\t");

            //userArr[x-1] = new User(tabs[1],tabs[0],tabs[2],tabs[3][0],tabs[4]);  //Yes I know i cant inst this, its because im too lazy to write the other classes yet 
        }

        while(true){

        }
    }
}
