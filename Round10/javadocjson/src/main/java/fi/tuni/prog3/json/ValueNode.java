/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.json;

/**
 *
 * A class for representing a JSON value. The value can be either a double, a boolean, a String or null.
 */
public final class ValueNode extends Node{

    private Double doublevalue_ = null;
    private Boolean booleanvalue_ = null;
    private String stringvalue_ = null;
    
    /**
     * Constructs a JSON value node that stores the given double value.
     * @param value The double value to store in the new JSON value node.
     */
    public ValueNode(double value){
        this.doublevalue_ = value;
    }
    
    /**
     * Constructs a JSON value node that stores the given boolean value.
     * @param value The boolean value to store in the new JSON value node.
     */
    public ValueNode(boolean value){
        this.booleanvalue_ = value;
    }
    
    /**
     * Constructs a JSON value node that stores the given string or null.
     * @param value The string or null to store in the new JSON value node.
     */
    public ValueNode(String value){
        this.stringvalue_ = value;
    }
    
    /**
     * Checks whether this value node stores a number (double).
     * @return true if this node stores a double value, otherwise false.
     */
    public boolean isNumber(){
        if(doublevalue_ == null){
            return false;
        }
        return true;
    }
    
    /**
     * Checks whether this value node stores a boolean value.
     * @return true if this node stores a boolean value, otherwise false.
     */
    public boolean isBoolean(){
        if(booleanvalue_ == null){
            return false;
        }
        return true;
    }
    
    /**
     * Checks whether this value node stores a string.
     * @return true if this node stores a string, otherwise false.
     */
    public boolean isString(){
        if(stringvalue_ == null){
            return false;
        }
        return true;
    }
    
    /**
     * Checks whether this value node stores null.
     * @return true if this node stores null, otherwise false.
     */
    public boolean isNull(){
        if(stringvalue_ == null && 
           booleanvalue_ == null && 
           doublevalue_ == null){
            return true;
        }
        return false;
    }
    
    /**
     * Returns the stored value as a number (double).
     * @return the stored number as a double value.
     * @throws IllegalStateException if the stored value is not a number.
     */
    public double getNumber(){
        if(!isNumber()){
            return 0;
        }
        return doublevalue_;
    }
    
    /**
     * Returns the stored value as a boolean value.
     * @return the stored boolean value.
     * @throws IllegalStateException if the stored value is not a boolean value.
     */
    public boolean getBoolean(){
        if(!isBoolean()){
            return false;
        }
        return booleanvalue_;
    }
    
    /**
     * Returns the stored value as a string.
     * @return the stored string.
     * @throws IllegalStateException if the stored value is not a string.
     */
    public String getString(){
        if(!isString()){
            return "";
        }
        return stringvalue_;
    }
    
    /**
     * Returns the stored value as null.
     * @return null.
     * @throws IllegalStateException if the stored value is not null.
     */
    public Object getNull(){
        return null;
    }
}
