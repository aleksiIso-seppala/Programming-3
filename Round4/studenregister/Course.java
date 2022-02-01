
public class Course {
    
    public String code_;
    public String name_;
    public int credits_;
    
    public Course(String code, String name, int credits){
        this.code_ = code;
        this.name_ = name;
        this.credits_ = credits;
    }
    
    public String getName(){
        return name_;
    }
    public String getCode(){
        return code_;
    }
    public int getCredits(){
        return credits_;
    }
    
}
