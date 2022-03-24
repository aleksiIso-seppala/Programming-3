/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.junitorder;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 *
 * @author Aleksi
 */
public class OrderTest {

    public final Order order = new Order();
    
    @Test
    public void testOrderIsEmpty(){
        boolean value = true;
        Order order1 = new Order();
        if(!order1.isEmpty()){value = false;}
        
        assertTrue(value);
    }
    @Test 
    public void testOrderGettersEmpty(){
        boolean value = true;
        
        ArrayList<Order.Entry> entries = order.getEntries();
        
        if(order.getTotalPrice() != 0){value = false;}
        if(order.getItemCount() != 0){value = false;}
        if(order.getEntryCount() != 0){value = false;}
        if(entries.size() != 0){value = false;}
        
        assertTrue(value);
    }
    
    @Test
    public void testItemGettersEmpty(){
        Item item1 = new Item("thing",1.3445);
        boolean value = true;
        if(!item1.getName().equals("thing")){value = false;}
        if(item1.getPrice() != 1.3445){value = false;}
        String line = item1.toString();
        if(!line.equals(String.format("Item(thing,1.34"))){value = false;}
        assertTrue(value);
    }
    @Test
    public void testItemEquals(){
        boolean value = true;
        Item item1 = new Item("thing",1.3345);
        Item item2 = new Item("thing",1.23);
        Item item3 = new Item("thign",1.23);
        if(!item1.equals(item2)){value = false;}
        if(item1.equals(item3)){value = false;}
        assertTrue(value);        
    }
    
    @Test
    public void testItemIllegals(){

        Assertions.assertThrows(IllegalArgumentException.class, ()->{ 
            new Item("afaa",-1.3);
        });
        Assertions.assertThrows(IllegalArgumentException.class, ()->{ 
            new Item(null,1.3);
        });
    }
    
    @Test
    public void testEntryGetters(){
        boolean value = true;
        Item item1 = new Item("thing",1.3345);
        Item item2 = new Item("thing2",2.13);
        Entry entry1 = new Entry(item1,3);
        if(!entry1.getItemName().equals("thing")){value = false;}
        if(entry1.getUnitPrice() != 1.3345){value = false;}
        if(!entry1.getItem().equals(item1)){value = false;}    
    }
    @Test
    public void testEntryToString(){
        boolean value = true;
        Item item1 = new Item("thing",1.3345);
        Entry entry1 = new Entry(item1,3);
        String line = entry1.toString();
        if(!line.equals("3 unites of item thing")){value = false;}
        assertTrue(value);
    }
    @Test
    public void testEntryIllegals(){
        
        Item item1 = new Item("thing",1.345);
        Assertions.assertThrows(IllegalArgumentException.class, ()->{ 
            new Entry(item1,-2);
        });
    }
    
}
