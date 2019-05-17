package algs;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DateReverse {
    
    // There will be given two years
    // for example: 2009 and 2018
    // between these two years find
    // one with the highest number
    // of reverse days, that contains in itself
    // 
    // in european format (day/month/year):
    // reverse day: 31.10.12   21.01.13
    // 31.10.12 -> 21.01.13
    // 31.01.12 -> 21.10.13
    // 31.11.12 -> 21.11.13
    // 21.01.13 -> 31.10.12
    // 21.01.13 -> 31.01.12
    // 21.01.13 -> 31.11.12    
    
    
    // months: 01 -> 10, 10 -> 01, 11 -> 11
    // days: 01 -> 10, 02 -> 20, 03 -> 30, 10 -> 01, 11 -> 11
    // 12 -> 21, 13 -> 31, 20 -> 02, 21 -> 12, 22 -> 22
    // 30 -> 03, 31 -> 13
    
    static Map <String, String> months = null;
    static Map <String, String> days = null;
    
    static Set <Integer> reverseDays = null;
            
    public static void createMaps() {
        months = new HashMap<String, String>();
        days = new HashMap<String, String>();
        
        months.put("01", "10");
        months.put("10", "01");
        months.put("11", "11");
        
        days.put("01", "10");
        days.put("02", "20");
        days.put("03", "30");
        days.put("10", "01");
        days.put("11", "11");
        days.put("12", "21");
        days.put("13", "31");
        days.put("20", "02");
        days.put("21", "12");
        days.put("22", "22");
        days.put("30", "03");
        days.put("31", "13");
    }
    
    public static void createSet() {
        reverseDays = new HashSet<Integer>();
        reverseDays.add(1);
        reverseDays.add(2);
        reverseDays.add(3);
        reverseDays.add(10);
        reverseDays.add(11);
        reverseDays.add(12);
        reverseDays.add(13);
        reverseDays.add(20);
        reverseDays.add(21);
        reverseDays.add(22);
        reverseDays.add(30);
        reverseDays.add(31);
    }
    
    public static int numberOfReverseDates(int year1, int year2) {
        createSet();
        int countReverseYears = 0;
        int countNonExistDates = 0;
        for (int year = year1; year <= year2; year++) {
            if (reverseDays.contains(year%100))
                countReverseYears++;
            if (year%100 == 13 || year%100 == 31)
                countNonExistDates++;
        }
        if (countReverseYears == 0)
            return 0;
        
        return Knk(countReverseYears, 2).intValue()*3-countNonExistDates*2;
        
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
    
    public static void main(String args[]) throws Exception {
        // 4
        System.out.println(numberOfReverseDates(2012, 2013));
        // 18
        System.out.println(numberOfReverseDates(2010, 2012));
        // 86
        System.out.println(numberOfReverseDates(2013, 2031));
        // 3
        System.out.println(numberOfReverseDates(2009, 2010));
        // 0
        System.out.println(numberOfReverseDates(2008, 2009));
        // 1
        System.out.println(numberOfReverseDates(2031, 2099));
        // 6
        System.out.println(numberOfReverseDates(2002, 2009));
    }

}
