/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Aleksi
 */
public class DateTime extends Date{
    
    private int minute_;
    private int second_;
    private int hour_;
    
    public DateTime(int year, int month, int day, int hour, int minute, int second) throws DateException{
        
        super(year,month,day);
        
        if(hour<0 || hour>=24 || 0>minute || minute>=60 || 0>second || second>=60){
            throw new DateException(String.format("Illegal time %s:%s:%s",hour,minute,second));
        }
        this.hour_ = hour;
        this.minute_ = minute;
        this.second_ = second;
        
    }
    
    public int getHour(){
        return hour_;
    }
    public int getMinute(){
        return minute_;
    }
    public int getSecond(){
        return second_;
    }
    public String toString(){
        int hour = getHour();
        int minute = getMinute();
        int second = getSecond();
        
        String date = super.toString();
        String time = hour_ + ":" + minute_ + ":" + second_;
        return date + " " + time;
    }
    
    
}
