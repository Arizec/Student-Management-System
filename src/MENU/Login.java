package MENU;

import java.util.*;

/**
 * Created by Martin on 2/09/2016.
 */
public class Login {

    public void loginMenu() {
        AdminMenu adminMenu = new AdminMenu();
        CoordinatorMenu coMenu = new CoordinatorMenu();
        FacultyMenu facMenu = new FacultyMenu();
        StudentMenu studentMenu = new StudentMenu();

        Scanner reader = new Scanner(System.in);

        // Logs user into the system
        System.out.println("Welcome to the system!");
        System.out.println("Username: ");
        String username = reader.nextLine();
        System.out.println("Password: ");
        String password = reader.nextLine();


        /* run student menu */
        if(username.charAt(0) == 's'){
            studentMenu.runMenu();
        }
        else if(username.charAt(0) == 'a'){


            adminMenu.runMenu();

        }
        else if(username.charAt(0) == 'c'){

            coMenu.runMenu();

        }
        else if(username.charAt(0) == 'f'){
            facMenu.runMenu();

        }



    }


}
