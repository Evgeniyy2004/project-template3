package edu.hw1;

import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Task3
{
    public static void main(String [] args)
    {
        try
        {
            Scanner scanner = new Scanner(System.in);
            String[] str1 = scanner.nextLine().split(" ");
            String[] str2=scanner.nextLine().split(" ");
            int[] firstone=new int[str1.length];
            for(int i=0;i<str1.length;i++)
            {
                firstone[i]=parseInt(str1[i]);
            }
            int[] secondone=new int[str2.length];
            for(int i=0;i<str2.length;i++)
            {
                secondone[i]=parseInt(str2[i]);
            }
            System.out.println(isNestable(firstone,secondone));
        }

        catch(Exception e)
        {
            return;
        }
    }

    public static boolean isNestable(int[] one,int[] two)
    {
        Arrays.sort(one);
        Arrays.sort(two);
        if(one.length>0 && two.length>0 && one[0]>two[0] && one[one.length-1]<two[two.length-1])
            return true;
        return false;
    }
}
