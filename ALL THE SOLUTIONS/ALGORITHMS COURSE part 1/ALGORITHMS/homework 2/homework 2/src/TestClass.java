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

class TestClass {
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
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
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
    
    
    static class SubArray{
    	int startIndex = 0;
    	int endIndex = 0;
    	int uniques = 0;
    	int[] subArray;
    	
    	SubArray(int[] fullArray, int start, int end, int uniques){
    		
    		if(end >= start && end < fullArray.length){
    			this.uniques = uniques;
    			startIndex = start;
    			endIndex = end;
    			this.subArray = new int[end-start + 1];
    			int j = 0;
    			for(int i = start; i <= end; i++){
    				this.subArray[j] = fullArray[i];
    				j++;
    			}
    		}
    		
    		
    	}

		@Override
		public String toString() {
			return "SubArray [startIndex=" + startIndex + ", endIndex=" + endIndex + ", uniques=" + uniques + ", subArray="
					+ Arrays.toString(subArray) + "]";
		}
    }
    
    public static void main(String args[] ) throws Exception {

    	Reader s=new Reader();
        BufferedOutputStream out=new BufferedOutputStream(System.out);
		StringBuilder sb=new StringBuilder();
        int size = s.nextInt();
        
        int [] array = new int[size];
        int max = 0;
        for(int i = 0; i < size; i++){
        	array[i] = s.nextInt();
        	if(array[i] > max){
        		max = array[i];
        	}
        }
        
        ArrayList<SubArray> subArraysList = new ArrayList<SubArray>();
        
        // when unique equal 1
        int t = 1;
        int firstI = 0, lastI = 0;
        SubArray arr;
        for(int i = 0; i < size-1; i++){
        	arr = new SubArray(array, i, i, t);
        	subArraysList.add(arr);
        	if(array[i] == array[i+1]){
        		lastI = i+1;
        	}
        	else{
        		if(firstI != lastI){
        			arr = new SubArray(array, firstI, lastI, t);
        			subArraysList.add(arr);
        		}
        		lastI = i+1;
        		firstI = lastI;
        	}
        }
        arr = new SubArray(array, size-1, size-1, t);
        subArraysList.add(arr);
        arr = new SubArray(array, firstI, lastI, t);
        subArraysList.add(arr);
        sb.append(subArraysList.size() + "\n");
        
        // other unique
        SubArray arr1;
        //ArrayList<SubArray> newSubArraysList = new ArrayList<SubArray>();
        for(t = 2; t <= max; t++){
        	// going on previous arrays
        	for(int subIndex = 0; subIndex < subArraysList.size(); subIndex++){
        		arr1 = subArraysList.get(subIndex);
        		int lastInd = arr1.endIndex;
        		int count = arr1.uniques;
        		for(int i = lastInd; i < size; i++){
        			if(!compareNums(arr1.subArray, array[i])){
        				count++;
        			}       			
        			if(count == t){
        				arr = new SubArray(array, arr1.startIndex, i, t);
        				subArraysList.add(arr);
        				//newSubArraysList.add(arr);
        			}
        			if(count > t){
        				break;
        			}
        		}
        		if(arr1.uniques == t-1){
        			subArraysList.remove(subIndex);
        			subIndex--;
        		}
        	}
        	
        	System.out.println(subArraysList);
        	
        	//subArraysList = new ArrayList<SubArray>(newSubArraysList);
        	sb.append(subArraysList.size() + "\n");
        }
       
        
        out.write(sb.toString().getBytes());
		out.flush();
    }
    
    static boolean compareNums(int [] array, int num){
    	if(array != null){
	    	for(int i = 0; i < array.length; i++){
	    		if(array[i] == num){
	    			return true;
	    		}
	    	}
    	}
    	return false;
    }
}
