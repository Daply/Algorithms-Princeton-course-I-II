import java.io.DataInputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class XMLGenerator {

	private DataInputStream din;

    public static void main(String[] args) {


        Scanner s = new Scanner(System.in);
        String testCases = s.nextLine();  
        
        Map<Integer,Integer> map = new TreeMap<Integer,Integer>();
        
        for(int i = 0; i < Integer.parseInt(testCases); i++){
            String sizeOfArray = s.nextLine();
            String arr = s.nextLine();
            String [] arrNum = arr.split(" ");
            for(int j = 0; j < Integer.parseInt(sizeOfArray); j++){
                map.put(Integer.parseInt(arrNum[j]), j+1);
            }
            
            String numOfN = s.nextLine();
            
            for(int j = 0; j < Integer.parseInt(numOfN); j++){
                String x = s.nextLine();
                if(map.containsKey(Integer.parseInt(x))){
                	System.out.println(map.get(Integer.parseInt(x)));
                }
                else{
                	System.out.println(-1);
                }
            }
        } 


    }
}
