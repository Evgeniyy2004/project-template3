package edu.hw1;

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

    public static int minutesToSeconds(String video)
    {
        try
        {
            int seconds = parseInt(video.split(":")[1]);
            int minutes=parseInt(video.split(":")[0]);
            return minutes*60+seconds;
        }
        catch(Exception e)
        {
            return -1;
        }
    }
}
