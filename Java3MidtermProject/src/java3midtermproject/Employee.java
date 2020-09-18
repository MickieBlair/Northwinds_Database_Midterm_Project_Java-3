/// Mickie Blair
// Java 3 - Midterm Project
// Northwind Database Query Tool
// Employee Class 

package java3midtermproject;

import javafx.beans.property.SimpleStringProperty;

public class Employee {
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty firstName;
    
    /**
     * Employee Constructor - no args
     */
    Employee(){
        this.lastName = new SimpleStringProperty();
        this.firstName = new SimpleStringProperty();
    }
    
    /**
     * Employee Constructor
     * @param ln name
     * @param fn city
     */
    Employee(String ln, String fn){
        this.lastName = new SimpleStringProperty(ln);
        this.firstName = new SimpleStringProperty(fn);
    }
    
    /**
     * Set Last Name
     * @param ln last name
     */
    public void setLastName(String ln){
        this.lastName.set(ln);
    }
    
    /**
     * Set First Name
     * @param fn first name
     */
    public void setFirstName(String fn){
        this.firstName.set(fn);
    }
    
    /**
     * Get Last Name
     * @return name
     */
    public String getLastName(){
        return this.lastName.get();
    }
    
    /**
     * Get First Name
     * @return first name
     */
    public String getFirstName(){
        return this.firstName.get();
    }
  
}
