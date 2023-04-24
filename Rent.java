
/**
 * This class represents a Rent object
 * @author Elad Benjo
 * @version December 3rd 2022
 */
public class Rent
{
    private String _name;
    private Car _car;
    private Date _pickDate;
    private Date _returnDate;
    // pricing
    public static final int PRICE_A = 100;
    public static final int PRICE_B = 150;
    public static final int PRICE_C = 180;
    public static final int PRICE_D = 240;
    public static final double WEEK_DSCT = 0.9; // 10% discount for 1 week rent
    
    /**
     * Creates a new Rent object
     * The return date must be at least one day after the pickup date, 
     * otherwise set it to one day after the pick up date.
     * @param name - the client's name
     * @param car - the rented car
     * @param pick - the pickup date
     * @param ret - the return date
     */
    public Rent(String name, Car car, Date pick, Date ret){
        _name = name;
        _car = new Car (car);
        _pickDate = new Date (pick);
        if (pick.before(ret)){
            _returnDate = new Date (ret);}
        else{ 
            _returnDate = pick.tomorrow();}
    }
    
    /**
     * Copy constructor
     * @param other - the rent to be copied
     */
    public Rent(Rent other){
        _name = new String (other._name);
        _car = new Car (other._car);
        _pickDate = new Date (other._pickDate);
        _returnDate = new Date (other._returnDate);
    }
    
    //getters
    /**
     * Gets the car
     * @return the car
     */
    public Car getCar(){
        return new Car(_car);
    }
    
    /**
     * Gets the name
     * @return the name
     */
    public String getName(){
        return new String(_name);
    }
    
    /**
     * Gets the pick up date
     * @return the pick up date
     */
    public Date getPickDate(){
        return new Date(_pickDate);
    }
    
    /**
     * Gets the return date
     * @return the return date
     */
    public Date getReturnDate(){
        return new Date(_returnDate);
    }
    
    // setters
    /**
     * Sets the rented car
     * @param car - the rented car (You can assume that car is not null)
     */
    public void setCar(Car car){
        _car = new Car(car);
    }
    
    /**
     * Sets the client name
     * @param name - the client name (You can assume that the name is a valid name)
     */
    public void setName (String name){
        _name = new String(name);
    }
    
    /**
     * Sets the pick up date
     * The pick up date must be at least one day before the return date, 
     * otherwise - don't change the pick up date
     * @param pickDate - the pick up date (You can assume that pick up date is not null)
     */
    public void setPickDate (Date pickDate){
        if (pickDate.before(this._returnDate))
            this._pickDate = new Date (pickDate);
    }
    
    /**
     * Sets the return date
     * The return date must be at least one day after the pick up date, 
     * otherwise - don't change the return date
     * @param returnDate - the return date (You can assume that return date is not null)
     */
    public void setReturnDate (Date returnDate){
        if (this._pickDate.before(returnDate))
            this._returnDate = new Date (returnDate);
    }
    
    /**
     * Returns a String that represents this rent
     * @return String that represents this rent in the following format:  
     * Name:Rama From:30/10/2022 To:12/11/2022 Type:B Days:13 Price:1845
     */
    public String toString(){
        return "Name:" + _name + " From:" + _pickDate + " To:" + _returnDate
        + " Type:" + _car.getType() + " Days:" + howManyDays() + " Price:"
        + getPrice();
    }
   
    /**
     * Check if 2 rents are the same
     * @param other - the rent to compare this rent to
     * @return true if the rents are the same
     */ 
    public boolean equals (Rent other){
        return (_name.equals(other._name) && 
                _pickDate.equals(other._pickDate) && 
                _returnDate.equals(other._returnDate) && 
                _car.equals(other._car));
    }
    
    /**
     * Returns the number of rent days
     * @return the number of rent days
     */
    public int howManyDays(){
        return (this._pickDate.difference(this._returnDate));
    }
    
    /**
     * Returns the rent total price
     * @return the rent total price
     */
    public int getPrice(){
        int numDays = howManyDays(); // length of rent in days
        int remainder = (howManyDays() % 7); // how many days are out of 7s 'package'- for calculating full price when 7 or more days total
        if (howManyDays() < 7){
            switch (_car.getType())
                {
                case 'A':
                    return numDays * PRICE_A;
                case 'B':
                    return numDays * PRICE_B;
                case 'C':
                    return numDays * PRICE_C;
                case 'D':
                    return numDays * PRICE_D;
                }
            }
        else // the else is rent time greater or equal to 7 therefore discounted rate
            {
            int dsctDays = (numDays - remainder);
            switch (_car.getType())
                {
                case 'A':
                    return (int)(PRICE_A * (dsctDays * WEEK_DSCT + 
                                            remainder));
                case 'B':
                    return (int)(PRICE_B * (dsctDays * WEEK_DSCT + 
                                            remainder));
                case 'C':
                    return (int)(PRICE_C * (dsctDays * WEEK_DSCT + 
                                            remainder));
                case 'D':
                    return (int)(PRICE_D * (dsctDays * WEEK_DSCT + 
                                            remainder));
                }
            }
        return 0; // must guarentee a return because of the use of ifs
    }
    
    /**
     * Try to upgrade the car to a better car
     * If the given car is better than the current car of the rent, 
     * upgrade it and return the upgrade additional cost, otherwise - don't upgrade
     * @param car - the car to upgrade to
     * @return the upgrade cost
     */
    public int upgrade (Car newCar){
        if (newCar.better(_car)){
            int oldPrice = getPrice();
            setCar(newCar);
            return (getPrice() - oldPrice);
        }
        return 0;
    }
    
    /**
     * Check if there is a double listing of a rent for the same person 
     * and car with an overlap in the rental days
     * If there is - return a new rent object with the unified dates, 
     * otherwise - return null.
     * @param other - the other rent
     * @return the unified rent or null
     */
    public Rent overlap (Rent other){
        if (!_name.equals(other._name) || !_car.equals(other._car))
            {
            return null;
            }
        Date earlyPick = _pickDate.before(other._pickDate) ? 
                        getPickDate() : other.getPickDate();
        Date latePick = _pickDate.after(other._pickDate) ? 
                        getPickDate() : other.getPickDate();
        Date earlyRet = _returnDate.before(other._returnDate) ? 
                        getReturnDate() : other.getReturnDate();
        Date lateRet = _returnDate.after(other._returnDate) ? 
                        getReturnDate() : other.getReturnDate();
        if (earlyRet.before(latePick))
            {
            return null;
            }
        return new Rent (_name, _car, earlyPick, lateRet);
    }
}

