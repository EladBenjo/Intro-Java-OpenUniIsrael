
/**
 * Company class represents a list of rents for a car rental company
 *
 * @author Elad Benjo
 * @version January 28th 2023
 */
public class Company
{
    private RentNode _head;

    /**
     * Company constructor creates a new Company object with an empty list
     */
    public Company()
    {
        _head = null;
    }
    /**
     * addRent adds a rent to the list in a sorted method
     *
     * @param name - the name of the renting customer
     * @param car - the car that the renting customer will have as a Car object
     * @param pick - the pick up date of the rent
     * @param ret - the return date of the rent
     * @return true if the rent was added and false if it exists in the list already
     */
    public boolean addRent(String name, Car car, Date pick, Date ret)
    {
        Rent newRent = new Rent (name, car, pick, ret);
        RentNode newNode = new RentNode(newRent);
        if (empty())
        {
            _head = newNode;
            return true;
        }// empty list
        RentNode ptr = _head;
        RentNode behind = null;
        while (ptr != null)
        {
            if (ptr.getRent().equals(newRent))
            {
                return false;
            }
            else if (ptr.getRent().getPickDate().after(pick))
            {
                if (behind == null)
                {
                    newNode.setNext(ptr);
                    _head = newNode;
                    return true;
                }//head of the list
                else
                {
                    behind.setNext(newNode);
                    newNode.setNext(ptr);
                    return true;
                }//else
            }
            else if (ptr.getRent().getPickDate().equals(pick))
            {
                if (ptr.getRent().howManyDays() < newRent.howManyDays())
                {
                    if (behind == null)
                    {
                        newNode.setNext(ptr);
                        _head = newNode;
                        return true;
                    }//head of the list
                    else
                    {
                        behind.setNext(newNode);
                        newNode.setNext(ptr);
                        return true;
                    }//else
                }
            }
            else if (ptr.getNext() == null)
            {
                ptr.setNext(newNode);
                return true;
            }
            behind = ptr;
            ptr = ptr.getNext();
        }
        return true;
    }// end of addRent method
    private boolean empty()
    {
        return _head == null;
    }// end of empty method
    /**
     * removeRent removes a rent from the list.
     * @param d - the return date of the rent to be removed
     * @return true if it was removed or flase if the wanted rent doesn't exist
     */
    public boolean removeRent(Date d)
    {
        RentNode ptr = _head;
        RentNode behind = null;
        if (empty())
        {
            return false;
        }
        while (ptr != null)
        {
            if(ptr.getRent().getReturnDate().equals(d))
            {
              if (behind == null)
              {
                  _head = ptr.getNext();
                  return true;
              }
              else
              {
                  behind.setNext(ptr.getNext());
                  return true;
              }
            }
            behind = ptr;
            ptr = ptr.getNext();
        }
        return false;
    }// end of removeRent
    /**
     * getNumOfRents gets the number of rents in the list
     * @return the number of rents
     */
    public int getNumOfRents()
    {
        RentNode ptr = this._head;
        int count = 0;
        if (empty())
        {
            return count;
        }//empty list
        else
        while (ptr != null)
        {
            count ++;
            ptr = ptr.getNext();
        }
        return count;
    }//end of getNumOfRents
    /**
     * getSumOfPrices gets the sum of the prices
     * @return the sum of the prices
     */
    public int getSumOfPrices()
    {
        RentNode ptr = _head;
        int count = 0;
        if (empty())
        {
            return count;
        }//empty list
        else
        while (ptr != null)
        {
            count += ptr.getRent().getPrice();
            ptr = ptr.getNext();
        }
        return count;
    }// end pf getSumOfPrices
    /**
     * getSumOfDays gets the sum of the days
     * @return the sum of the days
     */
    public int getSumOfDays()
    {
        RentNode ptr = _head;
        int count = 0;
        if (empty())
        {
            return count;
        }//empty list
        else
        while (ptr != null)
        {
            count += ptr.getRent().howManyDays();
            ptr = ptr.getNext();
        }
        return count;
    }// end pf getSumOfDays
    /**
     * AverageRent calculate and return the avarage rent period
     * @return the average rent period in days
     */
    public double averageRent()
    {
        if (empty())
        {
            return 0;
        }//empty list
        else
        return ((double)getSumOfDays()/(double)getNumOfRents());
    }// end of averageRent
    /**
     * lastCarRent gets the car that its return date is the latest, if there are few with same date it gets the first one of them.
     * @return the car that is being returned the latest
     */
    public Car lastCarRent()
    {
        if (empty())
        {
            return null;
        }//empty list
        RentNode ptr = _head;
        Date lastDate = _head.getRent().getReturnDate();
        Car lastCar = _head.getRent().getCar();
        while (ptr != null)
        {
            if (ptr.getRent().getReturnDate().after(lastDate))
            {
                lastDate = ptr.getRent().getReturnDate();
                lastCar = ptr.getRent().getCar();
            }
            ptr = ptr.getNext();
        }
        return lastCar;
    }// end of lastCarRent
    /**
     * longestRent gets the longest rent, if there are few with the same length it will get the first of them.
     * @return the longest rent
     */
    public Rent longestRent()
    {
        if (empty())
        {
            return null;
        }//empty list
        RentNode ptr = _head.getNext();
        int mostDays = _head.getRent().howManyDays();
        Rent longestRent = _head.getRent();
        while (ptr != null)
        {
            if (ptr.getRent().howManyDays() > mostDays)
            {
                mostDays = ptr.getRent().howManyDays();
                longestRent = ptr.getRent();
            }
            ptr = ptr.getNext();
        }
        return longestRent;
    }// end of longestRent
    /**
     * mostCommonRate calculates and gets the most popular 'rank' of car among the rents
     * if it's tied between few ranks it returns the 'higher' one.
     * @return the most popular rank (A/B/C/D)
     */
    public char mostCommonRate()
    {
        if (empty())
        {
            return 'N';
        }//empty list
        int A=0, B=0, C=0, D=0;
        int max;
        char temp;
        char most = 'N';
        RentNode ptr = _head;
        while(ptr != null)
        {
            temp = ptr.getRent().getCar().getType();
            switch (temp)
            {
                case 'A':
                    A++;
                    break;
                case 'B':
                    B++;
                    break;
                case 'C':
                    C++;
                    break;
                case 'D':
                    D++;
                    break;
            }// end of switch for counter
            ptr = ptr.getNext();
        }
        max = Math.max(Math.max(A,B),Math.max(C,D));
        if (max == D)
            most = 'D';
        else if (max == C)
            most = 'C';
        else if (max == B)
            most = 'B';
        else if (max == A)
            most = 'A';
        return most;
    }// end of mostCommonRate
    /**
     * includes checks if the list it gets as an argument is completly included in the calling list
     * @param other - the rental company list of rents to check if its included
     */
    public boolean includes(Company other)
    {
        if (other.empty())
        {
            return true;
        }// parameter company is empty
        else if (this.empty())
        {
            return false;
        }// the "including" company is empty
        int otherLength = other.getNumOfRents();
        int counter = 0;
        for(RentNode otherPtr = other._head; otherPtr !=null; otherPtr = otherPtr.getNext())
        {
            for(RentNode ptr = _head; ptr != null; ptr = ptr.getNext())
            {
                if (ptr.getRent().equals(otherPtr.getRent()))
                {
                    counter ++;
                }
            }
        }
        return (counter == otherLength);
    }// end of includes
    /**
     * merge merges the list it gets as an argument into the calling list
     * @param other - the rental company list of rents to merege in
     */
    public void merge(Company other)
    {
        if (other._head != null)
        {
            RentNode ptr = other._head;
            String name;
            Car car;
            Date pick;
            Date ret;
            while (ptr != null)
            {
                name = ptr.getRent().getName();
                car = ptr.getRent().getCar();
                pick = ptr.getRent().getPickDate();
                ret = ptr.getRent().getReturnDate();
                this.addRent(name, car, pick, ret);
                ptr = ptr.getNext();
            }
        }
    }// end of merge
    /**
     * toString prints a detailed list of rentals
     */
    public String toString()
    {
        if (empty())
        {
            return "The company has 0 rents.";
        }//empty list
        RentNode ptr = _head;
        return "The company has " + getNumOfRents() + " rents: \n" +
                rentString(ptr);
    }// end of toString
    private String rentString(RentNode ptr)
    {
        if (ptr.getNext() == null){
            return ptr.getRent().toString();}
        else
        return ptr.getRent().toString()+"\n" + rentString(ptr.getNext());
    }
}//end of company class
