package algs;

import java.math.BigInteger;

public class Fibonacci {
    // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89
    public static BigInteger fib(BigInteger n) {
        BigInteger a = BigInteger.ONE;
        BigInteger b = BigInteger.ONE;
        BigInteger i = BigInteger.valueOf(2);
        while (i.compareTo(n) == -1) {
            // b = a + b;
            // a = b - a;
            b = b.add(a);
            a = b.subtract(a);
            i = i.add(BigInteger.ONE);
        }
        if (n.equals(BigInteger.ZERO))
            return n;
        return b;
    }
    
    public static void main(String args[]) throws Exception {
        System.out.println(fib(BigInteger.valueOf(0))); // 0
        System.out.println(fib(BigInteger.valueOf(1))); // 1
        System.out.println(fib(BigInteger.valueOf(2))); // 1
        System.out.println(fib(BigInteger.valueOf(3))); // 2
        System.out.println(fib(BigInteger.valueOf(4))); // 3
        System.out.println(fib(BigInteger.valueOf(5))); // 5
        System.out.println(fib(BigInteger.valueOf(6))); // 8
        System.out.println(fib(BigInteger.valueOf(7))); // 13
    }
}
