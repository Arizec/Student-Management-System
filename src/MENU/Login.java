package MENU;

import java.util.*;

/**
 * Created by Martin on 2/09/2016.
 */
public class Login {

    private void loginMenu() {
        Scanner reader = new Scanner(System.in);

        // Logs user into the system
        System.out.println("Welcome to the system!");
        System.out.println("Username: ");
        String username = reader.nextLine();
        System.out.println("Password: ");
        String password = reader.nextLine();


        /* run student menu */
        if(username.charAt(0)){
            System.out.println("Testing that it works");
        }

        // run coordinator menu
        else if(usertype[1]== c){

        }

        // run admin menu
        else if(usertype[1]== a){

        }

    }


}
