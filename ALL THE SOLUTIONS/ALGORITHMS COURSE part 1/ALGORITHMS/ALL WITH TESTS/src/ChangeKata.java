import java.util.*;

public class ChangeKata {
    
    
    public static List<Integer> findChange(long num, int[] coins) {
        Arrays.sort(coins);
        List<Integer> list = collectChange(num, coins);
        return list;
    }
    
    public static List<Integer> collectChange(long n, int[] coins) {
           List<Integer> list = new ArrayList<Integer>();
           
           if (n == 1) {
               for (int i = 0; i < coins.length; i++) {
                    if (coins[i] == n) {
                        list.add(1);
                    }
               }
           }
           else {
               long num = 0;
               for (int i = 0; i < coins.length; i++) {
                   if (coins[i] == n) {
                       list.add((int) n);
                   }
                   if (n > coins[i]) {
                       num = n - coins[i];
                       if (coins[i] == num) {
                           list.add((int) num);
                       }
                       List<Integer> prevList = collectChange(num, coins);
                       if (!prevList.isEmpty())
                            list.addAll(prevList);
                   }
               }
           }
        return list;
    }
    
    public static void assertEquals(long num1, long num2) {
        if (num1 == num2)
            System.out.println("Test passed");
        else 
            System.out.println("Test failed");
    }
    
    public static void main(String args[]) throws Exception {
        // test
        
        List<Integer> list = findChange(4, new int[] {1,2});  //3
        System.out.println(list);
        list = findChange(10, new int[] {5,2,3}); //4
        System.out.println(list);
        list = findChange(11, new int[] {5,7}); //0
        System.out.println(list);
        
        
        assertEquals(RomanNumerals.fromRoman("XXI"), 21);
        assertEquals(RomanNumerals.fromRoman("I"), 1);
        assertEquals(RomanNumerals.fromRoman("III"), 3);
        assertEquals(RomanNumerals.fromRoman("IV"), 4);
        assertEquals(RomanNumerals.fromRoman("MMVII"), 2007);
        assertEquals(RomanNumerals.fromRoman("MDCLXIX"), 1669);
       
    }
    
}
