
public class Attainment {
    
    public String courseCode_;
    public String studentNumber_;
    public int grade_;
    
    public Attainment(String courseCode, String studentNumber, int grade){
        this.courseCode_ = courseCode;
        this.studentNumber_ = studentNumber;
        this.grade_ = grade;
    }
    
    public String getCourseCoede(){
        return courseCode_;
    }
    public String getStudentNumber(){
        return studentNumber_;
    }
    public int getGrade(){
        return grade_;
    }

}
