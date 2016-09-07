package PROGRAMS;

/**
 * Contains everything related to courses
 */
public class Courses {

    // Information associated to courses
    private String courseName;
    private String courseCode;
    private int creditsworth; //credits a course is worth
    private String programLink; //Faculty


    // creates objects of courses
    public Courses(String courseCode, String courseName, int creditsworth, String programLink){
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditsworth = creditsworth;
        this.programLink = programLink;

    }

    //retrieve course code
    public String getCourseCode(){
        return courseCode;

    }

    //retrieve credits
    public int getCredits(){
        return creditsworth;
    }

    //retrieve faculty that course is associated to
    public String getProgramLink(){
        return programLink;

    }

    //retrieve course name
    public String getCourseName(){
        return courseName;

    }

    //print core courses
    public void printCourse(){
        System.out.println("-------------------------------------------");
        System.out.println("Course Title: " + courseName);
        System.out.println("Course Code: " + courseCode);
        System.out.println("Credits Worth: " + Integer.toString(getCredits()));
        System.out.println("-------------------------------------------");

    }
    //print specialization courses
    public void printSpecialCourses(){
        System.out.println("-------------------------------------------");
        System.out.println("Course Title: " + courseName);
        System.out.println("Course Code: " + courseCode);
        System.out.println("Credits Worth: " + Integer.toString(getCredits()));
        System.out.println("-------------------------------------------");
    }

    //concatonates course information together
    public String toString(){
        return courseName + ":" + courseCode + ":" + Integer.toString(getCredits()) + ":" + programLink;

    }








}
