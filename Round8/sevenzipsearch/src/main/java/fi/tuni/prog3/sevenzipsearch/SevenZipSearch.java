package fi.tuni.prog3.sevenzipsearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

public class SevenZipSearch {

    private static int countOccurences(String haystack, String needle)
    {
    int index, lastIndex = -1, count = 0;

    while ((index = haystack.indexOf(needle, lastIndex + 1)) != -1)
    {
        lastIndex = index;
        ++count;
    }

    return count;
    }
    public static void main(String args[]) throws IOException {
        
        String word = args[1].toLowerCase();
        
        SevenZFile file = new SevenZFile(new File(args[0]));
        Iterable<SevenZArchiveEntry> entries = file.getEntries();
        for(var entry : entries){
            if(!entry.getName().contains(".txt")){
                continue;
            }
            int lineindex = 1;
            System.out.println(entry.getName());
            var textfile = new BufferedReader
        (new InputStreamReader(file.getInputStream(entry)));
            String line;
            while((line = textfile.readLine()) != null){
                String templine = line;
                if(!templine.toLowerCase().contains(word)){
                    ++lineindex;
                    continue;
                }
                int index = templine.toLowerCase().indexOf(word);
                
                int count = countOccurences(templine.toLowerCase(),word);
                String linelower = templine.toLowerCase();
                if(count >1){
                    int i = 0;
                    int indeex = 0;
                    boolean word_found = false;
                    while(i<count){
                        if(linelower.charAt(indeex) == word.charAt(0)){
                            if(lineindex == 210){
                            }
                            for(var j=0;j<word.length();j++){
                                if(indeex + j > templine.length()){
                                    break;
                                }
                                word_found = true;
                                if(templine.charAt(indeex+j) != word.charAt(j)){
                                    word_found = false;
                                    break;
                                }
                            
                            }
                        }
                        if(word_found == true){
                            templine = templine.substring(0,indeex)+word.toUpperCase()+
                        templine.substring(indeex+word.length(),line.length());
                            i++;
                            word_found = false;
                        }
                        indeex++;
                    }
                }
                templine = templine.substring(0,index)+word.toUpperCase()+
                        templine.substring(index+word.length(),line.length());
                System.out.println(lineindex+": "+templine);
                ++lineindex;
            }
            System.out.println();
        }
        
        
    }
}
