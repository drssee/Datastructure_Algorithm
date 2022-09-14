package skill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test19_Greedy {
    public static void main(String[] args) {
        //coin
        List<Integer> coin = new ArrayList<>();
        coin.add(500);
        coin.add(100);
        coin.add(50);
        coin.add(1);
        System.out.println(greedy_coin(4720,coin));
        System.out.println("------------------------------------");
        //

        //fractional knapsack // 무게대비 가치가 높은것
        //가치를 무게로 나눈다 == 가치/무게
        //{무게,가치}
        //수용량(10)안에서 가장 높은 가치를 넣어라 가방들은 무게와 가치가 정해져있고 쪼갤수 있다
        Integer[][] objectList = {{10,10},{15,12},{20,10},{25,8},{30,5}};
        greedy_bag(objectList,30);
        System.out.println("------------------------------------");

        //참고용 comparator lamda로 구현
        Bag[] bagArr = {new Bag(300),new Bag(500),new Bag(100)};
        Arrays.sort(bagArr,(a,b)->(a.weight-b.weight)*-1); //lamda function a->b
        for(Bag b : bagArr){
            System.out.print(b+" ");
        }
    }
    //동전
    public static int greedy_coin(int total, List<Integer> coin){
        int count=0;
        for(int i=0;i<coin.size();i++){
            count+=total/coin.get(i);
            total%=coin.get(i);
        }
        return count;
    }
    //

    //배낭
    public static void greedy_bag(Integer[][] objectList,double capacity){
        double totalValue=0.0;
        double fraction=0.0;//무게제한때문에 못들어가면 조각내서 넣을때 얼마나 들어갔는지

        Arrays.sort(objectList,(a,b)->((a[1]/a[0])-(b[1]/b[0]))*-1);//무게 당 가치 즉 가치/무게 높은순으로 내림차순으로 정렬

        for(int i=0;i<objectList.length;i++){
//            if(capacity<=0){
//                break;
//            }
            if(capacity-(double)objectList[i][0]>0){//쪼갤필요가 없을때
                capacity-=(double)objectList[i][0];
                totalValue+=(double)objectList[i][1];
                System.out.println("무게:"+objectList[i][0]+", 가치:"+objectList[i][1]);
            }
            else{//쪼갤필요가 있을때  // 무게10 에 무게15짜리 넣는다고 생각해서 봐라
//                double margin = (double)objectList[i][0]-capacity;
//
//                fraction = (double)objectList[i][1]-(margin/((double)objectList[i][0])*(double)objectList[i][1]);
//
//                capacity=0;
//                totalValue+=fraction;

                fraction = capacity/(double)objectList[i][0];//fraction == 얼마나 들어갔냐? 10에서15를 나눔
                totalValue+=(double)objectList[i][1]*fraction;
                System.out.println("무게:"+objectList[i][0]+", 가치:"+objectList[i][1]+", 비율:"+fraction);
                break;
            }
        }
        System.out.println("총 담을 수 있는 가치:"+totalValue);
    }
    //


    //참고용
    static class Bag{
        Integer weight;
        public Bag(Integer weight){
            this.weight=weight;
        }

        @Override
        public String toString() {
            return "Bag{" +
                    "weight=" + weight +
                    '}';
        }
    }
    class BagComparator implements Comparator<Bag> {
        @Override
        public int compare(Bag o1,Bag o2) {
            if(o1!=null||o2!=null){
                return -1;
            }
            return o1.weight.compareTo(o2.weight);
        }
    }
    //참고용끝
}
