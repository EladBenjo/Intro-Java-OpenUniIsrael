
/**
 * Class Ex13 is maman13.
 *
 * @author Elad Benjo
 * @version January 21st 2023
 */
public class Ex13
{
    /**
     * alternating method gets a String s of n '0' and n '1' and return the number of swaps between chars to get to an alternating sequence
     * time complexity is O(n) since the algorithm iterates through each 'char' of the input string (n). the amount of work done in the loop depends on the length of the string therefore linear - O(n).
     * space complexity is O(1) since the algorithm uses a fixed number of variables, not depending on any input whatsoever.
     * @param String s of n '0' and n '1'
     * @return int alternating the number of swaps between chars it took to get to an alternating sequence
     */
    public static int alternating (String s)
    {
        int odd_ones = 0 , even_ones = 0; // counters for '1' in odd or even index in the string
        for (int i = 0; i<s.length(); i++)
        {
            if (s.charAt(i) == '1'){
                if (i%2 != 0)
                {
                    odd_ones++;
                }
                else even_ones++;
            }
        }
        return Math.min(even_ones, odd_ones);
    }
    /**
     * What method gets an array of integers and return the length of the 
     * longest sub array that the sum of all its values is even
     * the original time complexity of what is O(n^3), the method used two nested loops in what method
     * and an additional one in the private f method that was called by it. each one of these loops depended on the array length therefore O(n^3).
     * the original space complexity of what method is O(1). the method used a fixed amount of variables.
     * the new time complexity of what method is O(n), it uses one loop that checks each value of the array, it dependes on the length of the array it recives as an arrgument therefore O(n).
     * the new space complexity is still O(1), the method uses a fixed amount of variables.
     * @param []a array of integers
     * @return int what the length of the longest sub-array calculated
     */
    public static int what (int []a){
        int sum = 0 , firstOddIndex = -1 , lastOddIndex = -1;
        for (int i=0; i<a.length; i++)
        {
          sum += a[i];
        
          if (a[i] % 2 != 0)
          {
            if (firstOddIndex == -1) // saves the first odd number index
            {
                firstOddIndex = i;
            }
            lastOddIndex = i; // saves the last odd number index
          }
        }
        if (sum % 2 != 0) //
        {
            // below calculates the length of the array left to the right of
            // the first odd number and the array left to the left of the last
            // odd number
            int rightArrLength = a.length - (firstOddIndex+1); // 'detaching' the first odd number and all the 'substring' to its left 
            int LeftArrLength = lastOddIndex; // 'detaching' the last odd number and all the 'substring' to its right
            return Math.max (rightArrLength, LeftArrLength);
        }
        return a.length;
    }
    /**
     * isWay method gets an array full of int (all are >0) and checks if there
     * is a way to start from index 0 and get to the end of the array by add or substract
     * the value that is stored in the current index to the index pointer.
     * @param a is an array full of int>0
     * @return isWay true/false
     */
    public static boolean isWay(int[] a){
        return isWay(a , 0);
    }
    //overloading isWay in order to initialize a 'starting' point for the way
    private static boolean isWay(int[] a, int i)
    {
        if (i == a.length-1) // that means we got to the last index or that array stores a single value.
        {
            return true;
        }
        if (i < 0 || i >= a.length || a[i] < 0)// if we step out of array borders or re-visit an index
        {
            return false;
        }
        
        a[i] = -a[i]; // temporary store negative value to prevent re-visit
        boolean rightWay = isWay (a, i - a[i]);// index -(-a[i]) step to the right
        boolean leftWay = isWay (a, i + a[i]);// index + (-a[i]) step to the left
        a[i] = -a[i]; // restoring the original value
        
        return (rightWay || leftWay);
    }
    /**
     * prince method is solving the prince problem. the prince is jumping between indexes in the array in order to reach the villain, the method checks if
     * there is a legal way for the prince and return the shortest one, if there is none the method will return -1.
     * @param drm an array of int that stores only positive values that represent the roof heigts, the one and only negative value is -1 that represents the villain.
     * @param i row coordinate for the prince starting point
     * @param j column coordinate for the prince starting point
     * @return prince the amount of steps it took the prince to reach the villain
     */
    public static int prince(int[][] drm, int i, int j){
        int steps = prince(drm,i,j,drm[i][j],1);//sending it to an overloading method that deals steps and the value of previous roof
        if (steps == Integer.MAX_VALUE)// if the overloading method return this that means that all the path the prince tried were illegal
        {
           return -1; 
        }
        else
        {
        return steps;
        }
    }
    private static int prince(int[][] drm, int i, int j, int prev,int steps)
    {
        if (i<0 || i>= drm.length || j<0 || j>=drm[0].length || drm[i][j] == -2
            || prev > drm[i][j]+2 || drm[i][j] > prev+1)
        //stepping out of borders rows then columns, -2 is the mark the method temporary leaves on a visited spot, jumping down 2, jumping up 1.
        {
            return Integer.MAX_VALUE;
        }
        if (drm[i][j] == -1)// the villain
        {
            return steps;
        }
        if ((i-1>=0 && drm[i-1][j] == -1)||
            (i+1<=drm.length-1 && drm[i+1][j] == -1)||
            (j-1>=0 && drm[i][j-1] == -1)||
            (j+1<=drm[0].length-1 && drm[i][j+1] == -1)) // any of the next steps is the villain
        {
            return steps +1;
        }

        
        int temp = drm[i][j]; // temporary storing original value
        drm[i][j] = -2; // initialize to 'visited' landmark
        
        int up = prince(drm, i-1, j, temp, steps+1);
        int down = prince(drm, i+1, j, temp, steps+1);
        int left = prince(drm, i, j-1, temp, steps+1);
        int right = prince(drm, i, j+1, temp,steps+1);
        
        drm[i][j] = temp;//restore to original value
        

        return Math.min(Math.min(up,right),Math.min(left,down));
    }
}