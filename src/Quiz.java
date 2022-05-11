import javax.sound.sampled.Line;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Quiz {

    String currentUser;

    public Quiz(String currentUser) {
        this.currentUser = currentUser;
    }

    //  Returns String that is not blank
    public String getNotBlank(){
        String userIn="";
        while(userIn.equals("")){
            Scanner reader = new Scanner(System.in);
            userIn = reader.nextLine();

            if(userIn.equals("")){
                System.out.println("Field cannot be blank, please re-enter");
            }
        }
        return(userIn);
    }
    public void quiz(){
        int score=0;
        System.out.println("\nToday's Quiz is going to be about Minecraft\n");

        String question[] = {"Question 1.\nHow many blocks does it take to make a nether portal?\n", "Question 2.\nHow many wooden planks does it take to make a boat?\n", "Question 3.\nWhats rule 1 of minecraft?\n"};
        String answers[][] = {{"1. 15","2. 12","3. 10","4. 9"}, {"1. 6","2. 5","3. 4","4. 3"}, {"1. Dont dig straight down","2. Always shoot creepers with a bow","3. Dont mine at night","4. Build house"}};

        for (int i=0; i< answers.length;i++){

//          Print Question
            System.out.println(question[i]);

//          Print answers corresponding to question index
            for (int j=0; j< answers[i].length;j++){
                System.out.println(answers[i][j]);
            }
            String answer = getNotBlank();

//          Check that answer is correct against question index
            if(i==0){
                if(answer.equals("3")){
                    score++;
                }
            }
            else if(i==1){
                if(answer.equals("2")){
                    score++;
                }
            }
            else{
                if(answer.equals("1")){
                    score++;
                }
            }
        }

        System.out.println(this.currentUser+ " got a score of " + score);
        saveScore(this.currentUser, score);
    }

    public void saveScore(String uname, int score){
        try {
            FileWriter fw = new FileWriter("scores.txt", true);
            fw.write(uname + " | " + score+"\n");
            fw.close();
            System.out.println("File write");
        } catch (IOException e) {
            System.out.println("Error");
        }
    }public void showLeaderboard(){
        try{
            File f = new File("scores.txt");
            Scanner readFile = new Scanner(f);
            System.out.println("\nLeaderBoard:");
            System.out.println("Username | Score");

            List<Integer> sortScores = new ArrayList<>();
            List<String> sortLines = new ArrayList<>();

            while(readFile.hasNextLine()){
                String line = readFile.nextLine();
                sortLines.add(line);

                String[] lines = line.split(" ");
                int numColumn = Integer.parseInt(lines[2]);
                sortScores.add(numColumn);
            }

            Collections.sort(sortScores, Collections.reverseOrder());
            for (int i=0; i<sortLines.size(); i++){
                for (int j=0; j<sortLines.size(); j++){
                    String line = sortLines.get(j);
                    String[] lines = line.split(" ");
                    int numColumn = Integer.parseInt(lines[2]);
                    if(sortScores.get(i) == numColumn){
                        System.out.println(sortLines.get(j));
                    }
                }
            }

        }catch(FileNotFoundException e){
            System.out.println("Score file not found");
        }

    }
}
