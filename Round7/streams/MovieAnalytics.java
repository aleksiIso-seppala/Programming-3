import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MovieAnalytics {
    
    private ArrayList<Movie> movies;
    private Consumer<Movie> object;
    
    public MovieAnalytics(){
        this.movies = new ArrayList<>();
    }
    public static final Consumer<Movie> showInfo(){
        Consumer<Movie> object = new Consumer<Movie>(){
            @Override
            public void accept(Movie t){
                System.out.format("%s (%s %s)%n",t.getTitle(),t.getDirector(),t.getReleaseYear());  
            }
        };
        return object;
    }
    
    public void populateWithData(String fileName) throws IOException{
        
        try(var file = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = file.readLine()) != null) {
                var split = line.split(";");
                int releaseyear = Integer.parseInt(split[1]);
                int duration = Integer.parseInt(split[2]);
                double score = Double.parseDouble(split[4]);
                var movie = new Movie(split[0],releaseyear,duration,split[3],score,split[5]);
                movies.add(movie);
            }
        }
    }
    public Stream<Movie> moviesAfter(int year){
        
        Comparator<Movie> compare = Comparator.comparing(Movie::getReleaseYear)
                                   .thenComparing(Movie::getTitle);
        return movies.stream().sorted(compare).filter(m->m.getReleaseYear() >= year);
        
    }
    public Stream<Movie> moviesBefore(int year){
        
        Comparator<Movie> compare = Comparator.comparing(Movie::getReleaseYear)
                                   .thenComparing(Movie::getTitle);
        return movies.stream().sorted(compare).filter(m->m.getReleaseYear()<=year);
    }
    public Stream<Movie> moviesBetween(int yearA, int yearB){
        
        Comparator<Movie> compare = Comparator.comparing(Movie::getReleaseYear)
                                   .thenComparing(Movie::getTitle);
        return movies.stream().sorted(compare).filter(m->m.getReleaseYear()>=yearA)
                                              .filter(m->m.getReleaseYear()<=yearB);
        
    }
    public Stream<Movie> moviesByDirector(String director){
        
        Comparator<Movie> compare = Comparator.comparing(Movie::getReleaseYear)
                                   .thenComparing(Movie::getTitle);
        return movies.stream().sorted(compare).filter(m->m.getDirector()==director);                
    }
}
