package fi.tuni.prog3.sevenzipsearch;

import java.io.File;
import java.io.IOException;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

public class SevenZipSearch {


    public static void main(String args[]) throws IOException {
        
        SevenZFile file = new SevenZFile(new File(args[0]));
        Iterable<SevenZArchiveEntry> entries = file.getEntries();
        for(var entry : entries){
            System.out.println(entry.getName()); 
        }
        
        
    }
}
