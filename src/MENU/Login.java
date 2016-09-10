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
        StudentMenu studentMenu = new StudentMenu();

        Scanner reader = new Scanner(System.in);

        // Logs user into the system
        System.out.println("Welcome to the system! Enter your details below. ");

        while(true) {
            System.out.println("Username: ");
            String username = reader.nextLine();
            System.out.println("Password: ");
            String password = reader.nextLine();

            if (findLoginDetails(username, password)) {



                if(username.charAt(0) == 's'){
                	String studentID=username;
                	studentMenu.runMenu(studentID);
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
                break;


            }else{

                System.out.println("Invalid Username or Password");

            }
        }

    }

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
