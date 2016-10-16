package USERS;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Martin on 1/09/2016.
 */
public class Coordinator {

    private String staffID;
    private String fullName;
    private String dob;
    private String programCode;


    public Coordinator(String staffID, String fullName, String dob){
        this.staffID = staffID;
        this.fullName = fullName;
        this.dob = dob;
    }

    public void createStudentID(){

        try
        {

            String filename= "loginDetails.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            Scanner reader = new Scanner(System.in);


            System.out.println("Create Student ID");
            String studentID = reader.nextLine();
            System.out.println("Create Student Password");
            String password = reader.nextLine();

            String logindetails = studentID + ":" + password;

            fw.write(logindetails);//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }




}
