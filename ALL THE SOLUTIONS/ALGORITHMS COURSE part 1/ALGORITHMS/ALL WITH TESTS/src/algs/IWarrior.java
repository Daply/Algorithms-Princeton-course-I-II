package algs;

public class IWarrior {

    public static Warrior selectMedian(Warrior[] warriors){     
        // cases:
        // 0 > 1 > 2 > 3 > 4
        // 0 < 1 < 2 < 3 < 4
        
        
        // 0 < 1 > 2 > 3 > 4
        // 0 < 1 > 2 < 3 < 4
        // 0 < 1 > 2 > 3 < 4
        
        // 0 > 1 > 2 < 3 > 4
        // 0 < 1 < 2 < 3 > 4
        // 0 > 1 < 2 < 3 > 4
        // 0 > 1 < 2 > 3 > 4
        // 0 > 1 < 2 < 3 < 4
        // 0 > 1 > 2 > 3 < 4
        // 0 < 1 < 2 > 3 < 4
        
        // 0 < 1 < 2 > 3 > 4
        // 0 > 1 > 2 < 3 < 4
  
        // 0 < 1 > 2 < 3 > 4
        // 0 > 1 < 2 > 3 < 4 
        
        boolean result1 = warriors[0].isBetter(warriors[1]);
        boolean result2 = warriors[1].isBetter(warriors[2]);
        boolean result3 = warriors[2].isBetter(warriors[3]);
        boolean result4 = warriors[3].isBetter(warriors[4]);
        boolean result5 = false;
        boolean result6 = false;
        
        // 0 > 1 > 2 > 3 > 4
        // 0 < 1 < 2 < 3 < 4
        if ((result1 && result2 && result3 && result4) ||
                (!result1 && !result2 && !result3 && !result4)) {
            return warriors[2];
        }
        // 0 < 1 > 2 > 3 > 4  ->  1 > 0 && 1 > 2 > 3 > 4
        if (!result1 && result2 && result3 && result4) {
            result5 = warriors[2].isBetter(warriors[0]);
            if (result5) {
                // 1 > 2 > 0 && 1 > 2 > 3 > 4
                result6 = warriors[3].isBetter(warriors[0]);
                if (result6) {
                    // 1 > 2 > 3 > 0 && 3 > 4
                    return warriors[3];
                }
                else {
                   // 1 > 2 > 0 > 3 > 4
                    return warriors[0];
                }
            }
            else {
                // 1 > 0 > 2 > 3 > 4
                return warriors[2];
            }
        }
        // 0 < 1 > 2 < 3 < 4 ->  4 > 3 > 2 && 1 > 0 && 1 > 2
        if (!result1 && result2 && !result3 && !result4) {
            result5 = warriors[1].isBetter(warriors[3]);
            if (result5) {
                // 4 > 3 > 2 && 1 > 0 && 1 > 3 > 2
                result6 = warriors[0].isBetter(warriors[4]);
                if (result6) {
                    // 1 > 0 > 4 > 3 > 2
                    return warriors[4];
                }
                else {
                   // 4 > 3 > 2 && 1 > 0 && 1 > 3 > 2 && 4 > 0
                    //TODO
                }
            }
            else {
               // 1 > 0 && 1 > 2 && 1 < 3 < 4
                return warriors[1];
            }
        }
        // 0 < 1 > 2 > 3 < 4  ->  1 > 2 > 3 && 1 > 0 && 4 > 3
        if (!result1 && result2 && result3 && !result4) {
            result5 = warriors[1].isBetter(warriors[3]);
            
        }
        
        return warriors[0];
    }
    
    public static class Warrior {
        int rank = 0;
        Warrior (int rank) {
            this.rank = rank;
        }
        
        public boolean isBetter(Warrior w) {
            if (this.rank > w.rank)
                return true;
            else 
                return false;
        }
    }
    
    
    public static void main(String args[]) throws Exception {
        Warrior[] input = new Warrior[]{
                new Warrior(1),
                new Warrior(4),
                new Warrior(5),
                new Warrior(3),
                new Warrior(2),
              };
        System.out.println(selectMedian(input));
    }
}
