package edu.hw2;

public class Task4 {
    private Task4() {
        //not used
    }

    public static CallingInfo findCallingInfo() {
        var c = new Throwable().getStackTrace();
        CallingInfo result = null;
        String className = new Task4().getClass().getName();
        String methodName = new Task4().getClass().getMethods()[0].getName();
        for (int i = 0; i < c.length - 1; i++) {
            if (c[i].getClassName() == className && c[i].getMethodName() == methodName) {
                    result = new CallingInfo(c[i + 1].getClassName(), c[i + 1].getMethodName().toString());
                    break;
            }
        }
        return  result;
    }


}
