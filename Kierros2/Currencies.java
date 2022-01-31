import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.TreeMap;

public class Currencies {
    
    public static void main(String args[]) throws IOException {
        
        
        BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
        
        TreeMap<String, Double> converts = new TreeMap<>();
        
        
        OUTER:
        while (true) {
            System.out.format("Enter command: ");
            String input = user.readLine();
            System.out.format("%s%n",input);
            String[] parameters = input.split(" ");
            String command = parameters[0];
            
            
            String currency;
            Double rate;
            Double amount;
            switch (command) {
                

                case "rate":
                    currency = parameters[1].toUpperCase();
                    rate = Double.parseDouble(parameters[2]);

                    converts.put(currency, rate);
                    System.out.format("Stored the rate 1 EUR = %.3f %s%n",rate,currency);
                    break;
                    
                case "convert":
                    currency = parameters[2].toUpperCase();
                    amount = Double.parseDouble(parameters[1]);
                    
                    if(!converts.containsKey(currency)){
                        System.out.format("No rate for %s has been stored!%n",currency);
                        break;
                    }
                    
                    Double result = amount/converts.get(currency);
                    System.out.format("%.3f %s = %.3f EUR%n",amount, currency, result);
                    break;
                case "rates":
                    System.out.println("Stored euro rates:");
                    converts.entrySet().forEach(pair -> {
                        System.out.format("  %s %.3f%n",pair.getKey(),pair.getValue());
                });
                    break;

                case "quit":
                    System.out.println("Quit-command received, exiting...");
                    break OUTER;
                default:
                    System.out.println("Unknown or illegal command!");
                    break;
            }
        }
    }
}
