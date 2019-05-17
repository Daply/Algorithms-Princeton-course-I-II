import java.util.Map;
import java.util.TreeMap;

public class RomanNumerals {
    
//    Modern Roman numerals are written by expressing each digit 
//    separately starting with the left most digit and skipping 
//    any digit with a value of zero. In Roman numerals 1990 is rendered: 
//    1000=M, 900=CM, 90=XC; resulting in MCMXC. 2008 is written as 2000=MM, 8=VIII; 
//    or MMVIII. 1666 uses each Roman symbol in descending order: MDCLXVI.
//    
//    Examples
//
//    RomanNumerals.toRoman(1000); // should return 'M'
//    RomanNumerals.fromRoman('M'); // should return 1000
//
//    Help
//    Symbol  Value
//    I   1
//    V   5
//    X   10
//    L   50
//    C   100
//    D   500
//    M   1000
    
    static Map<Integer, String> numbersToRoman = new TreeMap<Integer, String>();
    static Map<String, Integer> numbersFromRoman = new TreeMap<String, Integer>();
    
    public static void fillToRomanMap() {
        numbersToRoman.put(1, "I");
        numbersToRoman.put(5, "V");
        numbersToRoman.put(10, "X");
        numbersToRoman.put(50, "L");
        numbersToRoman.put(100, "C");
        numbersToRoman.put(500, "D");
        numbersToRoman.put(1000, "M");
    }
    
    public static void fillFromRomanMap() {
        numbersFromRoman.put("I", 1);
        numbersFromRoman.put("V", 5);
        numbersFromRoman.put("X", 10);
        numbersFromRoman.put("L", 50);
        numbersFromRoman.put("C", 100);
        numbersFromRoman.put("D", 500);
        numbersFromRoman.put("M", 1000);
    }

    public static String toRoman(long number) {
        fillToRomanMap();
        
        //999
        long left = 0;
        
        
        StringBuilder tens = new StringBuilder();
        StringBuilder hundreds = new StringBuilder();
        StringBuilder thousands = new StringBuilder();
        StringBuilder tenthousands = new StringBuilder();
        
        left = number % 10;
        if (left >= 1 && left <= 3) {
            for (int i = 0; i < left; i++)
                tens.append(numbersToRoman.get(1));
        }
        if (left == 4) {
            tens.append(numbersToRoman.get(1));
            tens.append(numbersToRoman.get(5));
        }
        if (left >= 5 && left <= 8) {
            tens.append(numbersToRoman.get(5));
            for (int i = 5; i < left; i++)
                tens.append(numbersToRoman.get(1));
        }
        if (left == 9) {
            tens.append(numbersToRoman.get(1));
            tens.append(numbersToRoman.get(10));
        }
        
        left = number % 100;
        left = left/10 * 10;
        if (left >= 10 && left <= 30) {
            for (int i = 0; i < left; i+=10)
                hundreds.append(numbersToRoman.get(10));
        }
        if (left == 40) {
            hundreds.append(numbersToRoman.get(10));
            hundreds.append(numbersToRoman.get(50));
        }
        if (left >= 50 && left <= 80) {
            hundreds.append(numbersToRoman.get(50));
            for (int i = 50; i < left; i+=10)
                hundreds.append(numbersToRoman.get(10));
        }
        if (left == 90) {
            hundreds.append(numbersToRoman.get(10));
            hundreds.append(numbersToRoman.get(100));
        }
        hundreds.append(tens);
        
        left = number % 1000;
        left = left/100 * 100;
        if (left >= 100 && left <= 300) {
            for (int i = 0; i < left; i+=100)
                thousands.append(numbersToRoman.get(100));
        }
        if (left == 400) {
            thousands.append(numbersToRoman.get(100));
            thousands.append(numbersToRoman.get(500));
        }
        if (left >= 500 && left <= 800) {
            thousands.append(numbersToRoman.get(500));
            for (int i = 50; i < left; i+=100)
                thousands.append(numbersToRoman.get(100));
        }
        if (left == 900) {
            thousands.append(numbersToRoman.get(100));
            thousands.append(numbersToRoman.get(1000));
        }
        thousands.append(hundreds);
        
        left = number % 10000;
        left = left/1000 * 1000;
        for (int i = 0; i < left; i+=1000)
            tenthousands.append(numbersToRoman.get(1000));
        
        tenthousands.append(thousands);
        
        return tenthousands.toString(); 
        
    }
    
    public static long fromRoman(String number) {
        fillFromRomanMap();
        
        int prevNumber = 0;
        int curNumber = 0;
        long result = 0;
        
        StringBuilder romanNumber = new StringBuilder(number);
        romanNumber.reverse();
        
        for (int i = 0; i < romanNumber.length(); i++) {
            String romNum = "" + romanNumber.charAt(i);
            curNumber = numbersFromRoman.get(romNum);
            
            if (prevNumber > curNumber) {
                result -= curNumber;
            }
            else {
                result += curNumber;
            }
            
            prevNumber = curNumber;
        }
        
        return result; 
    }
    
    public static void assertEquals(String str1, String str2) {
        if (str1.equals(str2))
            System.out.println("Test passed");
        else 
            System.out.println("Test failed");
    }
    
    public static void assertEquals(long num1, long num2) {
        if (num1 == num2)
            System.out.println("Test passed");
        else 
            System.out.println("Test failed");
    }
    
    public static void main(String args[]) throws Exception {
        // test

        assertEquals(RomanNumerals.toRoman(1000), "M");
        assertEquals(RomanNumerals.toRoman(999), "CMXCIX");
        assertEquals(RomanNumerals.toRoman(4), "IV");
        assertEquals(RomanNumerals.toRoman(1), "I");
        assertEquals(RomanNumerals.toRoman(1991), "MCMXCI");
        assertEquals(RomanNumerals.toRoman(2006), "MMVI");
        assertEquals(RomanNumerals.toRoman(2020), "MMXX");

        assertEquals(RomanNumerals.fromRoman("XXI"), 21);
        assertEquals(RomanNumerals.fromRoman("I"), 1);
        assertEquals(RomanNumerals.fromRoman("III"), 3);
        assertEquals(RomanNumerals.fromRoman("IV"), 4);
        assertEquals(RomanNumerals.fromRoman("MMVII"), 2007);
        assertEquals(RomanNumerals.fromRoman("MDCLXIX"), 1669);
       
    }
    
}
