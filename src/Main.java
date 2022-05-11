import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        User u1 = new User("admin", "Password123!");

        System.out.println("\nWelcome to the Quiz");
        System.out.println("\nYou need to Login to take part");

//      User chooses to either login or register
        String choice="";
        while(!choice.equals("4")){
            System.out.println("\nDo you want to:\n1. Login\n2. Register\n3. Show Leaderboard\n4. Exit");
            Scanner userChoice = new Scanner(System.in);
            choice = userChoice.nextLine();

//          Choice checks
            if (choice.equals("1")){
                String currentUser = u1.login();

                if (!currentUser.equals("locked")){
                    Quiz q1 = new Quiz(currentUser);
                    q1.quiz();

                }else{
                    choice="4";
                }

            }else if (choice.equals("2")){
                u1.register();

            }else if (choice.equals("3")){
                Quiz q2 = new Quiz("none");
                q2.showLeaderboard();

            }else if (choice.equals("4")){

            }else{
                System.out.println("Enter either 1 to Login, 2 to Register, 3 to show the Leaderboard or 4 to Exit");
            }
        }
    }
}
