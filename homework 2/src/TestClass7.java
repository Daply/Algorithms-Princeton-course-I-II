/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
*/
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class TestClass7 {
    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
 
        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public String readLine() throws IOException
        {
            byte[] buf = new byte[100]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n' && cnt > 0){
                    break;
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }
 
        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');
 
            if (neg)
                return -ret;
            return ret;
        }
 
        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }
 
        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
 
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
 
            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }
 
            if (neg)
                return -ret;
            return ret;
        }
 
        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
 
        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
 
        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
    
  
    public static void main(String args[] ) throws Exception {

    	Reader s=new Reader();
        BufferedOutputStream out=new BufferedOutputStream(System.out);
		StringBuilder sb=new StringBuilder();
		
		int tests = s.nextInt();
		for(int t = 0; t < tests; t++){

			int d = s.nextInt();
	    	
	    	int sum = 0;
	    	
	    	int arr[] = new int[(int) Math.sqrt(d)];
	    	int k = 0;
	    	double num = d;
	    	for(int i = 1; i < d; i++){
	    		for(int j = 1; j < d; j++){
	    			double ans = Math.sqrt((Math.pow(i, 2) +  Math.pow(j, 2)));
	    			if(num == ans){		
	    				sum = i+j;
	    				arr[k] = sum;
	    				k++;
	    			}
	    		}
	    	}
	    	
	    	int L = 0;
	    	int E = 0;
	    	int R = 0;
	    	
	    	int arr1[] = arr.clone();
	    	for(int i = 0; i < arr1.length; i++){
	    		for(int j = i; j < arr1.length; j++){
	    			if(arr1[i] == arr1[j]){
	    				arr1[j] = 0;
	    				E++;
	    			}
	    		}
	    	}
	    	
	    	int arr2[] = arr.clone();
	    	for(int i = 0; i < arr2.length; i++){
	    		for(int j = i; j < arr2.length; j++){
	    			if(arr2[i] < arr2[j]){
	    				arr2[j] = 0;
	    				L++;
	    			}
	    		}
	    	}
	    	
	    	int arr3[] = arr.clone();
	    	for(int i = 0; i < arr3.length; i++){
	    		for(int j = i; j < arr3.length; j++){
	    			if(arr3[i] > arr3[j]){
	    				arr3[j] = 0;
	    				R++;
	    			}
	    		}
	    	}
			
			sb.append(L*2 + " " + E*2 + " " + R*2 + "\n");
		}
		
		
        out.write(sb.toString().getBytes());
		out.flush();
    }
    
}
