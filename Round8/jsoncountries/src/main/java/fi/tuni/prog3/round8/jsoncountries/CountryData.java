
package fi.tuni.prog3.round8.jsoncountries;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CountryData {
    
    private static ArrayList<Country> countries = new ArrayList<>();
    private static TreeMap<String, TmpData> tmpcountries = new TreeMap<>();
    
    public static List<Country> readFromJsons(String areaFile, String populationFile, String gdpFile) throws IOException{
        
        ReadData(areaFile,"area");
        ReadData(populationFile,"population");
        ReadData(gdpFile,"gdp");
        
        for(var country : tmpcountries.values()){
            Country tmpcountry = new Country(country.name,country.area,country.population,country.gdp);
            countries.add(tmpcountry);
        }
        
        return countries;
    }
    
    private static void ReadData(String filename,String filetype) throws IOException{

        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get(filename));
        JsonObject response = gson.fromJson(reader, JsonObject.class);
        JsonElement rootElement = response.get("Root");
        JsonElement dataElement = rootElement.getAsJsonObject().get("data");
        JsonElement recordElement = dataElement.getAsJsonObject().get("record");
        JsonArray recordArray = recordElement.getAsJsonArray();
        
        for(var object : recordArray){
            JsonObject jsonObject = (JsonObject) object;
            JsonElement stuff = jsonObject.getAsJsonObject().get("field");
            JsonArray test = stuff.getAsJsonArray();
            int counter = 0;
            String countryName = null;
            String value = null;
            for(var things : test){
                var test1 = things.getAsJsonObject();
                for(var ate : test1.entrySet()){
                    if(counter == 0){
                        
                        countryName = ate.getValue().toString();
                        countryName = countryName.substring(1,countryName.length()-1);
                    }
                    if(counter == 4){
                        value = ate.getValue().toString();
                        value = value.substring(1,value.length()-1);
                        
                        if(filetype.equals("area")){
                            var tmp = new TmpData(countryName,Double.parseDouble(value));
                            tmpcountries.put(countryName,tmp);
                        }
                        if(filetype.equals("population")){
                            tmpcountries.get(countryName).setPopulation(Long.parseLong(value));
                        }
                        if(filetype.equals("gdp")){
                            tmpcountries.get(countryName).setgdp(Double.parseDouble(value));
                        }
                        
                    }
                    counter++;
                }
            }
        }
        reader.close();
        
    }
    
    public static void writeToJson(List<Country> countries, String countryFile) throws IOException{
        
        Gson gson  = new GsonBuilder().setPrettyPrinting().create();
        Writer writer = new FileWriter(countryFile);
        
        gson.toJson(countries,writer);
        writer.close();
        
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
