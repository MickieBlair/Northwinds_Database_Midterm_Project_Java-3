/// Mickie Blair
// Java 3 - Midterm Project
// Northwind Database Query Tool
// Customer Class 

package java3midtermproject;

import javafx.beans.property.SimpleStringProperty;

public class Customer {
    private final SimpleStringProperty name;
    private final SimpleStringProperty city;
    
    /**
     * Customer Constructor - no args
     */
    Customer(){
        this.name = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
    }
    
    /**
     * Customer Constructor
     * @param n name
     * @param c city
     */
    Customer(String n, String c){
        this.name = new SimpleStringProperty(n);
        this.city = new SimpleStringProperty(c);
    }
    
    /**
     * Set Name
     * @param n name
     */
    public void setName(String n){
        this.name.set(n);
    }
    
    /**
     * Set City
     * @param c city 
     */
    public void setCity(String c){
        this.city.set(c);
    }
    
    /**
     * Get Name
     * @return name
     */
    public String getName(){
        return this.name.get();
    }
    
    /**
     * Get City
     * @return city
     */
    public String getCity(){
        return this.city.get();
    }
    
    
}
