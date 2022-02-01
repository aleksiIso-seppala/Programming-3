
public class Attainment {
    
    private String courseCode_;
    private String studentNumber_;
    private int grade_;
    
    public Attainment(String courseCode, String studentNumber, int grade){
        this.courseCode_ = courseCode;
        this.studentNumber_ = studentNumber;
        this.grade_ = grade;
    }
    
    public String getCourseCode(){
        return courseCode_;
    }
    public String getStudentNumber(){
        return studentNumber_;
    }
    public int getGrade(){
        return grade_;
    }

}
