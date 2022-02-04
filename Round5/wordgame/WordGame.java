import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class WordGame {
    
    private ArrayList<String> words_;
    private String wordcomplete_;
    private WordGameState gamestate_;
    private boolean gameActive_;
    
    public class WordGameState{
        
        private String word_;
        private int mistakes_;
        private int mistakeLimit_;
        private int missingChars_;
        private TreeSet<Character> guessed_chars_;
        
        private WordGameState(){
            this.guessed_chars_ = new TreeSet<>();
            this.mistakes_ = 0;
            this.word_ = "";
            this.mistakeLimit_ = 0;
            this.missingChars_ = 0;
        }
        public String getWord(){
            return word_;
        }
        public int getMistakes(){
            return mistakes_;
        }
        public int getMistakeLimit(){
            return mistakeLimit_;
        }
        public int getMissingChars(){
            return missingChars_;
        }
        
    }
    
    public WordGame(String wordFilename) throws IOException{
        
        this.words_ = new ArrayList<>();
        
        try(var file = new BufferedReader(new FileReader(wordFilename))){
            String line;
            while((line = file.readLine()) != null){
                words_.add(line);
            }
        }
        
    }
    
    public void initGame(int wordIndex, int mistakeLimit){
        
        int index = wordIndex % words_.size();
        this.wordcomplete_ = words_.get(index);
        this.gamestate_ = new WordGameState();
        gamestate_.mistakeLimit_ = mistakeLimit;
        gamestate_.missingChars_ = wordcomplete_.length();
        
        for(int i=0;i<wordcomplete_.length();i++){
            gamestate_.word_ += "_";
        }
        this.gameActive_ = true;
        
    }
    public boolean isGameActive(){
        return gameActive_;
    }
    public WordGameState getGameState() throws GameStateException{
        
        throwgame();
        return gamestate_;
    }
    
    public WordGameState guess(char c) throws GameStateException{
        
        throwgame();
        Character chara = Character.valueOf(c);
        chara = Character.toLowerCase(chara);
        
        if(gamestate_.guessed_chars_.contains(chara)){
            gamestate_.mistakes_ += 1;
            return gamestate_;
        }
        
        gamestate_.guessed_chars_.add(c);
        boolean correct_guess = false;
        var word = gamestate_.word_.toCharArray();
        
        for(int i=0;i<wordcomplete_.toCharArray().length;i++){
            if (wordcomplete_.charAt(i) == chara){
                gamestate_.missingChars_ -= 1;
                word[i] = chara;
                correct_guess = true;
            }
        }
        if(!correct_guess){
            gamestate_.mistakes_ += 1;
            if(gamestate_.mistakes_ > gamestate_.mistakeLimit_){
                gameActive_ = false;
                gamestate_.word_ = wordcomplete_;
            }
            return gamestate_;
        }
        String new_word = "";
        for(var character : word){
            new_word += character;
        }
        gamestate_.word_ = new_word;
        
        if(gamestate_.missingChars_ == 0){
            gameActive_ = false;
        }
        return gamestate_;
        
    }
    
    public WordGameState guess(String word) throws GameStateException{
        
        throwgame();
        
        if(wordcomplete_.equals(word)){
            gamestate_.missingChars_ = 0;
            gamestate_.word_ = word;
            gameActive_ = false;
            return gamestate_;
        }
        else{
            gamestate_.mistakes_ += 1;
            if(gamestate_.mistakes_ > gamestate_.mistakeLimit_){
                gameActive_ = false;
                gamestate_.word_ = wordcomplete_;
            }
            return gamestate_;
        }
    }
    
    private void throwgame() throws GameStateException{
        
        if(!gameActive_){
            throw new GameStateException(String.format("There is currently no active word game!"));
        }
        
    }  
}
