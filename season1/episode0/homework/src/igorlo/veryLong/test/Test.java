package igorlo.veryLong.test;

import java.math.BigInteger;

import com.sun.org.apache.xpath.internal.SourceTree;

import igorlo.veryLong.VeryLong;
import main.java.edu.technopolis.DummyFibonacciAlgorithm;
import main.java.edu.technopolis.MyFibonacciAlgorythm;

public class Test {

    public static void main(String[] args) {

        lookForTroubles();
        //fibTest();

    }

    private static void fibTest() {
        MyFibonacciAlgorythm myal = new MyFibonacciAlgorythm();
        DummyFibonacciAlgorithm dummy = new DummyFibonacciAlgorithm();

        boolean foundTrouble = false;
        int count = 0;

        while (!foundTrouble){
            count++;
            String myalStr = myal.evaluate(count);
            String dummyStr = dummy.evaluate(count);
            if (myalStr.equals(dummyStr)){
                System.out.println("Success: " + myalStr);
            } else {
                System.out.println("Trouble in: " + dummyStr);
                System.out.println("You said:   " + myalStr);
                System.out.println("index: " + count);
                System.out.println("-----------------------");
                System.out.println("Long MAX: " + Long.toString(Long.MAX_VALUE));
                System.out.println("Long MIN: " + Long.toString(Long.MIN_VALUE));
                foundTrouble = true;
            }
        }
    }

    private static void lookForTroubles() {

        long step = 1;

        BigInteger big = new BigInteger("0");
        VeryLong veryLong = new VeryLong(0l);

        BigInteger stepBig = new BigInteger(Long.toString(step));
        VeryLong stepLong = new VeryLong(step);

        boolean foundTrouble = false;
        int count = 0;

        big = big.add(stepBig);
        veryLong.add(stepLong);
        while (!foundTrouble){
            count++;
            big = big.add(big);
            veryLong.add(veryLong);
            String bigStr = big.toString();
            String veryStr = veryLong.toString();
            if (bigStr.equals(veryStr)){
                System.out.println("Success: " + bigStr);
            } else {
                System.out.println("Trouble in: " + bigStr);
                System.out.println("You said: " + veryStr);
                System.out.println("Step: " + count);
                System.out.println("-----------------------");
                System.out.println("Long MAX: " + Long.toString(Long.MAX_VALUE));
                System.out.println("Long MIN: " + Long.toString(Long.MIN_VALUE));
                foundTrouble = true;
            }
        }

    }

    public static String printBinary(long number){
        long n = number;
        long nAbs = Math.abs(n);
        int remainder;
        StringBuilder sb = new StringBuilder();

        if (nAbs > 1) {
            remainder = (int) (n%2);
            sb.append(printBinary((n/2)));
            sb.append(Math.abs(remainder));
        } else {
            sb.append(nAbs);
        }

        return sb.toString();
    }

}
