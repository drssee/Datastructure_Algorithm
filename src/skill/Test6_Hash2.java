package skill;

public class Test6_Hash2 {
    public Slot[] hashTable;
    public Test6_Hash2(Integer size){
        this.hashTable = new Slot[size];
    }
    public class Slot{
        String key;
        String value;
        Slot(String key ,String value) {
            this.key=key;
            this.value=value;
        }
    }
    public int hashFunc(String key){
        int k=0;
        for(int i=0;i<key.length();i++){
            k+=key.charAt(i);
        }
        return k%this.hashTable.length;
//        return (int)(key.charAt(0))%this.hashTable.length;
    }

    public boolean saveData(String key,String value){
        Integer address=this.hashFunc(key);
        if(this.hashTable[address]!=null) {
            if (hashTable[address].key == key) {
                hashTable[address].value = value;
                return true;
            } else {
                address++;
                while (hashTable[address] != null || address == hashTable.length) {
                    if(hashTable[address].key==key){
                        hashTable[address].value=value;
                        return true;
                    }
                    address++;
                }
                if (address == hashTable.length) {
                    System.out.println("테이블 꽉참");
                    return false;
                }
                hashTable[address] = new Slot(key, value);
                return true;
            }
        }
        this.hashTable[address] = new Slot(key, value);
        return true;
    }

    public String getData(String key){
        int address=hashFunc(key);
        if(hashTable[address]!=null){
            while(hashTable[address]!=null||address==hashTable.length){
                if(hashTable[address].key==key){
                    return hashTable[address].value;
                }
                address++;
            }
            return null;
        }
        else{
            return null;
        }

    }
}
