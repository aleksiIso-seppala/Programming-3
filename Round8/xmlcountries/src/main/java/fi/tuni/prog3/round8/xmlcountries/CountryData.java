

package fi.tuni.prog3.round8.xmlcountries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class CountryData {
    
    private static List<Country> countries = new ArrayList<>();
    private static TreeMap<String, TmpData> tmpcountries = new TreeMap<>();
    
    private CountryData(){
    }
    
    public static List<Country> readFromXmls(String areaFile, String populationFile, 
    String gdpFile) throws JDOMException, IOException{
        
        tmpcountries.clear();
        readData(areaFile,"area");
        readData(populationFile,"population");
        readData(gdpFile,"gdp");
        
        for(var country : tmpcountries.values()){
            Country tmpcountry = new Country(country.name,country.area,country.population,country.gdp);
            countries.add(tmpcountry);
        }
        
        return countries;
        
    } 
    
    private static void readData(String readfile, String datatype) throws JDOMException,IOException{
                
        File file = new File(readfile);
        SAXBuilder sax = new SAXBuilder();
        Document doc = sax.build(file);
        
        Element root = doc.getRootElement();
        var target = root.getChild("data").getChild("record");
        var data = root.getChild("data");
        for(var entry : data.getChildren("record")){
            String currentCountry = null;
            String currentValue = null;
            for(var entry1 : entry.getChildren("field")){
                if(entry1.getAttributeValue("name").equals("Country or Area")){
                    currentCountry = entry1.getText();
                    continue;
                }
                if(entry1.getAttributeValue("name").equals("Value")){
                    currentValue = entry1.getText();
                }
                if(currentCountry != null && currentValue != null){
                    
                    if(datatype.equals("area")){
                        TmpData tmp = new TmpData(currentCountry,Double.parseDouble(currentValue));
                        tmpcountries.put(currentCountry, tmp); 
                    }
                    else if(datatype.equals("population")){
                        tmpcountries.get(currentCountry).setPopulation(Long.parseLong(currentValue));
                    }
                    else if(datatype.equals("gdp")){
                        tmpcountries.get(currentCountry).setgdp(Double.parseDouble(currentValue));              
                    }
                    currentCountry = null;
                    currentValue = null;
                }


            }
        }
    }
    
    public static void writeToXml(List<Country> countries,String countryFile){
        
    }
        
        private static class TmpData{
            
            private String name;
            private double area;
            private double gdp;
            private long population;
            
            private TmpData(String name,double area){
                this.name = name;
                this.area = area;
            }
            private void setgdp(double gdp){
                this.gdp = gdp;
            }
            private void setPopulation(long population){
                this.population = population;
            }
        }
}
