import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
    
public class StudentRegister {

    public ArrayList<Student> students_;
    public ArrayList<Course> courses_;
    public ArrayList<Attainment> attainments_;
    
    public StudentRegister(){
        this.students_ = new ArrayList<>();
        this.courses_ = new ArrayList<>();
    }
    public ArrayList<Student> getStudent(){
        ArrayList<Student> sorted = new ArrayList<>();
        Comparator<Student> compare = Comparator.comparing(Student::getName);
        sorted = Collections.sort(students_,compare);
        return sorted;
    }
    public ArrayList<Course> getCourses(){
        Comparator<Course> compare = Comparator.comparing(Course::getName);
        ArrayList<Course> sorted = Collections.sort(courses_,compare);
        return sorted;
    }
    public void addStudent(Student student){
        students_.add(student);
        return;
    }
    public void addCourse(Course course){
        courses_.add(course);
        return;
    }
    public void addAttanment(Attainment att){
        attainments_.add(att);
        return;
    }
    public void printStudentAttainments(String studentNumber, String order){
        
        Student student = null;
        
        for(int i=0;i<students_.size();i++){
            var current = students_[i].getStudentNumber();
            
            if (current.equals(studentNumber)){
                student = current;
                break;
            }
        }
        if (student == null){
            System.out.println("Unknown student number: " + studentNumber);
            return;
        }
        System.out.format("studentName(&s)&n",studentNumber);
        
        if(order.equals("by name")){
            var courses = getCourses();
            for(var course : courses){
                System.out.format("  %s %s: %s%n",course.getName(),course.getCode(),course.getGrade);
            }
        }
        else if(order.equals("by code")){
            Comparator<Course> compare = Comparator.comparing(Course::getName);
            ArrayList<Course> sorted = Collections.sort(courses_,compare)
            for(var course : sorted){
                System.out.format("  %s %s: %s%n",course.getName(),course.getCode(),course.getGrade);
            }
        }
        
    }
    public void printStudentAttainments(String studentNumber){
        
        Student student = null;
        
        for(int i=0;i<students_.size();i++){
            var current = students_[i].getStudentNumber();
            
            if (current.equals(studentNumber)){
                student = current;
                break;
            }
        }
        if (student == null){
            System.out.println("Unknown student number: " + studentNumber);
            return;
        }
        System.out.format("studentName(&s)&n",studentNumber);
        
        for(var course : courses_){
            System.out.format("  %s %s: %s%n",course.getName(),course.getCode(),course.getGrade);            
        }
    }
    
    
}
