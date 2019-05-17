import static org.junit.Assert.*;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueueTest {

//    Test 7: check that iterator() returns correct 
//    items after a sequence of n enqueue() operations
//    * n = 10 
//    - number of entries in student solution: 10 
//    - number of entries in reference solution: 10 
//    - 4 extra entries in student solution, including: '4' 
//    - 4 missing entries in student solution, including: '10'
    @Test
    public void testIterator() {
        // TODO
        RandomizedQueue<Integer> rnq1;
        rnq1 = new RandomizedQueue<Integer>();
        
        for (int i = 1; i <= 10; i++) {
            rnq1.enqueue(i);
        }
        
        Iterator<Integer> it1 = rnq1.iterator();
        while (it1.hasNext()) {
            int num = it1.next();
            StdOut.println(num);
            assertNotEquals(num, null);
        }   
        
        RandomizedQueue<String> rnq2;
        rnq2 = new RandomizedQueue<String>();
        
        rnq2.enqueue("LNlnl");
        rnq2.enqueue("vsd");
        rnq2.enqueue("dfg");
        rnq2.enqueue("vsoyikd");
        rnq2.enqueue("hrr");
        rnq2.enqueue("luil");
        rnq2.enqueue("IFKXBQPXCK");
        rnq2.enqueue("BCEISEFNVE");
        rnq2.enqueue("EGFEVQVWLA");
        rnq2.enqueue("XSHPRFAZCH");
        rnq2.enqueue("mghm");
        rnq2.enqueue("lDFDFBiy");
        rnq2.enqueue("uydtyd");
        rnq2.enqueue("DFSBGDFBHDB");
        rnq2.enqueue("mgho8kiy8iykm");
        rnq2.enqueue("-0[GVDrfyif");
        rnq2.enqueue("fyjuhjmh");
        rnq2.enqueue("JYHFGNJYNJ");
        rnq2.enqueue("wrqtetWWD");
        rnq2.enqueue("rtete");
        rnq2.enqueue("rtetDGFBHGFe");
        
        Iterator<String> it2 = rnq2.iterator();
        while (it2.hasNext()) {
            String s = it2.next();
            StdOut.println(s);
            assertNotEquals(s, null);
        }   
    }
}
