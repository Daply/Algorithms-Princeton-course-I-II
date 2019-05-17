import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CSVParser {

    static class SortByRSQ implements Comparator<String[]> 
    { 
        // Used for sorting in ascending order of 
        // roll number 
        public int compare(String[] a, String[] b) 
        { 
            return Integer.parseInt(a[2]) - Integer.parseInt(b[2]); 
        } 
    } 
    
    public static void main(String[] args) {
        File file = new File("D:\\records.txt");

        StringBuilder result = new StringBuilder();
        StringBuilder maxMinFields = new StringBuilder();
        StringBuilder generateDDL = new StringBuilder();
        StringBuilder generateInsert = new StringBuilder();
        
        StringBuilder halfFile = new StringBuilder();
        
        try {

            Scanner sc = new Scanner(file);
            Set<Integer> rsqSet = new TreeSet<Integer>();
            List<String[]> recordsList = new ArrayList<String[]>();

            int countRecords = 0;
            String[] fields = null;
            String[] fieldsTypes = null;
            String[] maxFields = null;
            String[] minFields = null;
            String newL = System.getProperty("line.separator");
            String recordName = "";
            String ownerName = "";
            
            int lineCounter = 0;
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                
                if (str.contains("RECORD NAME")){
                    String[] arrString = str.split(";");
                    int recInd = 0;
                    for (int i = 0; i < arrString.length; i++){
                        if (arrString[i].equals("RECORD NAME")){
                            recInd = i+1;
                            break;
                        }
                    }
                    recordName = arrString[recInd];
                }
                
                if(lineCounter < 10000)
                    halfFile.append(str + newL);
                lineCounter++;
                
                String[] nextString = str.split(";");
                if (nextString[0].equals("FIELDS")){
                    fields = new String[nextString.length];
                    fieldsTypes = new String[nextString.length];
                    maxFields = new String[nextString.length];
                    minFields = new String[nextString.length];
                    for (int i = 0; i < nextString.length; i++){
                        String field = nextString[i].replaceAll("\\s", "-").toUpperCase();
                        fields[i] = field;
                    }
                }
                if (nextString[0].equals("RECORD")){
                    result.append("-------- RECORD " + countRecords  + " --------" + newL);
                    recordsList.add(nextString);
                    for (int i = 1; i < nextString.length; i++){
                        result.append(fields[i] + ": " + nextString[i] + newL);
                        
                        // check if there duplicates
                        if(fields[i].equals("DB-KEY-RSQ")){
                            if(rsqSet.contains(Integer.parseInt(nextString[i]))){
                                System.out.println("duplicate!! : " + nextString[i]);
                            }
                            rsqSet.add(Integer.parseInt(nextString[i]));
                        }
                        
                        
                        if (countRecords == 0){
                            maxFields[i] = nextString[i];
                            minFields[i] = nextString[i];
                        }
                        else{
                            if(nextString[i].matches("\\-?[0-9]+")){
                                int num = Integer.parseInt(nextString[i]);
                                int maxNum = Integer.parseInt(maxFields[i]);
                                int minNum = Integer.parseInt(minFields[i]);
                                if (num > maxNum) {
                                    maxFields[i] = nextString[i];
                                }
                                if (num < minNum) {
                                    minFields[i] = nextString[i];
                                }
                            }
                            else if(nextString[i].matches("\\-?[0-9]+(\\.[0-9]+)")){
                                double num = Double.parseDouble(nextString[i]);
                                double maxNum = Double.parseDouble(maxFields[i]);
                                double minNum = Double.parseDouble(minFields[i]);
                                if (num > maxNum) {
                                    maxFields[i] = nextString[i];
                                }
                                if (num < minNum) {
                                    minFields[i] = nextString[i];
                                }
                            }
                            else{
                                int len = nextString[i].length();
                                double maxLen = maxFields[i].length();
                                if (len > maxLen) {
                                    maxFields[i] = nextString[i];
                                }
                            }
                        }
                    }
                   
                    countRecords++;
                }
            }
            System.out.println(lineCounter);
            // generate ddl
            fieldsTypes[0] = fields[0];
            String offset = "        ";
            for (int i = 1; i < maxFields.length; i++){
                
                maxMinFields.append(fields[i] + ": MAX: " + maxFields[i] +
                        ": MIN: " + minFields[i] + newL);
                
                if(minFields[i].matches("\\-?[0-9]+")){
                    int num = Integer.parseInt(minFields[i]);
                    if (num < 0) {
                        fieldsTypes[i] = "TYPE BIN 31."; 
                    }
                    else{
                        int len = maxFields[i].length();
                        fieldsTypes[i] = "PIC 9(" + len + ")."; 
                    }
                }
                else if(minFields[i].matches("\\-?[0-9]+(\\.[0-9]+)")){
                     fieldsTypes[i] = "TYPE DEC 10,10."; 
                }
                else{
                    int len = maxFields[i].length();
                    fieldsTypes[i] = "TYPE CHAR " + (len + 10) + "."; 
                }
            }
            
            for (int i = 1; i < fields.length; i++){
                if(fields[i].contains("DB-KEY"))
                    continue;
                generateDDL.append(offset + "01 " + fields[i] + " " + fieldsTypes[i] + newL);
            }

            int size = recordsList.size();
            recordsList.sort(new SortByRSQ());
            
            int part = 1;
            for (int i = 0; i < size; i++){          
                generateInsert.append(newL);
                
                if (i == part*size/2){
                 // write insert
                    writePart(part, generateInsert);
                    part++;
                    generateInsert = new StringBuilder();
                }
                
                for(int j = 1; j < fields.length; j++){       
                    
                    if(fields[j].contains("OWNER-DB-KEY-REC-REF")){
                        
                        generateInsert.append(offset + "MOVE " + recordsList.get(i)[j] + 
                                " TO REC-REF OF DB-KEY-RED." + newL);
                        generateInsert.append(offset + "MOVE 0 TO FILLER OF DB-KEY-RED." + newL);
                    }
                    else if(fields[j].contains("OWNER-DB-KEY-RSQ")){
                        
                        generateInsert.append(offset + "MOVE " + recordsList.get(i)[j] + 
                                " TO RSQ OF DB-KEY-RED." + newL);
                        generateInsert.append(offset + "FETCH DATABASE-KEY IS DB-KEY;" + newL);
                        generateInsert.append(offset + "FETCH ACCOUNTS." + newL);
                    }
                    else if (!fields[j].contains("DB-KEY") && 
                            !fields[j].contains("AREA-REF")){
                            String str = offset + "MOVE " + recordsList.get(i)[j];
                            if (str.length() >= 45){
                                str += newL + offset;
                            }
                            generateInsert.append(str + 
                                    " TO " + fields[j] +
                                    " OF " + recordName + "." + newL);   
                    }
                }
                generateInsert.append(offset + "STORE " + recordName + "." + newL);
            }
            
            // write half file
            writeHalfFile(halfFile);
            
            sc.close();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        // write records
        try(FileWriter writer = new FileWriter("D:\\result_records.txt", false))
        {
            writer.write(result.toString());
             
            writer.flush();
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
        
        // write max and min fields
        try(FileWriter writer = new FileWriter("D:\\maxmin_records.txt", false))
        {
            writer.write(maxMinFields.toString());
             
            writer.flush();
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
        
        // write ddl
        try(FileWriter writer = new FileWriter("D:\\ddl_records.txt", false))
        {
            writer.write(generateDDL.toString());
             
            writer.flush();
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
        
    }
    
    public static void writePart(int part, StringBuilder generateInsert){
        try(FileWriter writer = new FileWriter("D:\\insert_records_" + part + ".txt", false))
        {
            writer.write(generateInsert.toString());
             
            writer.flush();
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
    }
    
    public static void writeHalfFile(StringBuilder str){
        try(FileWriter writer = new FileWriter("D:\\records.csv", false))
        {
            writer.write(str.toString());
             
            writer.flush();
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
    }
}
