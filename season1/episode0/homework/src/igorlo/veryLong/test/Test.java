package igorlo.veryLong.test;

import igorlo.veryLong.VeryLong;

public class Test {

    public static void main(String[] args) {

        long testMePlease = -60000000;
        //int testMePlease = Integer.MIN_VALUE;
        System.out.println(testMePlease);
        System.out.println(printBinary(testMePlease));
        System.out.println();
        VeryLong veryLong1 = new VeryLong(testMePlease);

        System.out.println(veryLong1.getBinaryRepresentation());

        System.out.println(veryLong1.toString());

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