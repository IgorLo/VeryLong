package igorlo.veryLong;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MatchGenerator;

import java.math.BigInteger;
import java.util.ArrayList;

public class VeryLong {

    private Long[] longs;

    public final static int DEFAULT_CAPACITY = 16;

    public VeryLong() {
        longs = new Long[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            longs[i] = 0L;
        }
    }

    public VeryLong(long number) {
        longs = new Long[DEFAULT_CAPACITY];
        longs[0] = number;
        for (int i = 1; i < DEFAULT_CAPACITY; i++) {
            longs[i] = 0L;
        }
    }

    public VeryLong(int capacity) {
        longs = new Long[capacity];
        for (int i = 0; i < capacity; i++) {
            longs[i] = 0L;
        }
    }

    public VeryLong(int capacity, long number) {
        longs = new Long[capacity];
        longs[0] = number;
        for (int i = 1; i < capacity; i++) {
            longs[i] = 0L;
        }
    }

    public String getBinaryRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (Long number : longs) {
            if (number == null) {
                break;
            }
            sb.append(getBinary(number));
        }
        return sb.toString();
    }

    public static String getBinary(long number) {
        long n = number;
        long nAbs = Math.abs(n);
        int remainder;
        StringBuilder sb = new StringBuilder();

        if (nAbs > 1) {
            remainder = (int) (n % 2);
            sb.append(getBinary((n / 2)));
            sb.append(Math.abs(remainder));
        } else {
            sb.append(nAbs);
        }

        return sb.toString();
    }

    public static VeryLong getSum(VeryLong one, VeryLong other) {
        VeryLong sum = new VeryLong(one.longs.length);
        sum.set(one);
        sum.add(other);
        return sum;
    }

    private boolean sumLongs(VeryLong other, int index, boolean prevOverflow){
        boolean newOverflow = false;

        long numberOne = longs[index];
        long numberTwo = other.longs[index];
        if (prevOverflow)
            numberTwo++; //DANGEROUS AND MYSTERIOUS PLACE

        if (numberOne < 0 && numberTwo < 0){
            newOverflow = true;
        } else {
            if ((numberOne < 0 || numberTwo < 0) && (numberOne + numberTwo > 0)){
                newOverflow = true;
            }
        }

        longs[index] += numberTwo;
        return newOverflow;
    }

    public void add(VeryLong other) {

        boolean overflow = sumLongs(other, 0, false);

        for (int i = 1; i < longs.length; i++){
            overflow = sumLongs(other, i, overflow);
        }

        /*
        int i = 0;
        while (other.longs[i] != null && i < longs.length) {
            if (longs[i] < 0 && other.longs[i] < 0) {
                longs[i] += other.longs[i] + Long.MAX_VALUE + 1;
                if (this.longs[i + 1] == null) {
                    this.longs[i + 1] = 1L;
                } else {
                    this.longs[i + 1]++;
                }
            } else {
                if (this.longs[i] < 0 || other.longs[i] < 0) {
                    boolean signBefore = longs[i] < 0;
                    this.longs[i] += other.longs[i] + 1;
                    boolean signAfter = longs[i] < 0;
                    if (signAfter == signBefore) {
                        if (this.longs[i + 1] == null) {
                            this.longs[i] += Long.MAX_VALUE + 1;
                            this.longs[i + 1] = 1L;
                        } else {
                            this.longs[i + 1]++;
                        }
                    }
                } else {
                    this.longs[i] += other.longs[i];
                }
            }

            i++;
        ]
        */
    }

    public void add(long other) {
    }

    public void add(int other) {
    }

    public void set(VeryLong other) {
        for (int i = 0; i < this.longs.length; i++) {
            setLong(other.longs[i], i);
        }
    }

    @Override
    public String toString() {
        BigInteger big = new BigInteger("0");
        for (int i = longs.length - 1; i >= 0; i--) {
            Long currentLong = longs[i];
            if (currentLong != null) {
                big = big.multiply(new BigInteger("18446744073709551616"));
                if (currentLong < 0) {
                    big = big.add(new BigInteger(Long.toString(currentLong + Long.MAX_VALUE + 2)));
                    big = big.add(new BigInteger(Long.toString(Long.MAX_VALUE)));
                } else {
                    big = big.add(new BigInteger(Long.toString(currentLong)));
                }
            }
        }
        return big.toString();
    }

    private void setLong(long newLong, int index){
        this.longs[index] = newLong;
    }

    public Long[] getLongs() {
        return longs;
    }
}
