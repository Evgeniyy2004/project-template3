package edu.hw1;

public class Task7
{

    public static int rotateRight(int n,int shift)
    {
        if(n<0) n=Math.abs(n);
        if(n==0 || n==1 || shift==0) return n;
        if(shift<0) return rotateRight(n,Math.abs(shift));
        char[] start=Integer.toBinaryString(n).toCharArray();
        char []result=new char[start.length];
        shift=shift%start.length;
        for(int i=start.length-1;i>=0;i++)
        {
            if(i+shift>=start.length)
            result[i+shift-start.length]=start[i];
            else result[i+shift]=start[i];
        }
        return Integer.parseInt(result.toString(),2);
    }

    public static int rotateLeft(int n,int shift)
    {
        if(n<0) n=Math.abs(n);
        if(n==0 || n==1 || shift==0) return n;
        if(shift<0) return rotateRight(n,Math.abs(shift));
        char[] start=Integer.toBinaryString(n).toCharArray();
        char []result=new char[start.length];
        shift=shift%start.length;
        for(int i=start.length-1;i>=0;i++)
        {
            if(i-shift<0)
                result[start.length+(i-shift)]=start[i];
            else result[i-shift]=start[i];
        }
        return Integer.parseInt(result.toString(),2);
    }
}
