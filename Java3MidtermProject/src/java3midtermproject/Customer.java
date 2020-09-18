/// Mickie Blair
// Java 3 - Midterm Project
// Northwind Database Query Tool
// Customer Class 

package java3midtermproject;

import javafx.beans.property.SimpleStringProperty;

public class Customer {
    private final SimpleStringProperty name;
    private final SimpleStringProperty city;
    private final SimpleStringProperty region;
    private final SimpleStringProperty country;
    
    /**
     * Customer Constructor - no args
     */
    Customer(){
        this.name = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
        this.region = new SimpleStringProperty();
        this.country = new SimpleStringProperty();
    }
    
    /**
     * Customer Constructor
     * @param n name
     * @param c city
     */
    Customer(String n, String c, String r, String cntry){
        this.name = new SimpleStringProperty(n);
        this.city = new SimpleStringProperty(c);
        this.region = new SimpleStringProperty(r);
        this.country = new SimpleStringProperty(cntry);
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
     * Set Region
     * @param r region 
     */
    public void setRegion(String r){
        this.region.set(r);
    }
    
    /**
     * Set Country
     * @param cntry country 
     */
    public void setCountry(String cntry){
        this.country.set(cntry);
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
    
    /**
     * Get Region
     * @return region 
     */
    public String getRegion(){
        return this.region.get();        
    }
    
    /**
     * Get Country
     * @return country 
     */
    public String getCountry(){
        return this.country.get();  
    }
    
    
}
