/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.round8.xmlcountries;

/**
 *
 * @author Aleksi
 */
public class Country implements Comparable<Country>{
    
    private String name;
    private double area;
    private long population;
    private double gdp;
    
    public Country(String name, double area, long population, double gdp){
        this.name = name;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
    }
    
    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }

    public double getGdp() {
        return gdp;
    }
    
    public long getPopulation() {
        return population;
    }
    
    public String toString(){
        String line = "";
        line = String.format("%s%n  Area: %.1f km2%n  Population: %s%n  GDP: %.1f (2015 USD)%n",
                            name,area,population,gdp);
        return line;
    }
    
    @Override
    public int compareTo(Country other){
        int value = name.compareTo(other.name);
        return value;
    }
}
