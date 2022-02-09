import java.util.Comparator;

public class Attainment implements Comparable<Attainment>{

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
    
    @Override
    public int compareTo(Attainment other){
        
        int value = studentNumber_.compareTo(other.studentNumber_);
        if(value == 0){
            value = courseCode_.compareTo(other.courseCode_);
        }
        return value;
    }
    
    @Override
    public String toString(){
        String line = String.format("%s %s %s%n",courseCode_, studentNumber_,grade_);
        return line;
    } 

    public static final Comparator<Attainment> CODE_STUDENT_CMP = new Comparator<Attainment>(){
        
        public int compare(Attainment a, Attainment b){
            int value = a.courseCode_.compareTo(b.courseCode_);
            if(value == 0){
                value = a.studentNumber_.compareTo(b.studentNumber_);
            }
            return value;
            
        }
        
    };
            
    public static final Comparator<Attainment> CODE_GRADE_CMP = new Comparator<Attainment>(){
        
        public int compare(Attainment a, Attainment b){
            int value = a.courseCode_.compareTo(b.courseCode_);
            if(value == 0){
                value = (a.grade_ - b.grade_)*(-1);
            }
            return value;
        }
        
    };

}
