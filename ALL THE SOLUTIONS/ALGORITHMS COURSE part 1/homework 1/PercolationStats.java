// import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * 
 * @author Pleshchankova Daria
 *
 */
public class PercolationStats {
       
	   private int trials = 0;
	   private double [] xt = null;
	   private static final double CONFIDENCE_95 = 1.96;
	   private double mean = 0;
	   private double stddev = 0;
	
	   /**
	    * Constructor
	    * @param n - size of grid
	    * @param trials - number of trials of experiment
	    */
	   public PercolationStats(int n, int trials) {
	       if (n <= 0) {
	            throw new IllegalArgumentException("n: "+ n + " is less or equal than 0 ");  
	       }
	       if (trials <= 0) {
               throw new IllegalArgumentException("trials: "+ trials + " is less or equal than 0 ");  
           }
		   this.trials = trials;
		   this.xt = new double [trials];
		   
		   // counting mean and standard deviation
           int i = 0;
           int j = 0;		   
           for (int ind = 0; ind < trials; ind++) {
               Percolation perc = new Percolation(n);
               while (!perc.percolates()) {   
                   i = StdRandom.uniform(1, n+1);
                   j = StdRandom.uniform(1, n+1);
                   if (!perc.isOpen(i, j))
                       perc.open(i, j);
               }
               // PercolationVisualizer.draw(perc, n);
               xt[ind] = (double) (perc.numberOfOpenSites())/(double) (n*n);
           }
           mean = StdStats.mean(xt);
           stddev = StdStats.stddev(xt);
	   }
	   
	   /**
	    * Counting mean
	    * @return mean
	    */
	   public double mean() {
		   return mean;
	   }
	   
	   /**
	    * Counting standard deviation
	    * @return stddev
	    */
	   public double stddev() {
		   return stddev;
	   }
	   
	   /**
	    * Counting confidence low interval
	    * @return confidenceLo
	    */
	   public double confidenceLo() {
		   double confidenceLo = mean - (CONFIDENCE_95*stddev/Math.sqrt((double) trials));
		   return confidenceLo;
	   }
	   
	   /**
	    * Counting confidence high interval
	    * @return confidenceHi
	    */
	   public double confidenceHi() {
		   double confidenceHi = mean + (CONFIDENCE_95*stddev/Math.sqrt((double) trials)); 
		   return confidenceHi;
	   }

	   public static void main(String[] args) {
//		     int n = StdIn.readInt();
//		     int trials = StdIn.readInt();
		    int n = Integer.parseInt(args[0]);
		    int trials = 0;
		    if (args.length == 2) 
			    trials = Integer.parseInt(args[1]);
		   PercolationStats percStat = new PercolationStats(n, trials); 
		   
		   StdOut.println("mean                     = " + percStat.mean());
		   StdOut.println("stddev                   = " + percStat.stddev());
		   StdOut.println("95% confidence interval  = " + "[" + percStat.confidenceLo() + ", "
				   										+ percStat.confidenceHi() + "]");
	   }
}
