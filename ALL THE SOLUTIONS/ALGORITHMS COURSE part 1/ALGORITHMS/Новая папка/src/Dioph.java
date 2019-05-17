
public class Dioph {
    public static String solEquaStr(long n) {
        
        StringBuilder result = new StringBuilder();
        for (long div1 = 2; div1 <= Math.floor(Math.sqrt(n)); div1++) {
            if (n % div1 == 0) {
                long div2 = n / div1; 
                long x = (div1 + div2)/2;
                long y = Math.abs(div1 - div2)/4;
                result.append("[" + x + ", "+ y + "]");
            }
        }
        return result.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(solEquaStr(90005));
    }
}
