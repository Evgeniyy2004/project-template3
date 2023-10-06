package edu.hw1;

import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Task4
{
    public static void main(String [] args)
    {
        try
        {
            Scanner scanner = new Scanner(System.in);
            String str1 = scanner.nextLine();
            System.out.println(fixString(str1));
        }

        catch(Exception e)
        {
            return;
        }
    }

    public static @NotNull String fixString(@NotNull String str)
    {
        char[] result=new char[str.length()];
        for(int i=1;i<str.length();i+=2)
        {
            result[i]=str.charAt(i-1);
            result[i-1]=str.charAt(i);
        }
        if(str.length()%2!=0) result[str.length()-1]=str.charAt(str.length()-1);
        return String.valueOf(result);
    }
}
