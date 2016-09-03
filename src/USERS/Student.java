package USERS;

/**
 * Created by Martin on 2/09/2016.
 */
public class Student {

    private String studentID;
    private String fullName;
    private String dob;
    private String programCode;


    public Student(String studentID, String fullName, String programCode, String dob){
        this.studentID = studentID;
        this.fullName = fullName;
        this.programCode = programCode;
        this.dob = dob;
    }

    public String getStudentID(){

        return studentID;
    }

    public String getFullName(){
        return fullName;

    }

    public String toString(){
        return studentID + ":" + fullName + ":" + programCode + ":" + dob;
    }





}
