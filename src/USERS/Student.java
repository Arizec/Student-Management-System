package USERS;

import Driver.ProgramDriver;
import PROGRAMS.Courses;
import PROGRAMS.ProgramCourses;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;

/**
 * Created by Martin on 2/09/2016.
 */
public class Student {

    private String studentID;
    private String fullName;
    private String dob;
    private String programCode;
    private int credit;

    ArrayList<Courses> courseList =  new ArrayList<Courses>();
    ArrayList<Courses> coursesEnrolled =  new ArrayList<Courses>();



    public Student(String studentID, String fullName, String programCode, String dob, int credit){
        this.studentID = studentID;
        this.fullName = fullName;
        this.programCode = programCode;
        this.dob = dob;
        this.credit = credit;
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
        return studentID + ":" + fullName + ":" + programCode + ":" + dob + ":" + Integer.toString(credit);
    }

    public void addCourses(){
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



                    Courses course = new Courses(courseCode, courseName, creditsEarned, programLink );
                    if(programCode.contains(course.getProgramLink())){ // checks

                        courseList.add(course);


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

    public void printCourses(){
        for(int i = 0; i < courseList.size(); i++){
            System.out.println(courseList.get(i).toString());
        }

    }

    public boolean enrolIntoCourse(String courseID){

        for(int i = 0; i < courseList.size(); i++){
            if(courseID.equals(courseList.get(i).getCourseCode())){
                if(!coursesEnrolled.isEmpty()){
                    for(int j = 0; j<coursesEnrolled.size(); j++){
                        if(coursesEnrolled.get(j).getCourseCode().equals(courseID)){
                            System.out.println("Already Enrolled Into Course");
                            return false;
                        }else{
                            coursesEnrolled.add(courseList.get(i));
                            System.out.println("Course Enrolled");
                            return true;

                        }

                    }

                }else{

                    coursesEnrolled.add(courseList.get(i));
                    System.out.println("Course Enrolled");
                    return true;
                }


            }
        }
        System.out.println("Incorrect Course ID");
        return false;
    }


    public void completeCourses(Courses course){


    }



}
