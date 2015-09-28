import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;


 class Main {
	static int n,m,k,sum1,sum2;
	static int[] parents= new int[1000005];
	
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
	
	static public void main(String[] args) throws Exception{
		
		
		Scanner s= new Scanner(System.in);
		boolean flag=false;
		
		while(s.hasNext()){
			sum1=0;
			sum2=0;
			
			if(flag){
				System.out.println();
			}
			flag=true;
			n=s.nextInt();
			for(int i=0;i<n-1;i++){
				int from=s.nextInt(),to=s.nextInt(),cost=s.nextInt();
				sum1+=cost;
			}
			k=s.nextInt();
			int arr[][]=new int[k+5][3];
			for(int i=0;i<k;i++){
				arr[i][0]=s.nextInt();
				arr[i][1]=s.nextInt();
				arr[i][2]=s.nextInt();
			}
			m=s.nextInt();
			int[][]arr2=new int[m+5][3];
			for(int i=0;i<m;i++){
				arr2[i][0]=s.nextInt();
				arr2[i][1]=s.nextInt();
				arr2[i][2]=s.nextInt();
			}
			PriorityQueue<Edge>q = new PriorityQueue<Edge>(m+k+5,comp);

			for(int i=0;i<k;i++){
				Edge e=new Edge();
				e.from=arr[i][0];
				e.to=arr[i][1];
				e.cost=arr[i][2];
				q.add(e);
			}
			for(int i=0;i<m;i++){
				Edge e=new Edge();
				e.from=arr2[i][0];
				e.to=arr2[i][1];
				e.cost=arr2[i][2];
				q.add(e);
			}
			
			init();
			int edges=0;
			while(!q.isEmpty()){
				Edge e=q.remove();
				if(union(e.from,e.to)){
					edges++;
					sum2+=e.cost;
				}
				if(edges==n-1)
					break;
			}
			System.out.println(sum1);
			System.out.println(sum2);
		}
		s.close();
	}
	
}
