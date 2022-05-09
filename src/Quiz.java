import java.util.Scanner;
import java.util.regex.Pattern;

public class Quiz {

    //  Blank field acceptable as program will not allow a blank input, see line 10 function
    static String[] usernames = {"admin", ""};
    static String[] passwords = {"Password123!", ""};

    //  Returns String that is not blank
    public static String getNotBlank(){
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
    public static String login(){
        boolean matchFound=false;
        boolean lockout=false;
        int attempts = 0;
        while(!matchFound && !lockout){
            attempts++;

//          Get username and password from user
            System.out.println("Enter username:");
            String inName = getNotBlank();
            System.out.println("Enter password:");
            String inPass= getNotBlank();

//          Check usernames and passwords array against the given username and password for a match
            for(int i=0; i< usernames.length; i++){
                if(inName.equals(usernames[i]) && inPass.equals(passwords[i])) {
                    matchFound = true;
                    System.out.println("\nWelcome "+inName+"!");
                    return(inName);
                }
            }

            if(!matchFound){
                System.out.println("Username or Password is incorrect");
                if (attempts==3){
                    lockout=true;
                    System.out.println("Account locked, please call us to resolve");
                }
            }
        }
        return("locked");
    }


    public static void register(){
        boolean valid=false;
        while(!valid){

//          Get username and password from user
            System.out.println("Enter username:");
            String inName = getNotBlank();
            System.out.println("Enter password:");
            String inPass = getNotBlank();

//          Set up patterns for character requirements checks
            Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Pattern upperCasePatten = Pattern.compile("[A-Z ]");
            Pattern lowerCasePatten = Pattern.compile("[a-z ]");
            Pattern digitCasePatten = Pattern.compile("[0-9 ]");

            if(inPass.length()>15){
                System.out.println("Passwords cannot be over 15 characters");
            }else if(inPass.length()<8){
                System.out.println("Passwords cannot be less than 8 characters");

//          Checks password against patterns
            }else if(!specialCharPatten.matcher(inPass).find() || !upperCasePatten.matcher(inPass).find() || !lowerCasePatten.matcher(inPass).find() || !digitCasePatten.matcher(inPass).find()){
                System.out.println("Password must contain at least 1 uppercase, lowercase, special and number character");

            }else{
                valid=true;
                usernames[1]=inName;
                passwords[1]=inPass;
                System.out.println("Login");
                login();
            }
        }
    }

    public static void accountChoice(){
        System.out.println("\nDo you want to:\n1. Login\n2. Register");

//      User chooses to either login or register
        String choice="";
        while(!choice.equals("1") && !choice.equals("2")){
            Scanner userChoice = new Scanner(System.in);
            choice = userChoice.nextLine();
            if (choice.equals("1")) {
                String loggedUname = login();
                if (!loggedUname.equals("locked")){
                    quiz(loggedUname);
                }
            } else if (choice.equals("2")) {
                register();
            } else {
                System.out.println("Enter either 1 to login or 2 to register");
            }
        }
    }

    public static void quiz(String uname){
        int score=0;
        System.out.println("\nToday's Quiz is going to be about Minecraft\n");

        String question[] = {"Question 1.\nHow many blocks does it take to make a nether portal?\n", "Question 2.\nHow many wooden planks does it take to make a boat?\n", "Question 3.\nWhats rule 1 of minecraft?\n"};
        String answers[][] = {{"15","12","10","9"}, {"6","5","4","3"}, {"Dont dig straight down","Always shoot creepers with a bow","Dont mine at night","Build house"}};

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

        System.out.println(uname+ " got a score of " + score);
    }

    public static void main(String[] args) {
        System.out.println("\nWelcome to the Quiz");
        System.out.println("First you need an account");
        accountChoice();
    }
}
