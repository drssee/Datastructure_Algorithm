package skill;

import java.util.ArrayList;
import java.util.List;

public class Test14_MergeSort {
    public List<Integer> mergeSplit(List<Integer> list) {
        if(list.size()==1){
            return list;
        }
        int medium = list.size()/2;
        List<Integer> left=new ArrayList<>();
        List<Integer> right=new ArrayList<>();
        left=mergeSplit(list.subList(0, list.size() / 2));
        right=mergeSplit(list.subList(list.size() / 2, list.size()));
        return mergeFunction(left,right);
    }


    public List<Integer> mergeFunction(List<Integer> left, List<Integer> right) {
        ArrayList<Integer> mergedList = new ArrayList<>();
        int lP=0;
        int rP=0;
        //CASE1: left/right 둘다 있을때
        while(lP<left.size()&&rP<right.size()){
            if(left.get(lP)<right.get(rP)){
                mergedList.add(left.get(lP));
                lP++;
            }
            else{
                mergedList.add(right.get(rP));
                rP++;
            }
        }//while 둘다 포인터가 살아있는 경우는 whild에서 처리됨
        //CASE2: righ가 없을때
        while(lP<left.size()){
            mergedList.add(left.get(lP));
            lP++;
        }
        //CASE3: left가 없을때
        while(rP<right.size()){
            mergedList.add(right.get(rP));
            rP++;
        }
        return mergedList;
    }
}
