package skill;

import java.util.ArrayList;
import java.util.List;

public class Test15_QuickSort {
    //0번을 pivot으로 설정
    //pivot보다 작은값은 left 반대는 right
    //size가 1개가 될때까지 재귀후
    //리턴으로 모으면서 합친다

    public List<Integer> quick1(List<Integer> list){
        //재귀 종료조건 설정해줘야함
        if(list.size()<=1){
            return list;
        }
        int pivot;
        pivot=list.get(0);
        List<Integer> left=new ArrayList<>();
        List<Integer> right=new ArrayList<>();
        for(int i=1;i<list.size();i++){
            if(list.get(i)<pivot){
                left.add(list.get(i));
            }
            else{
                right.add(list.get(i));
            }
        }//for
        //left(정렬안된) pivot right(정렬안된)

        List<Integer> sortedList = new ArrayList<>();
        //이럴필요없이 addAll은 사이즈0을 더해도 예외 발생 안함
        if(left.size()>0&&right.size()>0){
            left=quick1(left);
            right=quick1(right);
            sortedList.addAll(left);
            sortedList.add(pivot);
            sortedList.addAll(right);
        }
        else if(left.size()>0){
            left=quick1(left);
            sortedList.addAll(left);
            sortedList.add(pivot);
        }
        else{
            right=quick1(right);
            sortedList.add(pivot);
            sortedList.addAll(right);
        }
        return sortedList;
        //어차피 addAll에 빈(널이아닌) 어레이리스트를 넣으면
        //아무것도 안들어가서 경우의수를 생각할필요없이
        //하나의 경우의 수로 전부다 해결 가능함
    }
}
