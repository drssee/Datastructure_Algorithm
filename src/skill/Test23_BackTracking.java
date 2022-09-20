package skill;

import java.util.ArrayList;
import java.util.List;

public class Test23_BackTracking {
    public static void main(String[] args) {
        dfsFunc(4,0,new ArrayList<>());
    }

    public static boolean isAvailable(List<Integer> candidate,Integer currentCol){
        Integer currentRow = candidate.size();
        for(int i=0;i<currentRow;i++){
            if(candidate.get(i)==currentCol||(Math.abs(candidate.get(i)-currentCol)==currentRow-i)){
                return false;
            }
        }
        return true;
    }

    public static void dfsFunc(Integer N, Integer currentRow, List<Integer> currentCandidate){
        if(currentRow==N){
            System.out.println(currentCandidate);
            return;
        }
        for(int i=0;i<N;i++){
            if(isAvailable(currentCandidate,i)==true){
                currentCandidate.add(i);
                dfsFunc(N,currentRow+1,currentCandidate);
                currentCandidate.remove(currentCandidate.size()-1);
            }
        }
    }
}
