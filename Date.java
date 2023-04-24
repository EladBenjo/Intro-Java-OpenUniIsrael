
/**
 * This class represents a Date object 
 * @author Elad Benjo
 * @version December 3rd 2022
 */
public class Date
{
    private int _day;
    private int _month;
    private int _year;
    
    public static final int JAN = 1;
    public static final int FEB = 2;
    public static final int MAR = 3;
    public static final int APR = 4;
    public static final int MAY = 5;
    public static final int JUN = 6;
    public static final int JUL = 7;
    public static final int AUG = 8;
    public static final int SEP = 9;
    public static final int OCT = 10;
    public static final int NOV = 11;
    public static final int DEC = 12;
    
    public static final int DEFAULT_YEAR = 2000;
    public static final int MIN_DAY = 1;
    public static final int SHORT_MAX_DAY = 30; // max days in monts 4,6,9,11
    public static final int LONG_MAX_DAY = 31; // max days in months 1,3,5,7,8,10,12
    public static final int SHORT_FEBRUARY = 28; // for common year
    public static final int LONG_FEBRUARY = 29; // for leap year
    public static final int MIN_MONTH = 1;
    public static final int MAX_MONTH = 12;
    public static final int MIN_YEAR = 1000;
    public static final int MAX_YEAR = 9999;
    
    private void DEFAULT_DATE(){
        _year = DEFAULT_YEAR;
        _month = JAN;
        _day = MIN_DAY;
    }
    
    // leap year check, use for valid dates of february.
    private boolean isLeap(int year){
        return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
    }
    
    private boolean validDate(int day, int month, int year){
        if (year > MAX_YEAR || year < MIN_YEAR || month > MAX_MONTH || 
            month < MIN_MONTH || day > LONG_MAX_DAY || day < MIN_DAY){
                return false; // if any value is greater than max - date isnt valid
            }
        switch (month){
            case JAN:
            case MAR:
            case MAY:
            case JUL:
            case AUG:
            case OCT:
            case DEC:
                return (day <= LONG_MAX_DAY); // above checked valid input, this checks it fits the long month max.
            case APR:
            case JUN:
            case SEP:
            case NOV:
                return (day <= SHORT_MAX_DAY); // check valid input for short months
            default: return // default = February and save an extra return below
                (day <= (isLeap(year) ? LONG_FEBRUARY : SHORT_FEBRUARY));
            }
    }
    
    //return the days count from day 0
    private int calculateDate (int day, int month, int year){
        if (month < 3) 
            {
            year--;
            month = month + 12;
            }
        return 365 * year + year/4 - year/100 + 
            year/400 + ((month+1) * 306)/10 + (day - 62);
    }
    
    //return the amount of days in each month
    private int daysCount (int month, int year){
        switch (month){
            case JAN:
            case MAR:
            case MAY:
            case JUL:
            case AUG:
            case OCT:
            case DEC:
                return LONG_MAX_DAY; 
            case APR:
            case JUN:
            case SEP:
            case NOV:
                return SHORT_MAX_DAY;
            default: return (isLeap(year) ? LONG_FEBRUARY : SHORT_FEBRUARY);
        }
    }
    
    /**
     * If the given date is valid - creates a new Date object, 
     * otherwise creates the date 1/1/2000
     * @param day - the day in the month (1-31)
     * @param month - the month in the year (1-12)
     * @param year - the year (4 digits)
     */
    public Date(int day ,int month ,int year){
        if (validDate(day, month, year)){
            _day = day;
            _month = month;
            _year = year;
        }
        else 
            DEFAULT_DATE();
    }
        
    /**
     * Copy constructor
     * @param other - the date to be copied
     */
    public Date(Date other){
        _year = other._year;
        _month = other._month;
        _day = other._day;
    }
    
    //getters
    /**
     * Gets the day
     * @return the day
     */
    public int getDay(){
        return this._day;
    }
    
    /**
     * Gets the month
     * @return the month
     */
    public int getMonth(){
        return this._month;
    }
    
    /**
     * Gets the year
     * @return the year
     */
    public int getYear(){
        return this._year;
    }
    
    //setters
    /**
     * Set the day (only if date remains valid)
     * @param dayToSet - the day value to be set
     */
    public void setDay (int dayToSet){
        if (validDate(dayToSet, _month, _year)){
            _day = dayToSet;}
    }
    
    /**
     * Set the month (only if date remains valid)
     * @param monthToSet - the month value to be set
     */
    public void setMonth (int monthToSet){
        if (validDate(_day, monthToSet, _year)){
            _month = monthToSet;}
    }
    
    /**
     * Sets the year (only if date remains valid)
     * @param yearToSet - the year value to be set
     */
    public void setYear (int yearToSet){
        if (validDate(_day, _month, yearToSet)){
            _year = yearToSet;}
    }
    
    /**
     * Check if 2 dates are the same
     * @param other - the date to compare this date to
     * @return true if the dates are the same, otherwise false
     */
    public boolean equals (Date other){
        return (_day == other._day && _month == other._month &&
                _year == other._year);
    }
    
    /**
     * Check if this date is before other date
     * @param other - date to compare this date to
     * @return true if this date is before other date, otherwise false
     */
    public boolean before (Date other){
        return ((_year < other._year) ||
                (_year == other._year && _month < other._month) ||
                (_year == other._year && _month == other._month &&
                _day < other._day));
    }
    
    /**
     * Check if this date is after other date
     * @param other - date to compare this date to
     * @return true if this date is after other date, otherwise false
     */
    public boolean after (Date other){
        return (other.before(this));
    }
    
    /**
     * Calculates the difference in days between two dates
     * @param other - the date to calculate the difference between
     * @return the number of days between the dates (non negative value)
     */
    public int difference (Date other){
        return Math.abs(calculateDate(_day, _month, _year) - 
                    calculateDate(other._day, other._month, other._year));
    }

    /**
     * Returns a String that represents this date
     * @return String that represents this date in the following format:
     * day (2 digits) / month(2 digits) / year (4 digits) for example: 02/03/1998
     */
    public String toString(){
        return ((_day < 10 ? "0" : "") + _day + "/" + (_month < 10 ? "0" : "")
                + _month + "/" + _year);
    }
    
    /**
     * Calculate the date of tomorrow
     * @return the date of tomorrow
     */
    public Date tomorrow(){
        int day = (daysCount(_month, _year) == _day) ? 1 : _day + 1;
        int month = (day == MIN_DAY) ? _month + 1 : _month;
        if (month > MAX_MONTH)
            {
            month = JAN;
            }
        int year = (day == MIN_DAY && month == JAN) ? _year + 1 : _year;
        
        return new Date(day, month, year);
            
    }
}