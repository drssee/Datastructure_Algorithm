package skill;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Test8_HeapTree {
     public ArrayList<Integer> heapArray = null;
     public Test8_HeapTree(Integer data){
         heapArray=new ArrayList<>();
         //계산을 편하게 하기 위해 첫번째 인덱스 0 은 비워둠
         heapArray.add(null);
         heapArray.add(data);
     }

     public boolean moveUp(Integer inserted_idx){
         if(inserted_idx<=1){
             return false;
         }
         Integer parent_idx = inserted_idx/2;
         if(heapArray.get(inserted_idx)>heapArray.get(parent_idx)){
             return true;
         }
         return false;
     }
     public boolean moveDown(Integer poped_idx){
         Integer left_child_poped_idx,right_child_popped_idx;
         left_child_poped_idx=poped_idx*2;
         right_child_popped_idx=poped_idx*2+1;
         //CASE1:왼쪽 자식 노드도 없을때 , 자식0
         if(left_child_poped_idx>=heapArray.size()){
             return false;
         }
         //CASE2:오른쪽 자식 노드만 없을때
         else if(right_child_popped_idx>=heapArray.size()){
             if(heapArray.get(poped_idx)<heapArray.get(left_child_poped_idx)){
                 return true;
             }
             else{
                 return false;
             }
         }
         //CASE3:자식노드가 모두다 있을때
         else{
             if(heapArray.get(left_child_poped_idx)>heapArray.get(right_child_popped_idx)){
                 if(heapArray.get(poped_idx)<heapArray.get(left_child_poped_idx)){
                     return true;
                 }
                 else{
                     return false;
                 }
             }
             else{
                 if(heapArray.get(poped_idx)<heapArray.get(right_child_popped_idx)){
                     return true;
                 }
                 else{
                     return false;
                 }
             }
         }
     }

     public boolean insert(Integer data) {
         Integer inserted_idx,parent_idx;
         if(heapArray==null){//생성자로 같은코드 있지만 데이터 다빼서 없을수도 있으니 대비
             heapArray=new ArrayList<Integer>();

             heapArray.add(null);
             heapArray.add(data);
             return true;
         }
         heapArray.add(data);
         inserted_idx=heapArray.size()-1;
         while(moveUp(inserted_idx)){
             parent_idx=inserted_idx/2;
             //값만 swap , index swap은 아님
             Collections.swap(this.heapArray,inserted_idx,parent_idx);
             inserted_idx=parent_idx;
         }
         return true;
     }

     public Integer peek(){
         if(heapArray==null){
             return null;
         }
         else {
             return heapArray.get(1);
         }
     }
    public Integer pop(){
         Integer returned_data,poped_idx,left_child_poped_idx,right_child_poped_idx;
        if(heapArray==null){
            return null;
        }
        else {
            returned_data=heapArray.get(1);
            this.heapArray.set(1,heapArray.get(heapArray.size()-1));
            heapArray.remove(heapArray.size()-1);
            poped_idx=1;
            while(moveDown(poped_idx)){
                left_child_poped_idx=poped_idx*2;
                right_child_poped_idx=poped_idx*2+1;
                //CASE1은 movedown에서 걸러짐

                //CASE2:자식노드1개(왼쪽만존재가능)
                if(right_child_poped_idx>=heapArray.size()){
                    Collections.swap(heapArray,poped_idx,left_child_poped_idx);
                    poped_idx=left_child_poped_idx;
                }
                //CASE3:자식노드2개
                else{
                    if(left_child_poped_idx>right_child_poped_idx){
                        Collections.swap(heapArray,poped_idx,left_child_poped_idx);
                        poped_idx=left_child_poped_idx;
                    }
                    else{
                        Collections.swap(heapArray,poped_idx,right_child_poped_idx);
                        poped_idx=right_child_poped_idx;
                    }
                }
            }
        }
        return returned_data;
    }
}
