import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.List;
import java.util.stream.Collectors;

public class StudentRegister {

    private ArrayList<Student> students_;
    private ArrayList<Course> courses_;
    private TreeMap<String,ArrayList<Attainment>> attainments_;
    private ArrayList<Attainment> all_attainments_;
    
    public StudentRegister(){
        this.students_ = new ArrayList<>();
        this.courses_ = new ArrayList<>();
        this.attainments_ = new TreeMap<>();
        this.all_attainments_ = new ArrayList<>();
    }
    
    public ArrayList<Student> getStudents(){
        ArrayList<Student> sorted = students_;
        Comparator<Student> compare = Comparator.comparing(Student::getName);
        Collections.sort(sorted,compare);
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
        String number = att.getStudentNumber();
        if(!attainments_.containsKey(number)){
            ArrayList<Attainment> array = new ArrayList<>();
            attainments_.put(number,array);
        }
        ArrayList<Attainment> attainments = attainments_.get(number);
        attainments_.get(number).add(att);
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
        System.out.format("%s (%s)%n",student.getName(),studentNumber);
        
        if(order.equals("by code")){
            ArrayList<Attainment> attainments = attainments_.get(studentNumber);
            Comparator<Attainment> compare = Comparator.comparing(Attainment::getCourseCode);
            List<Attainment> sorted = attainments.stream().sorted(compare).collect(Collectors.toList());            
            String coursename = "";
            for(var att : sorted){
                for(var course : courses_){
                    coursename = "";
                    if(course.getCode().equals(att.getCourseCode())){
                        coursename = course.getName();
                        break;
                    }
                }
                System.out.format("  %s %s: %s%n",att.getCourseCode(),coursename,att.getGrade());
            }
            
        }
        else if(order.equals("by name")){

            ArrayList<Attainment> attainments = attainments_.get(studentNumber);
            TreeMap<String,Attainment> attains = new TreeMap<>();
            for(var att : attainments){
                for(var course : courses_){
                    if(course.getCode().equals(att.getCourseCode())){
                        attains.put(course.getName(),att);
                    }
                }
            }
            for(var course : attains.entrySet()){
                System.out.format("  %s %s: %s%n",course.getValue().getCourseCode(),course.getKey(),course.getValue().getGrade());
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
        System.out.format("%s(%s)%n",student.getName(),studentNumber);
        ArrayList<Attainment> attainments = attainments_.get(studentNumber);
        for(var att : attainments){
            String coursename = "";
            for(var course : courses_){
                if(course.getCode().equals(att.getCourseCode())){
                    coursename = course.getName();
                    break;
                }
            }
            System.out.format("  %s %s: %s%n",att.getCourseCode(),coursename,att.getGrade());
        }
    }
    
    
}
