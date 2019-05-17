package homework;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
       
	   private int n = 0;
	   private int trials = 0;
	   private double xt[] = null;
	
	   public PercolationStats(int n, int trials) {
		   this.n = n;
		   this.trials = trials;
		   this.xt = new double[trials];
	   }
	   public double mean() {
		   return StdStats.mean(xt);
	   }
	   public double stddev() {
		   return StdStats.stddev(xt);
	   }
	   public double confidenceLo() {
		   double confidenceLo = 0;	   
		   confidenceLo = (mean()-1.96*stddev());
		   confidenceLo = confidenceLo/(double)Math.sqrt((double)trials);
		   return confidenceLo;
	   }
	   public double confidenceHi() {
		   double confidenceHi = 0;
		   confidenceHi = (mean()+1.96*stddev()); 
		   confidenceHi = confidenceHi/(double)Math.sqrt((double)trials);
		   return confidenceHi;
	   }

	   public static void main(String[] args) {
		   //int n = StdIn.readInt();
		   //int trials = StdIn.readInt();
		   int n = Integer.parseInt(args[0]);
		   int trials = 0;
		   if (args.length == 2) 
			   trials = Integer.parseInt(args[1]);
		   PercolationStats percStat = new PercolationStats(n,trials);
		   Percolation perc = new Percolation(n);

		   int i = 0;
		   int j = 0;
		   
		   for(int ind = 0; ind < trials; ind++) {
			   perc = new Percolation(n);
			   while(!perc.percolates()) {   
				   i = StdRandom.uniform(1, n+1);
				   j = StdRandom.uniform(1, n+1);
				   perc.init(i, j);
			   }
			   //PercolationVisualizer.draw(perc, n);
			   percStat.xt[ind] = (double)(perc.numberOfOpenSites())/(double)(n*n);
		   }
		   
		   StdOut.println("mean                     = " + percStat.mean());
		   StdOut.println("stddev                   = " + percStat.stddev());
		   StdOut.println("95% confidence interval  = " + "[" + percStat.confidenceLo() + ", "
				   										+ percStat.confidenceHi() + "]");
		   
	   }
}
