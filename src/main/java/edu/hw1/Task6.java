package edu.hw1;

import org.jetbrains.annotations.NotNull;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Task6
{
    public static void main(String [] args)
    {
        try
        {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();

        }

        catch(Exception e)
        {
            return;
        }
    }

    public static int countK(int arg)
    {
        if(arg<0) arg=Math.abs(arg);
        String str=Integer.toString(arg);
        if(str.length()!=4 || str.equals("1000")) return -1;
        if(str.charAt(0)==str.charAt(1) && str.charAt(0)==str.charAt(2) && str.charAt(0)==str.charAt(3)) return -1;
        if(arg==6174) return 0;
        int i=1;
        arg=numberK(str);
        while(arg!=6174)
        {
            arg=numberK(Integer.toString(arg));
            i++;
        }
        return i;
    }

    public static int numberK(@NotNull String arg)
    {
        char[] newArg= arg.toCharArray();
        Arrays.sort(newArg);
        int result=0;
        result+=((newArg[3]-'0')-(newArg[0]-'0'))*Math.pow(10,3);
        result+=((newArg[2]-'0')-(newArg[1]-'0'))*Math.pow(10,2);
        result+=((newArg[1]-'0')-(newArg[2]-'0'))*Math.pow(10,1);
        result+=((newArg[0]-'0')-(newArg[3]-'0'))*Math.pow(10,0);
        return result;
    }
}
