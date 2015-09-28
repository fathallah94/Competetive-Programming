import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Main {
	static int n,m,k,w;
	static String[][] levels=new String[1005][11];
	static int[][] costs=new int[1005][1005];
	static int[] parents=new int[1005];
	static boolean[] visited=new boolean[1005];
	static boolean[][] v=new boolean[1005][1005];

	
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
		for(int i=0;i<=k;i++)
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
	
	static int diff(String[] s1,String[] s2){
		int counter=0;
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				if(s1[i].charAt(j)!=s2[i].charAt(j))
					counter++;
			}
		}
		return counter*w;
	}
	
	static void dfs(int i){
		visited[i]=true;
		for(int j=1;j<=k;j++){
			if(v[i][j] && !visited[j]){
				System.out.println(j+" "+i);
				dfs(j);
			}
		}
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String[] in=br.readLine().split(" ");
		n=Integer.parseInt(in[0]);
		m=Integer.parseInt(in[1]);
		k=Integer.parseInt(in[2]);
		w=Integer.parseInt(in[3]);
		for(int i=1;i<=k;i++){
			for(int j=0;j<n;j++){
				levels[i][j]=br.readLine();
			}
		}
		
		for(int i=1;i<=k;i++){
			costs[0][i]=n*m;
			costs[i][0]=n*m;
		}
		costs[0][0]=-1;
		for(int i=1;i<=k;i++){
			for(int j=1;j<=k;j++){
				int distance =diff(levels[i],levels[j]);
				if(distance<n*m)
					costs[i][j]=distance;
				else
					costs[i][j]=-1;
				if(i==j)
					costs[i][j]=-1;
			}
		}
		
		
		
		PriorityQueue<Edge> q=new PriorityQueue<Edge>(1000005,comp);
		for(int i=0;i<=k;i++){
			for(int j=0;j<=k;j++){
				if(costs[i][j]!=-1){
					Edge e=new Edge();
					e.from=i;
					e.to=j;
					e.cost=costs[i][j];
					q.add(e);
				}
			}
		}
		
		int total=costs[1][0];
		init();
		parents[1]=0;
		while(!q.isEmpty()){
			Edge e=q.remove();
			if(union(e.from,e.to)){
				total+=e.cost;
				v[e.from][e.to]=true;
				v[e.to][e.from]=true;
			}
//			else{
//				costs[e.from][e.to]=-1;
//				costs[e.to][e.from]=-1;
//			}
		}
		
		visited[0]=true;
		
		System.out.println(total);
		for(int i=0;i<=k;i++){
			if(!visited[i]){
				System.out.println(i+" 0");
				dfs(i);
			}
		}
		
		
	}
}
