/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingapp;

/**
 *
 * @author Patsf
 */
public class Validation {

    private String lineEnd;
    
    public Validation() { 
        this.lineEnd = "\n";  
    }
    public Validation(String lineEnd) {
        this.lineEnd = lineEnd;
    }
    
    public String isPresent(String value, String name) {
        String msg = "";
        if (value.isEmpty()) {
            msg = name + " is required." + lineEnd;
        }
        return msg;
    }

    public String isDouble(String value, String name) {
        String msg = "";
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException e) {
            msg = name + " must be a valid number." + lineEnd;
        }
        return msg;
    }
    
    public String isInteger(String value, String name) {
        String msg = "";
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            msg = name + " must be an integer." + lineEnd;
        }
        return msg;
    } 
    
    public String isLong(String value, String name) {
        String msg = "";
        try {
            Long.parseLong(value);
        } catch (NumberFormatException e) {
            msg = name + " must be an long number." + lineEnd;
        }
        return msg;
    } 
    
}
