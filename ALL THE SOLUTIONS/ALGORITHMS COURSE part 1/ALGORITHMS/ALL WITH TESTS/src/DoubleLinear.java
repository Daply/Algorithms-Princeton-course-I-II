import java.util.*;

public class DoubleLinear {
    
//    Consider a sequence u where u is defined as follows:
//
//        The number u(0) = 1 is the first one in u.
//        For each x in u, then y = 2 * x + 1 and z = 3 * x + 1 must be in u too.
//        There are no other numbers in u.
//
//    Ex: u = [1, 3, 4, 7, 9, 10, 13, 15, 19, 21, 22, 27, 31, 39, 40, 43, 45, 46, 54...]
//
//    1 gives 3 and 4, then 3 gives 7 and 10, 4 gives 9 and 13, then 7 gives 15 and 22 and so on...
//
//    #Task: Given parameter n the function dbl_linear (or dblLinear...) returns the element u(n) of the ordered (with <) sequence u.
//
//    #Example: dbl_linear(10) should return 22
//
//    #Note: Focus attention on efficiency

    
    public static int dblLinear (int n) {
        Set<Integer> nums = new TreeSet<Integer>();
        nums = U(0, n, nums);
        return (int) nums.toArray()[n-1];
    }
    
    public static Set<Integer> U(int x, int n, Set<Integer> listOfNumbers) {
            int y = 2 * x + 1;
            int z = 3 * x + 1;
            listOfNumbers.add(y);
            listOfNumbers.add(z);
        if (listOfNumbers.size() <= n) {
            if (y == z){
                U(y, n, listOfNumbers); 
            }
            else{
                U(y, n, listOfNumbers);
                U(z, n, listOfNumbers);
            }
        }
        return listOfNumbers;
    }
    
    public static void assertEquals(long num1, long num2) {
        if (num1 == num2)
            System.out.println("Test passed");
        else 
            System.out.println("Test failed");
    }
    
    public static void main(String args[]) throws Exception {
        // test
        
        assertEquals(DoubleLinear.dblLinear(10), 22);
        assertEquals(DoubleLinear.dblLinear(20), 57);
        assertEquals(DoubleLinear.dblLinear(30), 91);
        assertEquals(DoubleLinear.dblLinear(50), 175);
       
    }
}
