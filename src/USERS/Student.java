package USERS;

import Driver.ProgramDriver;
import PROGRAMS.Courses;
import PROGRAMS.ProgramCourses;

import java.io.*;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.Random;
import java.util.StringJoiner;

/**
 * Created by Martin on 2/09/2016.
 */
public class Student {

    private String studentID;
    private String fullName;
    private String dob;
    private String programCode;
    private char type;
    private int credit;

    ArrayList<Courses> courseList =  new ArrayList<Courses>();
    ArrayList<Courses> coursesEnrolled =  new ArrayList<Courses>();
    ArrayList<Courses> coursesCompleted = new ArrayList<Courses>();



    public Student(String studentID, String fullName, String programCode, String dob, int credit, char type){
        this.studentID = studentID;
        this.fullName = fullName;
        this.programCode = programCode;
        this.dob = dob;
        this.credit = credit;
        this.type = type;
    }

    public String getStudentID(){

        return studentID;
    }

    public String getType(){
        if(type == 'B'){
            return "Bachelor";

        }
        if(type == 'D'){
            return "Diploma";

        }
        if(type == 'M'){
            return "Master";

        }
        if(type == 'H'){
            return "Honours";

        }
        return "Fail";

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


                        courseList.add(course);




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

    public void viewCurrentEnrolments(){
        for(int i = 0; i < coursesEnrolled.size(); i++){
            System.out.println(coursesEnrolled.get(i).toString());
        }

    }

    public void viewPastEnrolments(){


        int ranNum;



        for(int i = 0; i < coursesCompleted.size(); i++){
            Random random = new Random();
            ranNum = random.nextInt(100);
            System.out.print(coursesCompleted.get(i).getCourseName());
            if(80<= ranNum && ranNum <100 ){
                System.out.println("\t"+ranNum + "\tHD");

            }
            else if(70 <= ranNum && ranNum <80 ){
                System.out.println("\t"+ranNum + "\tD");


            }
            else if(60 <= ranNum && ranNum <70 ){
                System.out.println("\t"+ranNum + "\tC");



            }
            else{
                System.out.println("\t"+50 + "\tP");


            }


        }

    }


    public boolean enrolCourses(){

        int ranNum;

            /* go through courses already enrolled in */
        for(int j = 0; j < 4; j++){
            Random random = new Random();
            ranNum = random.nextInt(courseList.size());
            if(coursesEnrolled.isEmpty()){
                coursesEnrolled.add(courseList.get(ranNum));
            }else{

                for(int i =0 ; i<coursesEnrolled.size(); i++){
                    if(!courseList.get(ranNum).getCourseCode().equals(coursesEnrolled.get(i).getCourseCode())){
                        coursesEnrolled.add(courseList.get(ranNum));
                        break;
                    }

                }

            }


            // read the type of program student is enrolled in
            // Randomise past results based on degree type
            // If B, 0- 6 * 4
            // If H/D 0-2 * 4
            // If M 0-4 * 4
            // randomise from 1 * 4 to max


        }




        return true;
    }

    public boolean enrolPastResults(){

        int numCoursesFinished = credit / 12;

        int ranNum;
        boolean cheat = false;

        for(int j = 0; j < numCoursesFinished; j++) {
            Random random = new Random();
            ranNum = random.nextInt(courseList.size());
            if (coursesCompleted.isEmpty()) {
                for (int z = 0; z < coursesEnrolled.size(); z++) {
                    if(!coursesEnrolled.get(z).getCourseCode().equals(courseList.get(ranNum).getCourseCode())){
                        coursesCompleted.add(courseList.get(ranNum));
                        break;
                    }
                }
            } else {

                for (int m = 0; m < coursesEnrolled.size(); m++) {
                    if(!coursesEnrolled.get(m).getCourseCode().equals(courseList.get(ranNum).getCourseCode())){
                        for(int c =0; c<coursesCompleted.size(); c++){
                            if(coursesCompleted.get(c).getCourseCode().equals(courseList.get(ranNum).getCourseCode())){


                                continue;

                            }else{

                                coursesCompleted.add(courseList.get(ranNum));
                                cheat = true;
                                break;
                            }

                        }

                        if(cheat){
                            cheat = false;
                            break;

                        }
                    }
                }

            }

        }
        return true;

    }



    public void completeCourses(){


    }



}
