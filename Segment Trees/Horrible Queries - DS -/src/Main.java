import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	static int n,c;
	static long[] tree,lazy ;
	
	static void build(){
		for(int i=0;i<5000000;i++)
			tree[i]=lazy[i]=0;
	}
	static void lazyProp(int i,int li,int ri){
		lazy[2*i]+=lazy[i];
		lazy[2*i+1]+=lazy[i];
		tree[i]+=lazy[i]*(ri-li+1);
		lazy[i]=0;
	}
	static long query(int i,int li,int ri,int a,int b){
		lazyProp(i,li,ri);
		if(li>=a && ri<=b)return tree[i];
		else if(li>b || ri<a)return 0;
		return query(i*2,li,(li+ri)/2,a,b)+query(i*2+1,(li+ri)/2+1,ri,a,b);
	}
	
	static void update(int i,int li,int ri,int a,int b,long value){
		lazyProp(i,li,ri);
		if(li>=a && ri<=b){
			lazy[i]+=value;
			lazyProp(i,li,ri);
			return;
		}
		else if(li>b || ri<a)return;
		update(i*2,li,(li+ri)/2,a,b,value);
		update(i*2+1,(li+ri)/2+1,ri,a,b,value);
		tree[i]= tree[i*2]+tree[i*2+1];
	}
	static public void main(String[] args)throws Exception{
		Scanner sc = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		tree = new long[5000000];
		lazy = new long[5000000];
		int t = sc.nextInt();
		for(int z=1;z<=t;z++){
			n = sc.nextInt(); c = sc.nextInt();
			build();
			for(int i=0;i<c;i++){
				int curr = sc.nextInt();
				if(curr==0){
					int from = sc.nextInt() , to = sc.nextInt();
					long val = sc.nextLong();
					update(1,1,n,from,to,val);
				}
				else{
					int from = sc.nextInt() , to = sc.nextInt();
					out.append(query(1,1,n,from,to)+"\n");
				}
			}
		}
		sc.close();
		System.out.print(out);
	}
}
