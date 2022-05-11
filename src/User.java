import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class User {
    List<String> usernames = new ArrayList<>();
    List<String> passwords = new ArrayList<>();

    String currentUser;

//   Constructor
    public User(String uname, String upass) {
        this.usernames.add(uname);
        this.passwords.add(upass);
    }

//  Getters
    public String getCurrentUser() {
        return currentUser;
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
    public String login(){
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

//          Check usernames and passwords list against the given username and password for a match
            for(int i=0; i< this.usernames.size(); i++){
                if(inName.equals(usernames.get(i)) && inPass.equals(passwords.get(i))) {
                    matchFound = true;
                    System.out.println("\nWelcome "+inName+"!");
                    this.currentUser = inName;
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


    public void register(){
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
                usernames.add(inName);
                passwords.add(inPass);
                System.out.println("Account Registered");
            }
        }
    }
}
