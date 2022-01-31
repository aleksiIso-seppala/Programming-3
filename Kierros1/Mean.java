public class Mean{
    
    public static void main(String[] args){
        
        double sum = 0;
        int variables = 0;
        
        for(String number : args){
            double tmp = Double.parseDouble(number);
            sum += tmp;
            ++variables;
        }
        
        double mean = sum / variables;
        
        System.out.println("Mean: " + mean);
    }
}