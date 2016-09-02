package USERS;

/**
 * Created by Martin on 2/09/2016.
 */
public class Student {

    private String studentID;
    private String fullName;
    private String password;
    private int credit;

    public Student(String studentID, String password, String fullName, int credit){
        this.studentID = studentID;
        this.password = password;
        this.fullName = fullName;
        this.credit = credit;
    }

    public String getStudentID(){

        return this.studentID;
    }





}
