/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Aleksi
 */
public class Date {

    private int year_;
    private int month_;
    private int day_;
    
    public Date(int year, int month, int day) throws DateException{
        
        if(!LegalDate.isLegalDate(day,month,year)){
            throw new DateException(String.format("illegal date %s.%s.%s",day,month,year));
        }
        
        this.year_ = year;
        this.month_ = month;
        this.day_ = day;
    }
    public int getYear(){
        return year_;
    }
    public int getMonth(){
        return month_;
    }
    public int getDay(){
        return day_;
    }
    public String toString(){
        String date = day_ + "." + month_ + "." + year_;
        return date;
    }
    private class LegalDate {

        private static boolean isLeapYear(int year) {

          return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
        }

        static int[][] mDays = {{31, 31}, {28, 29}, {31, 31}, {30, 30}, {31, 31}, {30, 30},
                                {31, 31}, {31, 31}, {30, 30}, {31, 31}, {30, 30}, {31, 31}};

        private static int monthDays(int month, int year) {
          int days = -1;
          if(1 <= month && month <= 12) {

            days = isLeapYear(year) ? mDays[month-1][1] : mDays[month-1][0];
          }
          return days;
        }

        private static boolean isLegalDate(int day, int month, int year) {

          return (1 <= day) && (day <= monthDays(month, year));
        }
    }
    
}

