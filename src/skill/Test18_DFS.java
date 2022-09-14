package skill;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test18_DFS {
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
        System.out.println(dfs(graph,"A "));
    }
    public static List<String> dfs(HashMap<String,List<String>> graph,String startNode){
        //visited,needvisit 만든다
        List<String> visited = new ArrayList<>();
        List<String> needvisit = new ArrayList<>();
        //자식노드우선으로 탐색해야하기 때문에 needvisit은 큐가 아닌 스택의 정책을 따른다

        //init
        needvisit.add(startNode);

        //반복문시작
        while(needvisit.size()>0){
            //1.방문한이력확인
            //2.없으면 visit로 옮기고
            //3.옮긴 노드의 value값을 그래프에서 가져와 needvisit에 넣는다

            String poped = needvisit.remove(needvisit.size()-1);
            if(!visited.contains(poped)){//방문이력이 없으면
                visited.add(poped);
                needvisit.addAll(graph.get(poped));
            }
        }
        //여기에 도달하면
        //모든 탐색이 끝난상태니 리턴한다
        return visited;
    }
}
