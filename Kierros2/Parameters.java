import java.util.ArrayList;
import java.util.Collections;

public class Parameters { 

    public static void main(String args[]) {
        
        ArrayList<String> countries = new ArrayList<>();
        
        int longest_name = 0;
        for(String country : args){
            if(country.length() > longest_name){
                longest_name = country.length();
            }
            countries.add(country);            
        }
        
        Collections.sort(countries);
        
        int amount = countries.size();
        
        String number = String.valueOf(amount);
        
        int max_amount = number.length();
        
        for(int i=0; i<=amount;i++){
            if(i==0 || i==amount){
                for(int j=0;j<longest_name+max_amount+7;j++){
                    System.out.format("#");
                }
                System.out.println();
                if(i==amount){
                    continue;
                }
            }
            int amount_of_spaces = max_amount - String.valueOf(i+1).length();
            if( i != 0 ){
                System.out.format("#");
                for(int j=0;j<longest_name+max_amount+5;j++){
                    
                    if(j==max_amount+2){
                        System.out.format("+");
                        continue;
                    }
                    
                    System.out.format("-"); 
                }
                System.out.format("#%n");
                
            }

            
            System.out.format("#");
            for(int j=0;j<=amount_of_spaces;j++){
                System.out.format(" ");
            }
            System.out.format("%d |",i+1);
            
            System.out.format(" %s",countries.get(i));
            int remaining_spaces = longest_name - countries.get(i).length();
            for(int j=0;j<remaining_spaces;j++){
                System.out.format(" ");
            }
            System.out.println(" #");
        }
        
        
    }
}
