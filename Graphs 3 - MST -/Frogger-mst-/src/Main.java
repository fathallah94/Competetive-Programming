import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


class Main {
	static int n;
	static int[] parents=new int[205];
	static int[][] indices = new int[205][2];
	static double[][] diff=new double[205][205];
	static double[][] adj=new double[205][205];
	
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
		int from,to;
		double cost;
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
	
	static void bfs(){
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] v=new boolean[205];
		int start=0,end=1;
		q.add(start);
		v[start]=true;
		while(!q.isEmpty()){
			int x=q.remove();
			if(x==end){
				break;
			}
			for(int i=0;i<n;i++){
				if(adj[x][i]!=-1 && i!=x && !v[i]){
					q.add(i);
					v[i]=true;
					parents[i]=x;
				}
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		int cases=0;
		while(n!=0){
			cases++;
			
			for(int i=0;i<n;i++){
				String in[] =br.readLine().split(" ");
				indices[i][0]=Integer.parseInt(in[0]);
				indices[i][1]=Integer.parseInt(in[1]);
			}
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					diff[i][j]=Math.sqrt((indices[i][0]-indices[j][0])*(indices[i][0]-indices[j][0])+(indices[i][1]-indices[j][1])*(indices[i][1]-indices[j][1]));
				}
			}
			PriorityQueue<Edge> q= new PriorityQueue<Edge>(n*n+5,comp);
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					Edge e=new Edge();
					e.from=i;
					e.to=j;
					e.cost=diff[i][j];
					q.add(e);
				}
			}
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++)
					adj[i][j]=-1;
			}
			int edges=0;
			init();
			while(!q.isEmpty()){
				Edge e=q.remove();
				if(union(e.from,e.to)){
					adj[e.from][e.to]=e.cost;
					adj[e.to][e.from]=e.cost;
					edges++;
				}
				if(edges==n-1)
					break;
			}
			
			
			Arrays.fill(parents, -1);
			bfs();
			int x=1;
			double max=-1;
			while(x!=0){
				if(adj[x][parents[x]]>max)
					max=adj[x][parents[x]];
				x=parents[x];
			}
			System.out.println("Scenario #"+cases);
			System.out.printf("Frog Distance = %.3f\n",max);
			System.out.println();
			br.readLine();
			n=Integer.parseInt(br.readLine());
		}
		br.close();
	}
}
