
/**
 * RentNode class represents a node in a company's list of rentals, it stores a Rent object and a refernce to the next node in the list.
 *
 * @author Elad Benjo
 * @version January 28th 2023
 */
public class RentNode
{
    // instance variables - replace the example below with your own
    private Rent _rent;
    private RentNode _next;

    /**
     * RentNode constructor creates a new RentNode with the Rent object it gets as a parameter and initialize next to null.
     * @param r - a Rent object
     */
    public RentNode(Rent r)
    {
        _rent = new Rent (r);
        _next = null;
    }// end of RentNode constructor w/ null
    /**
     * RentNode constructor creates a new RentNode object with the Rent object it gets as a parameter and a pointer to the next node that it gets a s a parameter.
     * @param r - Rent object that the node will store
     * @param next - RenNode object that the node will point to
     */
    public RentNode(Rent r, RentNode next)
    {
        _rent = new Rent (r);
        _next = next;
    }// end of RentNode constructor
    /**
     * Copy constructor
     * @pararm other - a RentNode to copy
     */
    public RentNode(RentNode other)
    {
        _rent = new Rent (other._rent);
        _next = other._next;
    }// end of RentNode copy constructo
    /**
     * getRent gets the rent
     *
     * @return the rent
     */
    public Rent getRent()
    {
        if (_rent == null)
        {
            return null;
        }
        return new Rent(_rent);
    }// end of getRent method
    /**
     * getNext gets the next RentNode in the list
     * 
     * @return the next node in the list
     */
    public RentNode getNext()
    {
        if (_next == null)
        {
            return null;
        }
        return _next;
    }// end of getNext method
    /**
     * setRent sets the rent
     * 
     * @param r - a rent object to be assigned
     */
    public void setRent(Rent r)
    {
        _rent = new Rent(r);
    }// end of setRent method
    /**
     * setNext sets the the RentNode that this node will point to as next in the list
     * 
     * @param next - RentNode object that will be next in the list after this one
     */
    public void setNext(RentNode next)
    {
        _next = next;
    }// end of setNext method
}
