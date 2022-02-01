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
        this.attainments_ = new ArrayList<>();
    }
    
    public ArrayList<Student> getStudents(){
        ArrayList<Student> sorted = students_;
        Comparator<Student> compare = Comparator.comparing(Student::getName);
        Collections.sort(sorted,compare);git s
        return sorted;
    }
    public ArrayList<Course> getCourses(){
        ArrayList<Course> sorted = courses_;
        Comparator<Course> compare = Comparator.comparing(Course::getName);
        Collections.sort(sorted,compare);
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
    public void addAttainment(Attainment att){
        attainments_.add(att);
        return;
    }
    public void printStudentAttainments(String studentNumber, String order){
        
        Student student = null;
        
        
        for(var current : students_){
            var id = current.getStudentNumber();
            
            if (id.equals(studentNumber)){
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
                System.out.format("  %s %s: %s%n",course.getName(),course.getCode(),course.getCredits());
            }
        }
        else if(order.equals("by code")){
            ArrayList<Course> sorted = courses_;
            Comparator<Course> compare = Comparator.comparing(Course::getName);
            Collections.sort(sorted,compare);
            for(var course : sorted){
                System.out.format("  %s %s: %s%n",course.getName(),course.getCode(),course.getCredits());
            }
        }
        
    }
    public void printStudentAttainments(String studentNumber){
        
        Student student = null;
        
        for(var current : students_ ){
            var id = current.getStudentNumber();
            
            if (id.equals(studentNumber)){
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
            System.out.format("  %s %s: %s%n",course.getName(),course.getCode(),course.getCredits());            
        }
    }
    
    
}
