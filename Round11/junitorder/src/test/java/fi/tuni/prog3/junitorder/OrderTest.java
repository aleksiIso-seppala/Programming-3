/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.junitorder;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 *
 * @author Aleksi
 */
public class OrderTest {

    
    @Test
    public void testOrderIsEmpty(){
        boolean value = true;
        Order order1 = new Order();
        if(!order1.isEmpty()){value = false;}
        
        Order.Item item1 = new Order.Item("thing",21.24);
        order1.addItems(item1,2);
        if(order1.isEmpty()){value = false;}
        assertTrue(value);
        
        
    }
    @Test 
    public void testOrderGettersEmpty(){
        boolean value = true;
        Order order1  = new Order();
        List<Order.Entry> entries = order1.getEntries();
        
        if(entries.size() > 0){value = false;}
        
        assertTrue(value);
    }
    
    //testing total price and adding items
    @Test
    public void testGetTotalPrice(){
        boolean value = true;
        
        Order order1 = new Order();
        if(order1.getTotalPrice() > 0){value = false;}
        Order.Item item1 = new Order.Item("thing",1);
        order1.addItems(item1,3);
        if(order1.getTotalPrice() != 3){value = false;}
        
        Assertions.assertThrows(IllegalArgumentException.class, ()->{ 
            order1.addItems(item1,-2);
        });
        Order.Item item2 = new Order.Item("thing",1.5);
        Assertions.assertThrows(IllegalStateException.class, ()->{
            order1.addItems(item2,2);
        });
        order1.removeItems("thing",1);
        if(order1.getTotalPrice() != 2){value = false;}
        Order.Item item3 = new Order.Item("thing2",1);
        order1.addItems(item3,3);
        if(order1.getTotalPrice() != 5){value = false;}        
        assertTrue(value);
    }
    
    @Test
    public void testGetEntryCount(){
        boolean value = true;
        Order order1 = new Order();
        if(order1.getEntryCount() > 0){value = false;}
        Order.Item item1 = new Order.Item("thing",1.23);
        order1.addItems(item1,3);
        if(order1.getEntryCount() != 1){value = false;}
        Assertions.assertThrows(IllegalArgumentException.class, ()->{ 
            order1.removeItems("thing",4);
        });        
        Assertions.assertThrows(IllegalArgumentException.class, ()->{ 
            order1.removeItems("thing",-1);
        }); 
        Assertions.assertThrows(NoSuchElementException.class, ()->{ 
            order1.removeItems("thi2ng",1);
        });
        Order.Item item2 = new Order.Item("thing2",1.44);
        order1.addItems(item2,2);
        if(order1.getEntryCount() != 2){value = false;} 
        order1.removeItems("thing2",1);
        if(order1.getEntryCount() != 2){value = false;}
        order1.removeItems("thing2",1);
        if(order1.getEntryCount() != 1){value = false;}
        assertTrue(value);
    }
    
    @Test
    public void testItemCount(){
        boolean value = true;
        Order order1 = new Order();
        if(order1.getItemCount() > 0){value = false;}
        Order.Item item1 = new Order.Item("thing",1.23);
        order1.addItems(item1,3);
        if(order1.getItemCount() != 3){value = false;}
        Order.Item item2 = new Order.Item("thing2",1.44);
        order1.addItems(item2,2);
        if(order1.getItemCount() != 5){value = false;} 
        order1.removeItems("thing2",1);
        if(order1.getItemCount() != 4){value = false;}
        order1.removeItems("thing2",1);
        if(order1.getItemCount() != 3){value = false;}
        assertTrue(value);        
    }
    
    @Test
    public void testItemGettersEmpty(){
        Order.Item item1 = new Order.Item("thing",1.3445);
        boolean value = true;
        if(!item1.getName().equals("thing")){value = false;}
        if(item1.getPrice() != 1.3445){value = false;}
        String line = item1.toString();
        if(!line.equals(String.format("Item(thing, 1.34)"))){value = false;}
        assertTrue(value);
    }
    @Test
    public void testItemEquals(){
        boolean value = true;
        Order.Item item1 = new Order.Item("thing",1.3345);
        Order.Item item2 = new Order.Item("thing",1.23);
        Order.Item item3 = new Order.Item("thign",1.23);
        if(!item1.equals(item2)){value = false;}
        if(item1.equals(item3)){value = false;}
        assertTrue(value);        
    }
    
    @Test
    public void testItemIllegals(){

        Assertions.assertThrows(IllegalArgumentException.class, ()->{ 
            new Order.Item("afaa",-1.3);
        });
        Assertions.assertThrows(IllegalArgumentException.class, ()->{ 
            new Order.Item(null,1.3);
        });
    }
    
    @Test
    public void testEntryGetters(){
        boolean value = true;
        Order.Item item1 = new Order.Item("thing",1.3345);
        Order.Item item2 = new Order.Item("thing2",2.13);
        Order.Entry entry1 = new Order.Entry(item1,3);
        if(!entry1.getItemName().equals("thing")){value = false;}
        if(entry1.getUnitPrice() != 1.3345){value = false;}
        if(!entry1.getItem().equals(item1)){value = false;}    
    }
    @Test
    public void testEntryToString(){
        boolean value = true;
        Order.Item item1 = new Order.Item("thing",1.00);
        Order.Entry entry1 = new Order.Entry(item1,3);
        String line = entry1.toString();
        if(!line.equals("3 units of Item(thing, 1.00)")){value = false;}
        assertTrue(value);
    }
    @Test
    public void testEntryIllegals(){
        
        Order.Item item1 = new Order.Item("thing",1.345);
        Assertions.assertThrows(IllegalArgumentException.class, ()->{ 
            new Order.Entry(item1,-2);
        });
    }
    
}
