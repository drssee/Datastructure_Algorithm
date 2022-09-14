package skill;

import java.util.*;

public class Test21_MST_Kruskal {
    //기본셋팅 최소거리 연결할 그래프(시각)
    //그래프의 엣지(노드+간선)을 나타낼 클래스(시작노드,도착노드 ,간선)
    //엣지 클래스를 이용해서 그래프를 코드로 저장해둔 리스트
    //사이클을 만들지 말라는 조건을 위해 이용할 union-find 알고리즘을 위한
    //노드들의 부모를 저장한 맵1
    //노드들의 랭크를 저장한 맵2

    //간선을 기준으로 정렬하고
    //각 노드들을 전부 개별 크기1 union , rank0으로 초기화 해주고

    //간선이 작은순서부터 사이클이 없게 연결해 나간다

    static List<String> vertices = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G"));
    static List<Edge> edges = new ArrayList<>();

    static HashMap<String,String> parent = new HashMap<>();
    static HashMap<String,Integer> rank = new HashMap<>();
    public static void main(String[] args) {
        edges.add(new Edge(7,"A","B"));
        edges.add(new Edge(5,"A","D"));
        edges.add(new Edge(7,"B","A"));
        edges.add(new Edge(8,"B","C"));
        edges.add(new Edge(9,"B","D"));
        edges.add(new Edge(7,"B","E"));
        edges.add(new Edge(8,"C","B"));
        edges.add(new Edge(5,"C","E"));
        edges.add(new Edge(5,"D","A"));
        edges.add(new Edge(9,"D","B"));
        edges.add(new Edge(7,"D","E"));
        edges.add(new Edge(6,"D","F"));
        edges.add(new Edge(7,"E","B"));
        edges.add(new Edge(7,"E","D"));
        edges.add(new Edge(8,"E","F"));
        edges.add(new Edge(9,"E","G"));
        edges.add(new Edge(6,"F","D"));
        edges.add(new Edge(8,"F","E"));
        edges.add(new Edge(11,"F","G"));
        edges.add(new Edge(9,"G","E"));
        edges.add(new Edge(11,"G","F"));
        System.out.println(kruskalFunc(vertices,edges));
    }

    static class Edge implements Comparable<Edge>{
        public int weight;
        public String nodeV;
        public String nodeU;

        public Edge(int weight,String nodeV,String nodeU){
            this.weight=weight;
            this.nodeV=nodeV;
            this.nodeU=nodeU;
        }

        @Override
        public String toString() {
            return "("+weight+", "+nodeV+", "+nodeU+")";
        }
        @Override
        public int compareTo(Edge o) {
            return this.weight-o.weight;
        }
    }

    public static void makeSet(String node){//init
        parent.put(node,node);
        rank.put(node,0);
    }

    //union-find 알고리즘
    public static String find(String node){ // find + path compression = 루트노드를 찾으면서 루트를 공통 부모로 만드는작업
        if(parent.get(node)!=node){//parent의 value가 자신이라는것은 자신이 루트노드라는것
            //즉 여기는 root 노드가 아닌애들만 들어올수있다
            parent.put(node,find(parent.get(node)));
        }
        return parent.get(node);
        //재귀 후엔 path compression 기법이 적용되서 루트 노드를 공통의 부모로 가지게됨
    }
    public static void union(String nodeV,String nodeU){//사이클이 안만들어진다고 위에서 거르고 union
        //루트 비교를 위해 루트를 구함
        String root1=find(nodeV);
        String root2=find(nodeU);

        //union-by-rank 기법
        //여기서 rank가 생김
        if(rank.get(root1)>rank.get(root2)){
            parent.put(root2,root1);
        }
        else{
            parent.put(root1,root2);
            if(rank.get(root1)==rank.get(root2)){
                rank.put(root2,rank.get(root2)+1);
            }
        }
    }

    public static List<Edge> kruskalFunc(List<String> vertices,List<Edge> edges){
        List<Edge> mst= new ArrayList<>();
        Edge currentEdge;

        //1.초기화
        for(int i=0;i<vertices.size();i++){
            makeSet(vertices.get(i));
        }

        //2.간선 weight 기반 sort
        Collections.sort(edges);

        //3.간선이 짧은 엣지부터 순회
        for(int i=0;i<edges.size();i++){
            currentEdge=edges.get(i);

            //***간선으로 연결된 두 노드의 루트 노드가 서로 다르면 *** 사이클이 생기지 않으니 합친다
            if(find(currentEdge.nodeV)!=find(currentEdge.nodeU)){
                union(currentEdge.nodeV, currentEdge.nodeU);

                //결과에 넣는다
                mst.add(currentEdge);
            }
        }
        return mst;
    }
}
