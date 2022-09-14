package skill;

import java.util.ArrayList;
import java.util.List;

public class Test16_BinarySearch {
    public boolean binary(List<Integer> list,int search){
        //1 찾을 경우의수 1개 남았는데 search일경우
        //2 1개 이상일때 포인터를 binary하면서 순회하다 포인터에 걸리는경우
        if(list.size()<=1){//1
            if(list.size()==0){
                //피벗의 오른쪽에 아무것도 없어서 size 0인 리스트가 온경우 // 왼쪽은 size/2 < 1 인 경우는 이곳을 벗어날수없음
                return false;
            }
            if(search==list.get(0)){
                return true;
            }
            return false;
        }
        int pointer = list.size()/2;
        //피벗과 검색할수 비교해서 맞을때 더작을때 더클때
        if(search==list.get(pointer)){//2
            return true;
        }
        else if(search<list.get(pointer)){
            //왼쪽으로 보낸다
            List<Integer> left = new ArrayList<>();
            left=list.subList(0,pointer);
            return binary(left,search);
        }
        else{
            List<Integer> right = new ArrayList<>();
            right=list.subList(pointer+1,list.size());
            return binary(right,search);
        }
    }
}
