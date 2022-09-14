package skill;

public class Test6_Hash {
    public Slot[] hashTable;
    public Test6_Hash(Integer size){
        this.hashTable = new Slot[size];
    }
    public class Slot{
        String key;
        String value;
        Slot next;
        Slot(String key ,String value) {
            this.key=key;
            this.value=value;
        }
    }
    public int hashFunc(String key){
        return (int)(key.charAt(0))%this.hashTable.length;
    }

    public boolean saveData(String key,String value){
        Integer address=this.hashFunc(key);
        if(this.hashTable[address]!=null){
            Slot findSlot=this.hashTable[address];
            Slot prevSlot=this.hashTable[address];
            while(findSlot!=null){
                if(findSlot.key==key){
                    findSlot.value=value;
                    return true;
                }
                else{
                    prevSlot=findSlot;
                    findSlot=findSlot.next;
                }
            }
            prevSlot.next=new Slot(key,value);
        }
        else{
            this.hashTable[address]=new Slot(key,value);
        }
        return true;
    }

    public String getData(String key){
        int address=hashFunc(key);
        if(hashTable[address]!=null){
            Slot findSlot = hashTable[address];
            while(findSlot!=null){
                if(findSlot.key==key){
                    return findSlot.value;
                }
                else{
                    findSlot=findSlot.next;
                }
            }
            return null;
        }
        else{
            return null;
        }
    }
}
