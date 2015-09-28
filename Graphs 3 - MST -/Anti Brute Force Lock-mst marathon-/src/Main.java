import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;


class Main {
	static int t,n;
	static String[] keys = new String[505];
	static int[][] moves=new int[505][505];
	static int[] parents=new int[505];
	static class IntCompare implements Comparator<Edge> {
		 
		public int compare(Edge o1, Edge o2) {
 
			if(o1.cost>o2.cost)
			{
				return 1;
			}else if(o1.cost<o2.cost)
			{
				return -1;
			}
			return 0;
		}
 
	} 
	static Comparator<Edge>comp = new IntCompare();
 
	
	static class Edge{
		int from,to,cost;
	}
	static void init (){
		for(int i=0;i<=n;i++)
			parents[i]=i;
	}
	
	static int find(int i){
		if(i==parents[i])return i;
		return parents[i]=find(parents[i]);
	}
	
	static boolean union(int u,int v){
		int r_u=find(u);
		int r_v=find(v);
		if(r_v==r_u)return false;
		parents[r_v]=r_u;
		return true;
	}
	
	static int diff(int n1,int n2){
		return Math.min(Math.abs(n1-n2), 10-Math.abs(n1-n2));
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t=Integer.parseInt(br.readLine());
		for(int z=0;z<t;z++){
			keys=br.readLine().split(" ");
			n=Integer.parseInt(keys[0]);
			for(int i=1;i<=n;i++){
				for(int j=1;j<=n;j++){
					if(i==j)
						moves[i][j]=0;
					else{
						int sum=0;
						for(int k=0;k<4;k++){
							int n1=(int)(keys[i].charAt(k))-(int)'0';
							int n2=(int)(keys[j].charAt(k))-(int)'0';
							sum+=diff(n1,n2);
						}
						moves[i][j]=sum;
					}
				}
			}
			PriorityQueue<Edge> q=new PriorityQueue<Edge>(n*n+5,comp);
			
			for(int i=1;i<=n;i++){
				for(int j=1;j<=n;j++){
					Edge e=new Edge();
					e.from=i;
					e.to=j;
					e.cost=moves[i][j];
					q.add(e);
					
				}
			}
			
			int edges=0,total=0;
			init();
			while(!q.isEmpty()){
				Edge e=q.remove();
				if(union(e.from,e.to)){
					edges++;
					total+=e.cost;
				}
				if(edges==n-1)
					break;
			}
			int min=1000000000;
			for(int i=1;i<=n;i++){
				int sum=0;
				for(int k=0;k<4;k++){
					int n1=(int)keys[i].charAt(k)-(int)'0';
					sum+=diff(n1,0);
				}
				if(sum<min)
					min=sum;
			}
			total+=min;
			System.out.println(total);
		}
	}
}
