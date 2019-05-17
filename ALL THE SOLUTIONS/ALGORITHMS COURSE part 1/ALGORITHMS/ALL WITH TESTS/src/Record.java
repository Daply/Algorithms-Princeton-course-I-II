
public class Record {
      private String recordName = "";
      private int recRef = 0;
      private int RSQ = 0;
      private String ownerRecordName = "";
      private int ownerRecRef = 0;
      private int ownerRSQ = 0;
      
      public Record(int recRef, int RSQ){
          this.recRef = recRef;
          this.RSQ = RSQ;
      }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public int getRecRef() {
        return recRef;
    }

    public void setRecRef(int recRef) {
        this.recRef = recRef;
    }

    public int getRSQ() {
        return RSQ;
    }

    public void setRSQ(int rSQ) {
        RSQ = rSQ;
    }

    public String getOwnerRecordName() {
        return ownerRecordName;
    }

    public void setOwnerRecordName(String ownerRecordName) {
        this.ownerRecordName = ownerRecordName;
    }

    public int getOwnerRecRef() {
        return ownerRecRef;
    }

    public void setOwnerRecRef(int ownerRecRef) {
        this.ownerRecRef = ownerRecRef;
    }

    public int getOwnerRSQ() {
        return ownerRSQ;
    }

    public void setOwnerRSQ(int ownerRSQ) {
        this.ownerRSQ = ownerRSQ;
    }
      
      
      
}
