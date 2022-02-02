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
        // Huomaa, ett‰ kaikki luokan LegalDate j‰senet on t‰ss‰ m‰‰ritetty staattisiksi.

        // Funktiom‰‰ritys kuin C-kieless‰: paluuarvon tyyppi funktion nimen eteen,
        // parametrien tyypit ja nimet suluissa pilkuilla eroteltuina funktion nimen per‰‰n.
        // Funktio isLeapYear palauttaa totuusarvon, joka kertoo, onko year karkausvuosi.
        private static boolean isLeapYear(int year) {
          // Karkausvuosi: jaollinen 4:ll‰ ja ei jaollinen 100:lla tai jaollinen 400:lla.
          // Javan loogisaritmeettiset operaatiot ja return-lause kuin C-kieless‰.
          return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
        }

        // Kuukausien p‰ivien m‰‰ritt‰misen voi tehd‰ monella tavalla. T‰ss‰ k‰ytet‰‰n
        // taulukkosyntaksin lis‰havainnollistamiseksi kaksiulotteista esit‰ytetty‰
        // int-taulukkoa: kullekin kuukaudelle 2-alkioinen alitaulukko, jossa p‰ivien m‰‰r‰
        // tavallisena ja karkausvuonna (ainoa ero helmikuussa eli toisessa alitaulukossa).
        static int[][] mDays = {{31, 31}, {28, 29}, {31, 31}, {30, 30}, {31, 31}, {30, 30},
                                {31, 31}, {31, 31}, {30, 30}, {31, 31}, {30, 30}, {31, 31}};

        // Funktio monthDays palauttaa tiedon, kuinka monta p‰iv‰‰ kuukaudessa
        // month vuonna year on. Palautusarvo -1 vastaa virheellist‰ kuukautta.
        private static int monthDays(int month, int year) {
          int days = -1;
          if(1 <= month && month <= 12) {
            // Ehdollinen operaattori kuin C-kieless‰.
            days = isLeapYear(year) ? mDays[month-1][1] : mDays[month-1][0];
          }
          return days;
        }

        // Funktio isLegalDate tutkii, onko parametrien day, month ja year kuvaama
        // p‰iv‰m‰‰r‰ laillinen. T‰ss‰ vuosiluvun oletetaan olevan aina laillinen.
        private static boolean isLegalDate(int day, int month, int year) {
          // Tuloksen laskenta on suoraviivaista, koska monthDays
          // palauttaa -1, jos kuukausi on laiton.
          return (1 <= day) && (day <= monthDays(month, year));
        }
    }
    
}

