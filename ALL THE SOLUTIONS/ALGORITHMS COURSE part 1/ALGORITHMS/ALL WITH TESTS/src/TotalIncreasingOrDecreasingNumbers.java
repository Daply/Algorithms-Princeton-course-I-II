
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TotalIncreasingOrDecreasingNumbers {
    
    public static BigInteger totalIncDec(int x){
        BigInteger result = BigInteger.ZERO;
        int y = x-1;
        result = result.add(decreasing(y).add(increasing(y)));
        result = result.add(repeats(y));
        if (y >= 1)
            result = result.add(totalIncDec(y-1));
        return result;
    }
    
    public static BigInteger decreasing(int k){
        BigInteger result = BigInteger.ZERO;
        
        int n = 9;
        for (int i = 9; i >= 0; i--) {
            n = 9 - i;
            BigInteger Cnk = Cnk(n, k);
            result = result.add(Cnk);
        }
        
        return result;
    }
    
    public static BigInteger increasing(int k){
        BigInteger result = BigInteger.ZERO;
        
        int n = 9;
        for (int i = 1; i <= 9; i++) {
            n = 9 - i;
            BigInteger Cnk = Cnk(n, k);
            result = result.add(Cnk);
        }
        
        return result;
    }
    
    //   9  - for 2
    // 171 (10*9 + 9*9) - for 3
    // 1339 - for 4
    public static BigInteger repeats(int k){
        BigInteger result = BigInteger.ZERO;
        
        int n = 10;
        for (int i = 0; i <= 9; i++) {
            n = 10 - i;
            BigInteger Knk = Knk(n, i);
            result = result.add(Knk);
        }
        
        return result;
    }
    
    public static BigInteger Knk(int n, int k){
        return factorial(n).divide((factorial(n-k)));
    }
    
    public static BigInteger Cnk(int n, int k){
        return factorial(n).divide((factorial(n-k).multiply((factorial(k)))));
    }
    
    public static BigInteger factorial(int n)
    {
        BigInteger ret = BigInteger.ONE;
        for (int i = 1; i <= n; ++i) ret = ret.multiply(BigInteger.valueOf(i));
        return ret;
    }
    
    public static int bruteForceCheck(int n)
    {
        int num = (int) Math.pow(10, n);
        int prevnum = (int) Math.pow(10, n-1);
        int[] number = new int[num];
        int countInc = 0;
        int countDec = 0;
        int inc = 0;
        int dec = 0;
        for (int i = prevnum; i < num; i++) {
            number = bruteForceGetNumber(i, n);
            inc = 0;
            dec = 0;
            for (int j = 0; j < n - 1; j++) {
                if (number [j] > number [j + 1]) {
                    inc++;
                }
                if (number [j] < number [j + 1]) {
                    dec++;
                }
            }
            if (inc == n - 1) {
                countInc++;
            }
            if (dec == n - 1) {
                countDec++;
            }
            
        }
        return countInc+countDec;
    }
    public static int [] bruteForceGetNumber(int num, int clas)
    {
        int[] number = new int[clas];
        int i = clas - 1;
        while (num != 0) {
            number[i] = num%10;
            num /= 10;
            i--;
        }
        return number;
    }
    
    public static void tester(BigInteger i1, BigInteger i2){
        if (i1.equals(i2))
            System.out.println("Test passed");
        else 
            System.out.println("Test failed");
    }
    
    public static void main(String args[]) throws Exception {
        // testing
        
        // test C
        System.out.println(increasing(3).add(decreasing(3)));
       // System.out.println(repeats(2));
        
        System.out.println(bruteForceCheck(4));
        
        // test totalIncDec
//        System.out.println(totalIncDec(4));
//        System.out.println(totalIncDec(5));
//        System.out.println(totalIncDec(6));
//        System.out.println(totalIncDec(10));
//        System.out.println(totalIncDec(11));
//        
//        tester(BigInteger.valueOf(0), BigInteger.valueOf(1));
//        tester(BigInteger.valueOf(1), BigInteger.valueOf(10));
//        tester(BigInteger.valueOf(2), BigInteger.valueOf(100));
//        tester(BigInteger.valueOf(3), BigInteger.valueOf(475));
//        tester(BigInteger.valueOf(4), BigInteger.valueOf(1675));
    }
}
