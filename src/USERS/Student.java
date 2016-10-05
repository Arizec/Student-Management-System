package USERS;

import PROGRAMS.Courses;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Martin on 2/09/2016.
 */
public class Student {

    private String studentID;
    private String fullName;
    private String dob;
    private String programCode;

    ArrayList<Courses> coursesEnrolled =  new ArrayList<Courses>();


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

    public String getprogramCode(){
        return programCode;

    }

    public String getDob(){
        return dob;

    }

    public String toString(){
        return studentID + ":" + fullName + ":" + programCode + ":" + dob;
    }

    public void addCourses(String courseCodegiven){
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("courses.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file

                    String programTxt[] = x.split(":", 4);
                    String courseCode = programTxt[0];
                    String courseName = programTxt[1];
                    int creditsEarned = Integer.parseInt(programTxt[2]);
                    String programLink = programTxt[3];


                    if(courseCode.equals(courseCodegiven)){ // checks
                        Courses course = new Courses(courseCode, courseName, creditsEarned, programLink );
                        coursesEnrolled.add(course);

                    }

                }

                //runs out of line error
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Error if file cannot be found
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }



}
