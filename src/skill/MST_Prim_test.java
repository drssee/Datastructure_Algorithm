package skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class MST_Prim_test {
    //** 1.방문 기록 저장
    //** 2.방문할 우선순위 제어
    //** 3.방문할 엣지의 인근 엣지 정리
    //** 4.인근 엣지 정리할때 규칙을 줌 (weight , Key , adjNode)


    static List<Prim.Edge> graph = new ArrayList<>();
    public static void main(String[] args) {
        graph.add(new Prim.Edge(7,"A","B"));
        graph.add(new Prim.Edge(5,"A","D"));
        graph.add(new Prim.Edge(8,"B","C"));
        graph.add(new Prim.Edge(9,"B","D"));
        graph.add(new Prim.Edge(7,"B","E"));
        graph.add(new Prim.Edge(5,"C","E"));
        graph.add(new Prim.Edge(7,"D","E"));
        graph.add(new Prim.Edge(6,"D","F"));
        graph.add(new Prim.Edge(8,"E","F"));
        graph.add(new Prim.Edge(9,"E","G"));
        graph.add(new Prim.Edge(11,"F","G"));

        Prim prim = new Prim();
        System.out.println(prim.primFunc("A",graph));
    }
}

class Prim {
    static class Edge implements Comparable<Edge>{
        int weight;
        String node1;
        String node2;

        public Edge(int weigth,String node1,String node2){
            this.weight=weigth;
            this.node1=node1;
            this.node2=node2;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight-o.weight;
        }

        @Override
        public String toString() {
            return "("+weight+", "+node1+", "+node2+")";
        }
    }//edge

    //core 변수들
    HashMap<String,List<Edge>> adjacentEdgeMap = new HashMap<>();
    PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
    List<String> connectedNodes = new ArrayList<>();
    List<Edge> mst = new ArrayList<>();

    //side 변수들
    Edge currentEdge;
    Edge popedEdge;
    List<Edge> popedEdgeList;

    public List<Edge> primFunc(String start,List<Edge> graph){
        //임의의 시작 start를 connectedNodes에 넣는다
        connectedNodes.add(start);

        //node(key) -> 연결된edges(values) 인 adjacentEdgeMap을 graph에서 값을 가져와 초기화한다
        //node(key)부터 가져옴
        for(int i=0;i<graph.size();i++){
            currentEdge = graph.get(i);
            if(!adjacentEdgeMap.containsKey(currentEdge.node1)) {//key로 넣으려고 가져온 값이 인접엣지맵에 key로 등록되어 있지 않다면
                adjacentEdgeMap.put(currentEdge.node1,new ArrayList<>());
            }
            if(!adjacentEdgeMap.containsKey(currentEdge.node2)){
                adjacentEdgeMap.put(currentEdge.node2, new ArrayList<>());
            }
        }

        //연결된edges(values) 가져옴
        for(int i=0;i<graph.size();i++){
            popedEdge = graph.get(i);//graph는 전체 엣지 목록 리스트임

            popedEdgeList = adjacentEdgeMap.getOrDefault(popedEdge.node1,new ArrayList<>());//value list<edge>를 이용하기위해 변수설정
            popedEdgeList.add(new Edge(popedEdge.weight, popedEdge.node1, popedEdge.node2));

            popedEdgeList = adjacentEdgeMap.getOrDefault(popedEdge.node2,new ArrayList<>());
            popedEdgeList.add(new Edge(popedEdge.weight,popedEdge.node2, popedEdge.node1));
        }

        //start노드의 인접엣지들을 넣어줌
        for(int i=0;i<adjacentEdgeMap.getOrDefault(start,new ArrayList<>()).size();i++){
            priorityQueue.add(adjacentEdgeMap.getOrDefault(start,new ArrayList<>()).get(i));
        }

        while(priorityQueue.size()>0){
            popedEdge = priorityQueue.poll();
            if(!connectedNodes.contains(popedEdge.node2)){
                mst.add(popedEdge);
//                priorityQueue.clear();
                connectedNodes.add(popedEdge.node2);
                popedEdgeList = adjacentEdgeMap.getOrDefault(popedEdge.node2,new ArrayList<>());
                for(int i=0;i<popedEdgeList.size();i++){
                    if(!connectedNodes.contains(popedEdgeList.get(i).node2)){
                        priorityQueue.add(popedEdgeList.get(i));
                    }
                }//for
            }
        }//while
        return mst;
    }
}
