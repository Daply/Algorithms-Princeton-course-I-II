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

public class CSVSetReader {

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
        StringBuilder notSortedResult = new StringBuilder();        
        try {

            Scanner sc = new Scanner(file);
            String[] fields = null;
            String newL = System.getProperty("line.separator");
            String recordName = "";

            boolean writeSwitch = false;
            List<Record> recordsList = new ArrayList<Record>();
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                
//                if(!writeSwitch){
//                    notSortedResult.append(str + newL);
//                }
                
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
                String[] nextString = str.split(";");
                if (nextString[0].equals("FIELDS")){
                    fields = new String[nextString.length];
                    for (int i = 0; i < nextString.length; i++){
                        String field = nextString[i].replaceAll("\\s", "-").toUpperCase();
                        fields[i] = field;
                    }
                }
                if (nextString[0].equals("RECORD")){
                    int recRef = 0;
                    int RSQ = 0;
                    int ownerRecRef = 0;
                    int ownerRSQ = 0;
                    if(fields[1].contains("DB-KEY")){ 
                        recRef = Integer.parseInt(nextString[1]);
                    }
                    if(fields[2].contains("DB-KEY")){ 
                        RSQ = Integer.parseInt(nextString[2]);
                    }
                    if(fields[3].contains("OWNER-DB-KEY")){ 
                        ownerRecRef = Integer.parseInt(nextString[3]);
                    }
                    if(fields[4].contains("OWNER-DB-KEY")){ 
                        ownerRSQ = Integer.parseInt(nextString[4]);
                    }
                    Record rec = new Record(recRef, RSQ);
                    rec.setRecordName(recordName);
                    rec.setOwnerRecRef(ownerRecRef);
                    rec.setOwnerRSQ(ownerRSQ);
                    recordsList.add(rec);
                    if(RSQ == 28){
                        writeSwitch = true;
                    }
                }
            }
            
            Comparator<Record> cmp = Comparator.comparingInt(Record::getOwnerRSQ)
                    .thenComparing(Record::getRSQ);
            recordsList.sort(cmp);
            
            int currentOwnerRSQ = 0;
            int countMembers = 0;
            Record rec = null;
            Record prevRec = null;
            Set<Integer> rsqs = new TreeSet<Integer>();
            for (int i = 0; i < recordsList.size(); i++){
                rec = recordsList.get(i);
                if(rec.getRSQ() == 28){
                    System.out.println();
                }
                if (i == 0) {
                    currentOwnerRSQ = rec.getOwnerRSQ();
                    countMembers = 0;
                    result.append("OWNER : " + rec.getOwnerRecRef() + " : " + currentOwnerRSQ + " " + newL);
                }
                else {
                    if (rec.getOwnerRSQ() != currentOwnerRSQ){
                        currentOwnerRSQ = rec.getOwnerRSQ();
                        result.append("      members: " + countMembers + newL);
//                        if (prevRec.getOwnerRSQ() <= 2287){
//                            rsqs.add(prevRec.getOwnerRSQ());
//                        }
                        countMembers = 0;
                        result.append("OWNER : " + rec.getOwnerRecRef() + " : " + currentOwnerRSQ + " " + newL);
                    }
                }
                result.append("      record : " + rec.getRecRef() + " : " + rec.getRSQ() + " " + newL);
                countMembers++;
                prevRec = rec;
            }
            result.append("      members: " + countMembers + newL);
            
          // change fpa value
          rsqs.add(5922);
          rsqs.add(5926);
          rsqs.add(5934);
          rsqs.add(5965);
          
//            rsqs.add(21164);
//            rsqs.add(21170);
//            rsqs.add(21172);
//            
//            rsqs.add(21212);
//            rsqs.add(21224);
//            rsqs.add(21246);
//            rsqs.add(21272);
//            rsqs.add(21300);
//            rsqs.add(21331);
//            rsqs.add(21342);
            
            sc = new Scanner(file);
            int counter = 0;
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] nextString = str.split(";");
                if (nextString[0].equals("RECORD")){
                    int RSQ = 0;
                    int ownerRSQ = 0;
                    if(fields[2].contains("DB-KEY")){ 
                        RSQ = Integer.parseInt(nextString[2]);
                    }
                    if(fields[4].contains("OWNER-DB-KEY")){ 
                        ownerRSQ = Integer.parseInt(nextString[4]);
                    }
                    if (rsqs.contains(ownerRSQ) /*ownerRSQ <= (44301)*/){ // 43200(45737)  44104(47019)
                        // 44301(47266)  
                        // (44333(47286) doesnt work)
                        // (44455(47358) doesnt work)
                        // (44466(47372) doesnt work)
                        // (44497(47397) doesnt work) (44545(47471) doesnt work)
                        notSortedResult.append(str + newL);
                        counter++;
                    }
                }
                else{
                    notSortedResult.append(str + newL);
                }
            }
            
            System.out.println(counter);
            
            sc.close();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        // write records
        try(FileWriter writer = new FileWriter("D:\\owners_members.txt", false))
        {
            writer.write(result.toString());
             
            writer.flush();
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 

        
        try(FileWriter writer = new FileWriter("D:\\error_represent.txt", false))
        {
            writer.write(notSortedResult.toString());
             
            writer.flush();
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
        
    }
}
