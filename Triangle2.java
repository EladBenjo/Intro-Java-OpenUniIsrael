/********************************************************************************
 * This program is taking 3 lengths and checking if they can make a triangle.
 * if yes it will check what kind of triangle it is. at the end it will tell the
 * user wheter or not it can make a triangle or what kind it is.
 * by Elad Benjo November 9th 2022.
 ********************************************************************************/

import java.util.Scanner;
public class Triangle2
{
    public static void main (String [] args)
    {
        Scanner scan = new Scanner (System.in);
        System.out.println ("This program will tell you what kind of triangle you have! if it is a triangle at all...");
        System.out.println ("Please enter your 3 lengths below, one at a time:");
        int x = scan.nextInt();
        int y = scan.nextInt();
        int z = scan.nextInt();
        //if a triangle
        if ((x>0) && (y>0) && (z>0) && (x+y>z) && (x+z>y) && (y+z>x)){ //any pair of sides must be greater than the third side + no negative lengths
            //equilateral
            if ((x==y) && (y==z)) //simply mean that all sides are equal
                System.out.println ("The numbers: "+ x +", "+ y +" and "+ z +" represent an equilateral triangle");
            //isosceles
            else if ((x==y)||(x==z)||(y==z)) //by this point we know they are not all equal, then if any pair is equal its an isosceles
                System.out.println ("The numbers: "+ x +", "+ y +" and "+ z +" represent an isosceles triangle");
            //right angle
            else if ((x*x + y*y == z*z)||(x*x + z*z == y*y)||(y*y + z*z == x*x)) //Pythagorean theorem, if any is true then its a right-angle
                System.out.println ("The numbers: "+ x +", "+ y +" and "+ z +" represent a right-angle triangle");
            //common
            else //none of the above was true, but we know it's a triangle then it's a common
                System.out.println ("The numbers: "+ x +", "+ y +" and "+ z +" represent a common triangle");
        } 
        //not a triangle
        else System.out.println ("The numbers: "+ x +", "+ y +" and "+ z +" cannot represent a triangle");
    } //end of method main
} //end of class Triangle2