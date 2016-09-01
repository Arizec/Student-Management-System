import MENU.Menu;
import java.util.*;

/**
 * Created by Martin on 1/09/2016.
 */
public class main {


    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in); 
        
        // Logs user into the system 
        System.out.println("Welcome to the system!");
        System.out.println("Username: ");
        String username = reader.nextLine();
        System.out.println("Password: ");
        String password = reader.nextLine();
        
        // retrieve first letter of username
        String[] usertype = username.split("");
        usertype[2];
        
        /* run student menu */
        if(usertype== s){
            
        }
        
        // run coordinator menu 
        else if(usertype== c){
            
        }
        
        // run admin menu 
        else if(usertype== a){
            
        }
        
        Menu TestMenu = new Menu();
        TestMenu.runMenu();
        System.out.println("kek");
      

    }


}
