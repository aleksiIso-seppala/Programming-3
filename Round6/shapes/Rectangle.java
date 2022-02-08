
public class Rectangle implements IShapeMetrics {
    
    private double height_;
    private double width_;
    
    public Rectangle(double height, double width){
        this.height_ = height;
        this.width_ = width;
    }
    public String toString(){
        String line = String.format("Rectangle with height %.2f and width %.2f",height_,width_);
        return line;
    }
    public String name(){
        String line = "rectangle";
        return line;
    }
    public double area(){
        return height_*width_;
    }
    public double circumference(){
        return 2*height_ + 2*width_;
    }
}
