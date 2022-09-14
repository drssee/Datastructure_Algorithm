package skill;

public class Test13_DP_DC {
    //시간복잡도 dc>rc==rc2 순으로 빠름

    //재귀호출이용
    public int pibonacci_rc(int n){
        if(n<=1){
            return n;
        }
        return pibonacci_rc(n-1)+ pibonacci_rc(n-2);
    }

    //재귀호출이용2
    public int pibonacci_rc2(int n){
        if(n>1){
            return pibonacci_rc(n-1)+ pibonacci_rc(n-2);
        }
        switch (n){
            case 0 : return 0;
            case 1 : return 1;
        }
        return -1;
    }

    //DC만 사용
    public int pibonacci_dc(int n){
        int[] pibonacci = new int[n+1];
        pibonacci[0]=0;
        pibonacci[1]=1;
        for(int i=2;i<n+1;i++){
            pibonacci[i]=pibonacci[i-1]+pibonacci[i-2];
        }
        return pibonacci[n];
    }
}
