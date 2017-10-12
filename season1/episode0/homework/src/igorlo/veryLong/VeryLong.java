package igorlo.veryLong;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MatchGenerator;

import java.math.BigInteger;
import java.util.ArrayList;

public class VeryLong {

    private Long[] longs;

    public final static int DEFAULT_CAPACITY = 5;

    public VeryLong() {
        longs = new Long[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            longs[i] = null;
        }
    }

    public VeryLong(long number) {
        longs = new Long[DEFAULT_CAPACITY];
        longs[0] = number;
        for (int i = 1; i < DEFAULT_CAPACITY; i++) {
            longs[i] = null;
        }
    }

    public VeryLong(int capacity) {
        longs = new Long[capacity];
        for (int i = 0; i < capacity; i++) {
            longs[i] = null;
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
        VeryLong sum = new VeryLong();
        sum.set(one);
        int i = 0;
        while (other.longs[i] != null && i < sum.longs.length) {
            if (sum.longs[i] < 0 && other.longs[i] < 0) {
                sum.longs[i] += other.longs[i] + 1;// + Long.MAX_VALUE + 1;
                if (sum.longs[i + 1] == null) {
                    sum.longs[i + 1] = 1L;
                } else {
                    sum.longs[i + 1]++;
                }
            } else {
                if (sum.longs[i] < 0 || other.longs[i] < 0) {
                    boolean signBefore = sum.longs[i] < 0;
                    sum.longs[i] += other.longs[i] + 1;
                    boolean signAfter = sum.longs[i] < 0;
                    if (signAfter == signBefore) {
                        if (sum.longs[i + 1] == null) {
                            //sum.longs[i] += Long.MAX_VALUE + 1;
                            sum.longs[i + 1] = 1L;
                        } else {
                            sum.longs[i + 1]++;
                        }
                    }
                } else {
                    sum.longs[i] += other.longs[i];
                }
            }

            i++;
        }
        return sum;
    }

    public void add(VeryLong other) {
        int i = 0;
        while (other.longs[i] != null && i < longs.length) {
            if (this.longs[i] < 0 && other.longs[i] < 0) {
                this.longs[i] += other.longs[i] + Long.MAX_VALUE + 1;
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
        }
    }

    public void add(long other) {
    }

    public void add(int other) {
    }

    public VeryLong set(VeryLong other) {
        VeryLong result = new VeryLong(0L);
        for (int i = 0; i < longs.length; i++) {
            if (other.longs[i] == null)
                break;
            longs[i] = other.longs[i].longValue();
        }
        return result;
    }

    @Override
    public String toString() {
        BigInteger big = new BigInteger("0");
        for (int i = longs.length - 1; i >= 0; i--) {
            Long currentLong = longs[i];
            if (currentLong != null) {
                //BigInteger multiplier = new BigInteger(Long.toString(Long.MAX_VALUE));
                //multiplier = multiplier.add(new BigInteger("1"));
                big = big.multiply(new BigInteger(Long.toString((long) (Math.pow(2, 62)))));
                //big = big.multiply(new BigInteger(Long.toString(Long.MAX_VALUE)));
                //big = big.multiply(multiplier);
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

    public Long[] getLongs() {
        return longs;
    }
}
