package PROGRAMS;

/**
 * Created by Martin +updated by Gabby on 2/09/2016.
 */

/**
 * Base program class. This should be extended to add specific functionality.
 */

import PROGRAMS.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProgramCourses {

    //information relating to/contained in Programs
    private String programCode;
    private String programName;
    private String programVersion;
    private int creditsNeeded;
    private String programType;
    private Boolean status;

    ArrayList<Courses> courseList =  new ArrayList<Courses>();



    public ProgramCourses(String programCode, String programName, String programVersion, int creditsNeeded, String programType, boolean status){
        this.programCode = programCode;
        this.programName = programName;
        this.programVersion = programVersion;
        this.creditsNeeded = creditsNeeded;
        this.programType = programType;
        this.status = status;
    }

    public int getCreditsNeeded(){
        return creditsNeeded;
    }

    public String getProgramCode(){
        return programCode;
    }

    public String getProgramName(){
        return programName;
    }

    public String getProgramType(){
        return programType;
    }


    public void setProgramName(String newprogramName){
        programName = newprogramName;

    }

    public void setProgramCode(String newprogramCode){
        programCode = newprogramCode;

    }

    public void setProgramCredit(int newprogramCredit){
        creditsNeeded = newprogramCredit;

    }

    public String getStatus(){
        if(status == true){
            return "Active";

        }else{

            return "Down";
        }

    }

    /*
     * Assign specific courses to a certain program depending on program type
     */
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

    //retrieves core courses associated to program to print
    public void printCourseDetails(){
        for(int i = 0; i< courseList.size(); i++){
            courseList.get(i).printCourse();

        }
    }

    //retrieves specialization courses associated to program to print
    public void printSpecialCourseDetails(){
        for(int i = 0; i< courseList.size(); i++){
            courseList.get(i).printSpecialCourses();

        }
    }

    /*
     * concatonate everything together
     */
    public String toString(){
        return programCode + ":" + programVersion + ":" + Integer.toString(creditsNeeded) + ":" + programType + ":" + getStatus();

    }

}
