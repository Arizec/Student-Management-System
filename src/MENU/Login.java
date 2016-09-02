package MENU;

import java.util.*;

/**
 * Created by Martin on 2/09/2016.
 */
public class Login {

    public void loginMenu() {
        Scanner reader = new Scanner(System.in);

        // Logs user into the system
        System.out.println("Welcome to the system!");
        System.out.println("Username: ");
        String username = reader.nextLine();
        System.out.println("Password: ");
        String password = reader.nextLine();


        /* run student menu */
        if(username.charAt(0) == 's'){
            System.out.println("Student");
        }
        else if(username.charAt(0) == 'a'){

            System.out.println("Admin");

        }
        else if(username.charAt(0) == 'c'){

            System.out.println("Coordinator");

        }
        else if(username.charAt(0) == 'f'){
            System.out.println("Faculty Admin");
            
        }



    }


}
