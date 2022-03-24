/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.junitattainment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
/**
 *
 * @author Aleksi
 */
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class AttainmentTest {
    
    private final Attainment attainment = new Attainment("Ohj3","H292168",3);
    
    @Test
    public void testToString(){
        String line = String.format("Ohj3 H292168 3");
        boolean value = line.equals(attainment.toString());
        assertTrue(value);
        
    }
    
    @Test
    public void testGetters(){
        boolean value = true;
        if(!attainment.getCourseCode().equals("Ohj3")){
            value = false;
        }
        if(!attainment.getStudentNumber().equals("H292168")){
            value = false;
        }
        if(attainment.getGrade() != 3){
            value = false;
        }
        assertTrue(value);
    }
    
    @Test
    public void testCompareTo(){
        boolean value = true;
        Attainment attainment1 = new Attainment("Bohj3","H299168",3);
        Attainment attainment2 = new Attainment("Ohj3","H299168",3);
        if(attainment.compareTo(attainment2) > 0){value = false;}
        if(attainment.compareTo(attainment1) < 0){value = false;}
        assertTrue(value);
    }
    
    @Test
    public void testIllegal(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{ 
            new Attainment(null,"H299168",3);
        });
        Assertions.assertThrows(IllegalArgumentException.class,()->{ 
            new Attainment("jawdji",null,3);
        });
        Assertions.assertThrows(IllegalArgumentException.class, ()->{ 
            new Attainment("adaw","H299168",6);
        });
        Assertions.assertThrows(IllegalArgumentException.class, ()->{ 
            new Attainment("adaw","H299168",-1);
        });   
    
    }
    public static void main(String args[]) {
        
        
        
    }
}
