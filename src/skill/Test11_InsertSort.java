package skill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test11_InsertSort {
    List<Integer> list = new ArrayList<>();
    public List<Integer> sort(){
        for(int i=1;i<list.size();i++){
            for(int p=i;p>0;p--){
                if(list.get(p)<list.get(p-1)){
                    Collections.swap(list,p,p-1);
                }
                else{//앞부터 정렬하기때문에 스왑이 없으면 앞의 숫자들은 무조건 자신보다 작은 숫자들임
                    break;
                }
            }//내부for
        }//외부for
        return list;
    }//sort
}
