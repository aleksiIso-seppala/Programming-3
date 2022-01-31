import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;

public class Median{
    
    public static void main(String[] args){
        
        ArrayList<Double> numbers = new ArrayList<>();
               
        for(var number : args){
            Double tmp = Double.parseDouble(number);
            numbers.add(tmp);
        }
        
        Collections.sort(numbers);
        
        if(numbers.size() % 2 != 0){
            int middle = numbers.size() / 2;
            System.out.println("Median: " + numbers.get(middle));
        }
        else{
            int middle = numbers.size() / 2;
            Double median = (numbers.get(middle) + numbers.get(middle-1))/2;
            System.out.println("Median: " + median);
        }
    }
}
