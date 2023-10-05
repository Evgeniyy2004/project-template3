package edu.hw1;

import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Task2
{
    public static void main(String [] args)
    {
        try
        {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            System.out.println(countDigits(parseInt(str)));
        }

        catch(Exception e)
        {
            return;
        }
    }

    public static int countDigits(int number)
    {
        int i=0;
        while(number!=0)
        {
            number=number/10;
            i+=1;
        }
        return i;
    }
}
