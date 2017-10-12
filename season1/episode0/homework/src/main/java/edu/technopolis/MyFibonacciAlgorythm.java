package main.java.edu.technopolis;

import igorlo.veryLong.VeryLong;

public class MyFibonacciAlgorythm implements FibonacciAlgorithm {
    @Override
    public String evaluate(int index) {
        if (index < 3) {
            return "1";
        }
        VeryLong one = new VeryLong(1L);
        VeryLong two = new VeryLong(1L);
        VeryLong result = new VeryLong(1L);
        for (int i = 2; i < index; i++) {
            result = VeryLong.getSum(one, two);
            one.set(two);
            two.set(result);
            //System.out.println(result.toString());
        }
        return result.toString();
    }
}
