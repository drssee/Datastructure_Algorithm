package skill;

import java.util.ArrayList;
import java.util.List;

public class BackTracking_NQueen_test {
    public static void main(String[] args) {
        nQueen(4,new ArrayList<>(),0);
    }
    public static void nQueen(int N, List<Integer> queen,int row_pointer){
        if(row_pointer==N){
            System.out.println(queen);
            return;
        }
        for(int col_pointer=0;col_pointer<N;col_pointer++){
            if(isValid(N,queen,row_pointer,col_pointer)){
                queen.add(col_pointer);
                row_pointer++;
                nQueen(N,queen,row_pointer);

                row_pointer--;
                queen.remove(queen.size()-1);
            }
        }
    }

    public static boolean isValid(int N,List<Integer> queen,int row_pointer,int col_pointer){
        for(int i=0;i<queen.size();i++){ //(i)는 퀸의 인덱스 get(i)는 퀸의 값
            if(queen.get(i)==col_pointer||((Math.abs(queen.get(i)-col_pointer))==1&&(row_pointer-i)==1)){
                return false;
            }
        }
        return true;
    }
}
