package PROGRAMS;

/**
 * Created by Martin on 6/09/2016.
 */
public class Courses {

    private String courseName;
    private String courseCode;
    private int creditsworth;
    private String programLink;



    public Courses(String courseCode, String courseName, int creditsworth, String programCode){
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditsworth = creditsworth;
        this.programLink = programCode;

    }

    public String getCourseCode(){
        return courseCode;

    }

    public String getProgramLink(){
        return programLink;

    }

    public String getCourseName(){
        return courseName;

    }








}
