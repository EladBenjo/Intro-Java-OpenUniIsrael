/********************************************************************************
 * This program is taking 3 lengths of triangle sides from the user and calculates
 * the perimeter and area of it. the program then prints out the length of each
 * side, the perimeter, and area that it calculated.
 * by Elad Benjo November 9th 2022.
 ********************************************************************************/

import java.util.Scanner;
public class Triangle1
{
    public static void main (String [] args)
    {
        Scanner scan = new Scanner (System.in);
        System.out.println ("This program calculates the area "
        + "and the perimeter of a given triangle. ");
        System.out.println ("Please enter the three lengths"
        + " of the triangle's sides");
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        
        //the required calculations
        int perimeter = a+b+c;
        double s = perimeter/2; //for cleaner look in Heron formula bellow
        double area = Math.sqrt(s*(s-a)*(s-b)*(s-c)); // Heron formula
        //print everything out
        System.out.println("The lengths of the triangle sides are: "+a+", "+b+", "+c+".");
        System.out.println("The perimeter of the triangle is: "+perimeter);
        System.out.println("The area of the triangle is: "+area);
    } //end of method main
} //end of class Triangle1