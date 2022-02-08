

public class Circle implements IShapeMetrics {
    
    private double radius_;
    
    public Circle(double radius){
        this.radius_ = radius;
    }
    
    public String toString(){
        String line = String.format("Circle with radius: %.2f", radius_);
        return line;
    }
    
    @Override
    public String name(){
        String line = "circle";
        return line;
    }
    
    @Override
    public double area(){
        double area = PI*(radius_*radius_);
        return area;
    }
    
    @Override
    public double circumference(){
        double result = 2*PI*radius_;
        return result;
    }
    
}