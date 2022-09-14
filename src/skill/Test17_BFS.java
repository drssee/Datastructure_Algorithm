package skill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test17_BFS {
    public static void main(String[] args) {
        HashMap<String, List<String>> graph = new HashMap<>();
        graph.put("A",new ArrayList<>(Arrays.asList("B","C")));
        graph.put("B",new ArrayList<>(Arrays.asList("A","D")));
        graph.put("C",new ArrayList<>(Arrays.asList("A","G","H","I")));
        graph.put("D",new ArrayList<>(Arrays.asList("B","E","F")));
        graph.put("E",new ArrayList<>(Arrays.asList("D")));
        graph.put("F",new ArrayList<>(Arrays.asList("D")));
        graph.put("G",new ArrayList<>(Arrays.asList("C")));
        graph.put("H",new ArrayList<>(Arrays.asList("C")));
        graph.put("I",new ArrayList<>(Arrays.asList("C","J")));
        graph.put("J",new ArrayList<>(Arrays.asList("I")));
        System.out.println(graph);
        System.out.println(bfsFunc(graph,"A"));
    }
    public static List<String> bfsFunc(HashMap<String, List<String>> graph, String startNode){
        //그래프 이동을 위한 두개의 큐를 만든다
        //하나는 포인터큐 다른 하나는 경로저장큐
        List<String> visitedQue = new ArrayList<>();
        List<String> needVisitQue = new ArrayList<>();

        //처음 경로 startnode를 입력해준다
        if(needVisitQue.size()==0){
            needVisitQue.add(startNode);
        }

        //순회하며 반복문을 실행한다
        while(needVisitQue.size()>0){//더이상 방문할 needvisitque가 없으면 종료
            //visitedque에 방문한 기록이 있는지 확인한다 중복이면 visitedque에 값을 넣지 않고 다음 while문으로
            if(!visitedQue.contains(needVisitQue.get(0))){//que라서 FIFO
                startNode = needVisitQue.remove(0);
                //방문했다!
                //방문리스트큐에 저장하고 방문필요한큐에서 FIFO
                visitedQue.add(startNode);//A

                //방문필요한큐에 다음 노드들 저장함
                needVisitQue.addAll(graph.get(startNode));//B C
            }//if
            else{
                needVisitQue.remove(0);
            }
            System.out.println(needVisitQue);
        }//while
        //반복문을 종료하면 방문이 필요한 큐는 없어지고
        //방문한 기록을 담은 큐만 남는다 그것을 리턴
        return visitedQue;
    }
}
