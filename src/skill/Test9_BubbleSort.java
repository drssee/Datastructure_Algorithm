package skill;

import java.util.ArrayList;
import java.util.Collections;

public class Test9_BubbleSort {
    public ArrayList<Integer> sort(ArrayList<Integer> list){
        //length-1-0
        //length-1-1
        //length-1-2
        //....
        //length-1-n==1
        for(int i=0;i<list.size()-1;i++){
            boolean swap = false;
            for(int j=0;j<list.size()-1-i;j++){
                if(list.get(j)>list.get(j+1)){
                    Collections.swap(list,j,j+1);
                    swap=true;
                }
            }//내부for
            if(!swap){
                break;
            }
        }//외부for
        return list;
    }
}
