package edu.hw1;

import org.jetbrains.annotations.NotNull;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Task1
{
    public static void main(String [] args)
    {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(minutesToSeconds(str));
    }

    public static int minutesToSeconds(@NotNull  String video)
    {
        String[] minutesSec=video.split(":");
        if(minutesSec.length!=2) return -1;
        try
        {
            int seconds = parseInt(minutesSec[1]);
            int minutes = parseInt(minutesSec[0]);
            return minutes * 60 + seconds;
        }
        catch (Exception e)
        {
            return -1;
        }
    }
}
