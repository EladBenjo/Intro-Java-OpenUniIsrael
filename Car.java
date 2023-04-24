
/**
 * This class represents a Car object
 * @author Elad Benjo
 * @version December 3rd 2022
 */
public class Car
{
    private int _id; // car id number
    private char _type; // from A to D - worse to better
    private String _brand;
    private boolean _isManual;
    
    public static final int MIN_ID = 1000000;
    public static final int MAX_ID = 9999999;
    public static final int DEFAULT_ID = 9999999;
    public static final char DEFAULT_TYPE = 'A';
    // checks if id is valid and has 7 digits
    private boolean VALID_ID (int id){
        return (id >=MIN_ID && id <= MAX_ID);
    }
    // checks if type is valid
    private boolean VALID_TYPE (char type){
        return (type == 'A' || type == 'B' || type == 'C' || type == 'D');
    }

    /**
     * Creates a new Car object
     * id should be a 7 digits number, otherwise set it to 9999999
     * type should be 'A','B','C' or 'D', otherwise set it to 'A'
     * @param id - the id of the car (7 digits number)
     * @param type - the type of the car ('A','B','C' or 'D')('A' worse 'D' best)
     * @param brand - the car's brand
     * @param isManual - flag indicating if the car is manual
     */
    public Car(int id, char type, String brand, boolean isManual){
        if (VALID_ID(id))
            _id = id;
        else
            _id = DEFAULT_ID;
            
        _brand = brand;
        
        if (VALID_TYPE(type))
            _type = type;
        else
            _type = DEFAULT_TYPE;
            
        _isManual = isManual;
    }
    
    /**
     * Copy Constructor
     * @param other - the car to be copied
     */
    public Car (Car other){
        _id = other._id;
        _type = other._type;
        _brand = new String(other._brand);
        _isManual = other._isManual;
    }

    //getters
    /**
     * Gets the id
     * @return Car's id number
     */
    public int getId(){
        return this._id;
    }
    
    /**
     * Gets the car type
     * @return Car's type
     */
    public char getType(){
        return this._type;
    }
    
    /**
     * Gets the brand
     * @return Car's brand
     */
    public String getBrand(){
        return new String (_brand);
    }
    
    /**
     * Gets the isManual flag
     * @return isManual flag
     */
    public boolean isManual(){
        return this._isManual;
    }
    
    //setters
    /**
     * Sets the id (only if the given id is valid)
     * @param id - the id value to be set
     */
    public void setId(int id){
        if (VALID_ID(id))
            this._id = id;
    }
    
    /**
     * Sets the type (only if the given type is valid)
     * @param type - the type value to be set
     */
    public void setType(char type){
        if (VALID_TYPE(type))
            this._type = type;
    }
    
    /**
     * Sets the brand of the car
     * @param brand - the brand value to be set
     */
    public void setBrand(String brand){
        this._brand = brand;
    }
    
    /**
     * Sets the isManual flag of the car
     * @param isManual - the isManual flag to be set
     */
    public void setIsManual(boolean manual){
        this._isManual = manual;
    }
    
    /**
     * Returns a String object that represents this car
     * @return String that represents this car in the following format:
        id:1234567 type:B brand:Toyota gear:manual (or auto)
     */
    public String toString(){
        return ("id:" + _id + " type:" + _type + " brand:" + _brand + 
                " gear:" + (_isManual? "manual":"auto"));
    }
    
    /**
     * Check if two cars are the same
     * Cars are considered the same if they have the same type, brand and gear
     * @param other - the car to compare this car to
     * @return true if the cars are the same, otherwise false
     */
    public boolean equals (Car other){
       return (_type == other._type && _brand.equals(other._brand) &&
               _isManual == other._isManual);
    }
    
    /**
     * Check if this car is better than the other car
     * A car is considered better than another car if its type is higher.
     * If both cars have the same type, an automated car is better than a manual car.
     * @param other - car to compare this car to
     * @return true if this car is better than the other car, otherwise false
     */
    public boolean better (Car other){
        if (_type > other._type)
            return true;
        else if (_type == other._type && _isManual == false && other._isManual
                == true)
            return true;
        else 
            return false;
    }
    
    /**
     * Check if this car is worse than the other car
     * @param other - car to compare this car to
     * @return true if this car is worse than the other car, otherwise false
     */
    public boolean worse (Car other){
        return (other.better(this));
    }
}
