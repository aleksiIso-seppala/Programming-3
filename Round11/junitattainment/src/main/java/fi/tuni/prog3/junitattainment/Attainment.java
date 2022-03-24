/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.junitattainment;

import org.junit.jupiter.api.Test;

/**
 *
 * @author Aleksi
 */
public class Attainment implements Comparable<Attainment> {
    
    private String courseCode;
    private String studentNumber;
    private int grade;
    
    public String getCourseCode() {
        return courseCode;
    }
    
    public String getStudentNumber() {
        return studentNumber;
    }
    
    public int getGrade() {
        return grade;
    }
    
    public Attainment(String courseCode, String studentNumber, int grade) throws IllegalArgumentException {
        
        if(courseCode == null || studentNumber == null){
            throw new IllegalArgumentException();
        }
        if(grade < 0 || grade > 5){
            throw new IllegalArgumentException();
        }
        
        this.courseCode = courseCode;
        this.studentNumber = studentNumber;
        this.grade = grade;
    } 
    
    @Test
    @Override
    public int compareTo(Attainment o) {
        int value = courseCode.compareTo(o.courseCode);
        if(value == 0){
            value = studentNumber.compareTo(o.studentNumber);
        }
        return value;
    }

    @Test
    public String toString(){
        String value = String.format("%s %s %s",courseCode,studentNumber,grade);
       
        return value;
    }
}
