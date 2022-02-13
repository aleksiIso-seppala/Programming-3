
public class ValueNode extends Node{

    private Double doublevalue_ = null;
    private Boolean booleanvalue_ = null;
    private String stringvalue_ = null;
    
    public ValueNode(double value){
        this.doublevalue_ = value;
    }
    public ValueNode(boolean value){
        this.booleanvalue_ = value;
    }
    public ValueNode(String value){
        this.stringvalue_ = value;
    }
    
    public boolean isNumber(){
        if(doublevalue_ == null){
            return false;
        }
        return true;
    }
    public boolean isBoolean(){
        if(booleanvalue_ == null){
            return false;
        }
        return true;
    }
    public boolean isString(){
        if(stringvalue_ == null){
            return false;
        }
        return true;
    }
    public boolean isNull(){
        if(stringvalue_ == null && 
           booleanvalue_ == null && 
           doublevalue_ == null){
            return true;
        }
        return false;
    }
    
    public double getNumber(){
        if(!isNumber()){
            return 0;
        }
        return doublevalue_;
    }
    public boolean getBoolean(){
        if(!isBoolean()){
            return false;
        }
        return booleanvalue_;
    }
    public String getString(){
        if(!isString()){
            return "";
        }
        return stringvalue_;
    }
}
    
