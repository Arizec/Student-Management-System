package MENU;

import java.util.*;
import java.io.*;

/**
 * Created by Martin on 2/09/2016.
 */
public class Login {

    public void loginMenu() {
        AdminMenu adminMenu = new AdminMenu();
        CoordinatorMenu coMenu = new CoordinatorMenu();
        FacultyMenu facMenu = new FacultyMenu();


        Scanner reader = new Scanner(System.in);

        // Logs user into the system
        System.out.println("Welcome to the system! Enter your details below. ");

        while(true) {
            System.out.println("Username: ");
            String username = reader.nextLine();
            System.out.println("Password: ");
            String password = reader.nextLine();

            //login details are valid, proceed to associated menu
            if (findLoginDetails(username, password)) {


                //go to student menu
                if(username.charAt(0) == 's'){
                    StudentMenu studentMenu = new StudentMenu(username);
                	studentMenu.runMenu();
                }

                //go to admin menu
                else if(username.charAt(0) == 'a'){
                    adminMenu.runMenu();

                }

                //go to coordinator menu
                else if(username.charAt(0) == 'c'){
                    coMenu.runMenu();

                }

                //go to facaulty admin menu
                else if(username.charAt(0) == 'f'){
                    facMenu.runMenu();

                }
                break;

            //invalid login details, ask for it again
            }else{

                System.out.println("Invalid Username or Password");

            }
        }

    }

    /* see if login account exists */
    private boolean findLoginDetails(String username, String password){
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("loginDetails.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String loginDetails[] = x.split(":",2);
                    if(username.equals(loginDetails[0])){

                        if(password.equals(loginDetails[1])){

                            return true;
                        }
                        else{

                            return false;

                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return false;

    }


}
