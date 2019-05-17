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

class TestClass3 {
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
    
  
    public static void main(String args[] ) throws Exception {

    	Reader s=new Reader();
        BufferedOutputStream out=new BufferedOutputStream(System.out);
		StringBuilder sb=new StringBuilder();
        String string = s.readLine().trim();
        int num = s.nextInt();
        

        for(int i = 0; i < string.length(); i++){
        	int charNum = string.charAt(i);
        	char result = ' ';
        	
        	if((charNum >= 48 && charNum <= 57) ||
            		(charNum >= 65 && charNum <= 90) ||
            		(charNum >= 97 && charNum <= 122)){
        	
        	if(charNum >= 48 && charNum <= 57){
        		 result = number(48, 57, charNum, num);
        	}
        	
        	if(charNum >= 65 && charNum <= 90){
       		     result = number(65, 90, charNum, num);
       	    }
        	
        	if(charNum >= 97 && charNum <= 122){
       		     result = number(97, 122, charNum, num);
       	    }

        	sb.append(result);
        	}
        	
        	else{
        		sb.append((char)(charNum));
        	}
        }
        
        out.write(sb.toString().getBytes());
		out.flush();
    }
    
    static char number(int a, int b, int charNumber, int num){
    	char result = (char) (charNumber);	
    	int diff = num%((b-a)+1);
    	if(charNumber+diff > b){
    		int left = diff - (b - charNumber)-1;
    		result = (char)(a + left);
    	}
    	else{
    		result = (char)(charNumber + diff);
    	}
    	
    	return result;
    	
    }

}
