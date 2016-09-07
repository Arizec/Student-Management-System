
import MENU.Login;
import java.util.*;

/**
 * Base of program. User logs in, and a menu is shown to them
 * depending on their user type
 */
public class main {

    public static void main(String[] args) {

        Login TestLogin =  new Login();

        //User logs into menu
        TestLogin.loginMenu();

    }


}
