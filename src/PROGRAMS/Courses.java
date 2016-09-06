package PROGRAMS;

/**
 * Created by Martin on 6/09/2016.
 */
public class Courses {

    private String courseName;
    private String courseCode;
    private int creditsworth;
    private String programLink;



    public Courses(String courseCode, String courseName, int creditsworth, String programLink){
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditsworth = creditsworth;
        this.programLink = programLink;

    }

    public String getCourseCode(){
        return courseCode;

    }

    public int getCredits(){
        return creditsworth;
    }

    public String getProgramLink(){
        return programLink;

    }

    public String getCourseName(){
        return courseName;

    }

    public void printCourse(){
        System.out.println("-------------------------------------------");
        System.out.println("Course Title: " + courseName);
        System.out.println("Course Code: " + courseCode);
        System.out.println("Credits Worth: " + Integer.toString(getCredits()));
        System.out.println("-------------------------------------------");

    }

    public String toString(){
        return courseName + ":" + courseCode + ":" + Integer.toString(getCredits()) + ":" + programLink;

    }








}
