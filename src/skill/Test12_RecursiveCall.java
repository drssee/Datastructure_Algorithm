package skill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test12_RecursiveCall {
    public int factorial(int n){
        if(n<=1){
            return n;
        }
        return n*factorial(n-1);
    }
    public int arrSum(List<Integer> list){//arr마지막 inx부터 0까지의 합
        int n = list.size()-1;
        if(n==0){
            return list.get(n);
        }
        List<Integer> tmp = list.subList(0,list.size()-1);
        return list.get(n)+arrSum(tmp);
    }

    public int case123(int n){
        //n을 재귀 제어 변수로 사용
        if(n>3){
            return case123(n-1)+case123(n-2)+case123(n-3);
        }
        //재귀 종료 시점 설정
        switch (n){
            case 1:return 1;
            case 2:return 2;
            case 3:return 4;
        }
        //n<=0
        return -1;
    }
}
