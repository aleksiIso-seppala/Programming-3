import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Month;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.time.temporal.ChronoUnit;

public class Dates {
        
    private Dates(){
    }
    
    public static class DateDiff{
        private String start_;
        private String end_;
        private int diff_;

        
        private DateDiff(){
        }
        
        private String startweekday_;
        private String endweekday_;
        private String startiso_;
        private String endiso_;
        
        public String getStart(){
            return start_;
        }
        public String getEnd(){
            return end_;
        }
        public int getDiff(){
            return diff_;
        }
        public String toString(){
            String diff = startweekday_+" "+startiso_+" --> "+endweekday_+" "+endiso_+": "+diff_+" days";
            return diff;
        }
    }
    public static DateDiff[] dateDiffs(String ...dateStrs){
        
        String dateformat = "dd-MM-yyyy";

        ArrayList<LocalDate> dates = new ArrayList<>();
        for(int i=0;i<dateStrs.length;i++){
            String[] split = dateStrs[i].split("\\.");
            String year;
            String month;
            String day;
            if(split.length < 3){
                var split1 = dateStrs[i].split("-");
                year = split1[0];
                month = split1[1];
                day = split1[2];
                if(month.length()<2 || day.length()<2){
                    char parenthesis = '"';
                    System.out.format("The date %s%s%s is illegal!%n",parenthesis,dateStrs[i],parenthesis); 
                    continue;
                }
            }
            else{
                year = split[2];
                month = split[1];
                day = split[0];
            }
            if(Integer.parseInt(year) < 1000 || Integer.parseInt(year) > 9999){
                char parenthesis = '"';
                System.out.format("The date %s%s%s is illegal!%n",parenthesis,dateStrs[i],parenthesis);
                continue;                
            }
            if(Integer.parseInt(day) < 10 && day.length()<2){
                day = "0"+day;
            }
            if(Integer.parseInt(month) <10 && month.length()<2){
                month = "0"+month;
            }
            String datestring = year+"-"+month+"-"+day;
            try{
                //LocalDateTime datetmp = LocalDateTime.parse(datestring,dateTimeFormatter);
                LocalDate datetmp = LocalDate.parse(datestring);
                dates.add(datetmp);
            }
            catch (DateTimeParseException e){
                char parenthesis = '"';
                System.out.format("The date %s%s%s is illegal!%n",parenthesis,dateStrs[i],parenthesis);
                continue;
            }
        }
        Comparator<LocalDate> compare = Comparator.comparing(LocalDate::getYear)
                                                      .thenComparing(LocalDate::getMonth)
                                                      .thenComparing(LocalDate::getDayOfMonth);
        List<LocalDate> sorted = dates.stream().sorted(compare).collect(Collectors.toList());
        
        ArrayList<DateDiff> datediffs = new ArrayList<>();
        
        if(sorted.size() <= 1){
            DateDiff[] array = new DateDiff[1];
            return array;
        }
        DateTimeFormatter dateTimeFormatter = 
        DateTimeFormatter.ofPattern(dateformat, Locale.UK)
                         .withResolverStyle(ResolverStyle.STRICT);
        for(int i=1;i<sorted.size();i++){
            DateDiff datediff = new DateDiff();
            String endmonth = Integer.toString(sorted.get(i).getMonthValue());
            if(endmonth.length() < 2){
                endmonth = "0"+endmonth;
            }
            String endday = Integer.toString(sorted.get(i).getDayOfMonth());
            if(endday.length() < 2){
                endday = "0"+endday;
            }
            String startmonth = Integer.toString(sorted.get(i-1).getMonthValue());
            if(startmonth.length() < 2){
                startmonth = "0"+startmonth;
            }
            String startday = Integer.toString(sorted.get(i-1).getDayOfMonth());
            if(startday.length() < 2){
                startday = "0"+startday;
            }
            String end = sorted.get(i).getYear()+"-"+endmonth+"-"+endday;
            String start = sorted.get(i-1).getYear()+"-"+startmonth+"-"+startday;
            var diff = sorted.get(i-1).until(sorted.get(i),ChronoUnit.DAYS);
            int diffInt = (int) diff;
            //muista split ja muutos uudelleen
            datediff.endiso_ = endday+"."+endmonth+"."+sorted.get(i).getYear();
            datediff.startiso_ = startday+"."+startmonth+"."+sorted.get(i-1).getYear();
            datediff.startweekday_ = sorted.get(i-1).getDayOfWeek().toString().toLowerCase();
            datediff.startweekday_ = datediff.startweekday_.substring(0,1).toUpperCase() + datediff.startweekday_.substring(1);
            datediff.endweekday_ = sorted.get(i).getDayOfWeek().toString().toLowerCase();
            datediff.endweekday_ = datediff.endweekday_.substring(0,1).toUpperCase() + datediff.endweekday_.substring(1);            
            datediff.start_ = start;
            datediff.end_ = end;
            datediff.diff_ = diffInt;
            datediffs.add(datediff);
        }
        DateDiff[] array = new DateDiff[datediffs.size()];
        int i = 0;
        for(var diff : datediffs){
            array[i] = diff;
            i++;
        }
        return array;
    }
    
}
