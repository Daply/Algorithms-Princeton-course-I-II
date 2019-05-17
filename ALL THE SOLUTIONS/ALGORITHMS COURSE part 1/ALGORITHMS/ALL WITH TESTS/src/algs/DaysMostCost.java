package algs;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class DaysMostCost {
    public static void main(String[] args) {
        Map <Integer, Integer> map = new TreeMap<Integer, Integer>();
        map.put(1, 90);
        map.put(2, 105);
        map.put(3, 75);
        map.put(4, 35);
        map.put(5, 120);
        
        Iterator<Integer> it = map.values().iterator();
        int currentMinimum = it.next();
        int next = 0;
        int difference = 0;
        int currentDifference = 0;
        int firstDay = 1;
        int secondDay = 2;
        int dayIndex = 2;
        Map <Integer, Integer> days = new TreeMap<Integer, Integer>();
        while (it.hasNext()) {
            next = it.next();
            if (next > currentMinimum) {
                difference = next - currentMinimum;
                if (difference > currentDifference) {
                    currentDifference = difference;
                    secondDay = dayIndex;
                }
            }
            else {
                if (secondDay > firstDay)
                    days.put(firstDay, secondDay);
                currentMinimum = next;
                firstDay = dayIndex;
            }
            dayIndex++;
        }
        if (secondDay > firstDay)
            days.put(firstDay, secondDay);
        
        Iterator it1 = days.entrySet().iterator();
        while (it1.hasNext()) {
            Entry<Integer, Integer> entry = (Entry<Integer, Integer>) it1.next();
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }
    
}
