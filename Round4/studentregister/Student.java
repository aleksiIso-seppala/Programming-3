
public class Student {
    
    private String name_;
    private String studentNumber_;
    
    public Student(String name, String studentNumber){
        this.name_ = name;
        this.studentNumber_ = studentNumber;
    }
    
    public String getName(){
        return name_;
    }
    
    public String getStudentNumber(){
        return studentNumber_;
    }
    
}
