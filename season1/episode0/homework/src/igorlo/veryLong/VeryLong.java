package igorlo.veryLong;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MatchGenerator;

import java.math.BigInteger;
import java.util.ArrayList;

public class VeryLong {

    private Long[] longs;

    public final static int DEFAULT_CAPACITY = 5;

    public VeryLong(){
        longs = new Long[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++){
            longs[i] = null;
        }
    }

    public VeryLong(long number){
        longs = new Long[DEFAULT_CAPACITY];
        longs[0] = number;
        for (int i = 1; i < DEFAULT_CAPACITY; i++){
            longs[i] = null;
        }
    }

    public VeryLong(int capacity){
        longs = new Long[capacity];
        for (int i = 0; i < capacity; i++){
            longs[i] = null;
        }
    }


    public String getBinaryRepresentation(){
        StringBuilder sb = new StringBuilder();
        for (Long number: longs){
            if (number == null)
                break;
            sb.append(getBinary(number));
        }
        return sb.toString();
    }

    public static String getBinary(long number){
        long n = number;
        long nAbs = Math.abs(n);
        int remainder;
        StringBuilder sb = new StringBuilder();

        if (nAbs > 1) {
            remainder = (int) (n%2);
            sb.append(getBinary((n/2)));
            sb.append(Math.abs(remainder));
        } else {
            sb.append(nAbs);
        }

        return sb.toString();
    }

    public void add(VeryLong other){
    }

    public void add(long other){
    }

    public void add(int other){
    }

    private VeryLong copy() {
        return this; //TODO
    }

    @Override
    public String toString(){
        BigInteger big = new BigInteger("0");
        for (int i = 0; i < longs.length; i++){
            Long currentLong = longs[i];
            if (currentLong == null)
                break;
            big = big.multiply(new BigInteger(Long.toString(Long.MAX_VALUE)));
            if (currentLong < 0){
                big = big.add(new BigInteger(Long.toString(currentLong + Long.MAX_VALUE)));
                big = big.add(new BigInteger("18446744073709551616")); // Используем знаковый разряд как обычный числовой разряд
            } else
                big = big.add(new BigInteger(Long.toString(currentLong)));
        }
        return big.toString();
    }

    public Long[] getLongs() {
        return longs;
    }
}
