
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Comparator;
import java.util.Collections;


public class MovieAnalytics2 {

    private List<Movie> movies;
    
    public MovieAnalytics2(){
        this.movies = new ArrayList<>();
    }
    
    public void populateWithData(String fileName) throws IOException{
        
        try(var file = new BufferedReader(new FileReader(fileName))) {
            movies = file.lines().map(line -> line.split(";"))
                .map(mov -> new Movie(mov[0],Integer.parseInt(mov[1]),
                        Integer.parseInt(mov[2]),mov[3],Double.parseDouble(mov[4]),mov[5])).collect(Collectors.toList());
        }
    }
    
    public void printCountByDirector(int n){
        
        var test = movies.stream().collect(Collectors.groupingBy(Movie::getDirector,Collectors.counting()));
        
        test.entrySet().stream()
                .sorted(Collections
                        .reverseOrder(
                                Map.Entry.<String, Long>comparingByValue())
                        .thenComparing(Map.Entry.comparingByKey()))
        .limit(n).forEach(e->System.out.format("%s: %s movies%n",e.getKey(),e.getValue()));
        
    }
    
    public void printAverageDurationByGenre(){
        var test = movies.stream().collect(Collectors.groupingBy(Movie::getGenre,Collectors.averagingInt(e->e.getDuration())));
        
        test.entrySet().stream()
                .sorted(
                                Map.Entry.<String, Double>comparingByValue()
                        .thenComparing(Map.Entry.comparingByKey()))
        .forEach(e->System.out.format("%s: %.2f%n",e.getKey(),e.getValue())); 
        
    }
    
    public void printAverageScoreByGenre(){
        var test = movies.stream().collect(Collectors.groupingBy(Movie::getGenre,Collectors.averagingDouble(e->e.getScore())));
        
        test.entrySet().stream()
                .sorted(Collections
                        .reverseOrder(
                                Map.Entry.<String, Double>comparingByValue())
                        .thenComparing(Map.Entry.comparingByKey()))
        .forEach(e->System.out.format("%s: %.2f%n",e.getKey(),e.getValue())); 

    }
}
