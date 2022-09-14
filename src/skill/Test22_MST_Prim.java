package skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Test22_MST_Prim {
    static List<Edge> mst = new ArrayList<>();
    static List<Edge> edges = new ArrayList<>(); //전체 엣지리스트
    static List<Edge> currentEdgeList;//현재 엣지
    static List<String> connectedNodes;//연결된 엣지리스트
    static List<Edge> candidateEdgeList;//간선리스트
    static HashMap<String,List<Edge>> adjacentEdges;//해당key노드에 대한 연결된 모든 간선(엣지)들
    static Edge poppedEdge;
    static List<Edge> adjacentEdgeNodes;
    static Edge adjacentEdgeNode;
    static PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
    static HashMap<String, List<Edge>> graph = new HashMap<>();
    Edge currentEdge;

    public static void main(String[] args) {
        edges.add(new Edge(7,"A","B"));
        edges.add(new Edge(5,"A","D"));
        edges.add(new Edge(8,"B","C"));
        edges.add(new Edge(9,"B","D"));
        edges.add(new Edge(7,"B","E"));
        edges.add(new Edge(5,"C","E"));
        edges.add(new Edge(7,"D","E"));
        edges.add(new Edge(6,"D","F"));
        edges.add(new Edge(8,"E","F"));
        edges.add(new Edge(9,"E","G"));
        edges.add(new Edge(11,"F","G"));

        System.out.println(primFunc("A",edges));
    }
    static class Edge implements Comparable<Edge> {
        public int weight;
        public String node1;
        public  String node2;

        public Edge(int weight, String node1 , String node2){
            this.weight=weight;
            this.node1=node1;
            this.node2=node2;
        }

        @Override
        public String toString() {
            return "("+weight+", "+node1+", "+node2+")";
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight-o.weight;
        }
    }


    public static List<Edge> primFunc(String startNode,List<Edge> edgs){
        mst = new ArrayList<>();//최종결과
        connectedNodes = new ArrayList<String>();//연결된 노드들 리스트(중간결과)
        adjacentEdges=new HashMap<>();//인접 엣지 리스트

        //init
        for(int i=0;i<edges.size();i++){//edges 는 문제그래프
            Edge currentEdge = edges.get(i);
            if(!adjacentEdges.containsKey(currentEdge.node1)){
                adjacentEdges.put(currentEdge.node1,new ArrayList<Edge>());
            }
            if(!adjacentEdges.containsKey(currentEdge.node2)){
                adjacentEdges.put(currentEdge.node2,new ArrayList<Edge>());
            }
        }

        for(int i=0;i<edges.size();i++){
            Edge currentEdge = edgs.get(i);
            currentEdgeList = adjacentEdges.get(currentEdge.node1);
            currentEdgeList.add(new Edge(currentEdge.weight,currentEdge.node1, currentEdge.node2));
            currentEdgeList = adjacentEdges.get(currentEdge.node2);
            currentEdgeList.add(new Edge(currentEdge.weight,currentEdge.node2, currentEdge.node1));
        }

        connectedNodes.add(startNode);
        candidateEdgeList = adjacentEdges.getOrDefault(startNode,new ArrayList<Edge>());//get이 없으면 빈 리스트라도 초기화해라
        priorityQueue = new PriorityQueue<Edge>();
        for(int i=0;i<candidateEdgeList.size();i++){
            priorityQueue.add(candidateEdgeList.get(i));
        }
        //init 끝

        while(priorityQueue.size()>0){
            poppedEdge = priorityQueue.poll();
            if(!connectedNodes.contains(poppedEdge.node2)){
                connectedNodes.add(poppedEdge.node2);
                mst.add(new Edge(poppedEdge.weight, poppedEdge.node1, poppedEdge.node2));

                adjacentEdgeNodes = adjacentEdges.getOrDefault(poppedEdge.node2,new ArrayList<>());
                for(int i = 0; i< adjacentEdgeNodes.size(); i++){
                    adjacentEdgeNode = adjacentEdgeNodes.get(i);
                    if(!connectedNodes.contains(adjacentEdgeNode.node2)){
                        priorityQueue.add(adjacentEdgeNode);
                    }
                }
            }
        }
        return mst;
    }
}
