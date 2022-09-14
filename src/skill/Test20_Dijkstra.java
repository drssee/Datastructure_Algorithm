package skill;

import java.util.*;

public class Test20_Dijkstra {
    public static void main(String[] args) {
        HashMap<String, List<Edge>> graph = new HashMap<>();//string 이름 vertex의 인접 edge
        graph.put("A",new ArrayList<Edge>(Arrays.asList(new Edge(8,"B"),new Edge(1,"C"),new Edge(2,"D"))));
        graph.put("B",new ArrayList<Edge>());
        graph.put("C",new ArrayList<Edge>(Arrays.asList(new Edge(5,"B"),new Edge(2,"D"))));
        graph.put("D",new ArrayList<Edge>(Arrays.asList(new Edge(3,"E"),new Edge(5,"F"))));
        graph.put("E",new ArrayList<Edge>(Arrays.asList(new Edge(1,"F"))));
        graph.put("F",new ArrayList<>(Arrays.asList(new Edge(5,"A"))));

        DijkstraPath dp = new DijkstraPath();
        System.out.println(dp.dijkstraFunction(graph,"A"));
    }
    static class Edge implements Comparable<Edge>{//구현한 comparable이 위의 priorityque의 정렬기준으로 들어갔음
        public int distance;
        public String vertex;

        public Edge(int distance,String vertex){
            this.distance=distance;
            this.vertex=vertex;
        }
        @Override
        public String toString() {
            return "<vertex: "+this.vertex+", distance: "+this.distance+">";
        }
        @Override
        public int compareTo(Edge o) {
            return this.distance-o.distance;
        }
    }

    static class DijkstraPath{ //edge >> -d -v
        public HashMap<String,Integer> dijkstraFunction(HashMap<String,List<Edge>> graph,String start){
            Edge curEdge = null;
            int curDistance=0;
            String curVertex="";
            String adjacentVertex="";
            int adjacentDistance=0;
            int totalDistance=0;

            //1. 최단거리저장(결과) 저장용 맵 만듬
            HashMap<String,Integer> distances = new HashMap<>();
            for(String key : graph.keySet()){
                //최소값 비교를 위해 최대값으로 초기화
                distances.put(key,Integer.MAX_VALUE);
            }
            //초기셋팅 끝
            distances.put(start,0);//이미 값이 있으면 업데이트됨

            //2. 우선순위큐(다음노드탐색순서용) 만듬
            PriorityQueue<Edge> pq = new PriorityQueue<>();

            //초기셋팅 끝
            pq.add(new Edge(distances.get(start),start));//distance vertex

            //알고리즘 시작
            while(pq.size()>0){//탐색할 노드가 있을동안 돌아라
                curEdge = pq.poll();//탐색할 edge
                curDistance = curEdge.distance;//탐색할노드의 distance
                curVertex = curEdge.vertex;//탐색할노드의 vertex(탐색할 노드와 연결된 노드)

                if(distances.get(curVertex)<curDistance){//비교하려는엣지의 distance가 이미 더 길어서 계산할필요가 없을경우
                    continue;
                }

                List<Edge> edgeList = graph.get(curVertex);
                for(int i = 0;i<edgeList.size();i++){
                    Edge adjacentEdge = edgeList.get(i);//인접 엣지를 가져옴
                    adjacentVertex = adjacentEdge.vertex;
                    adjacentDistance=adjacentEdge.distance;//해당 edge의 가중치
                    totalDistance = curDistance+adjacentDistance;//start로부터 총 가중치
                    if(totalDistance<distances.get(adjacentVertex)){//현재 distances에 저장된 distance가 더 크면 덮어씀
                        distances.put(adjacentVertex,totalDistance);
                        //pq에도 추가
                        pq.add(new Edge(totalDistance,adjacentVertex));
                    }
                }
            }
            return distances;
        }
    }
}