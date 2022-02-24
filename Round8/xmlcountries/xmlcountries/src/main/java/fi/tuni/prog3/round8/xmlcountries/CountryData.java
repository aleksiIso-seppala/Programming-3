

package fi.tuni.prog3.round8.xmlcountries;
<?xml version="1.0" encoding="UTF-8"?>

import java.util.List;
import java.util.ArrayList;

public class CountryData {
    
    private List<Country> countries;
    
    private CountryData(){
        this.countries = new ArrayList<>();
    }
    
    public List<Country> readFromXmls(String areaFile, String populationDile, String gdpFile){
        
    } 
    
    public void writeToXml(List<Country> countries,String countryFile){
        
    }
}
