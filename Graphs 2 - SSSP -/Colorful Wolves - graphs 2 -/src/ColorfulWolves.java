import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ColorfulWolves {
	static public int[] dist;
	static public int[][] adj;
	static public boolean[] v;
	static public Comparator<node> compa = new comp();
	static public PriorityQueue<node> q ;
	static public int n;
	
	static public class node{
		int x,w;
		public node(int a,int b){
			x=a;w=b;
		}
	}
	static public class comp implements Comparator<node>{
		public int compare(node n1, node n2) {
			if(n1.w<n2.w)return -1;
			else if(n1.w>n2.w)return 1;
			return 0;
		}
	}
	
	static public int dijkstra(){
		dist[0]=0;
		q.add(new node(0,0));
		while(!q.isEmpty()){
			node now = q.poll();
			if(!v[now.x]){
				v[now.x]=true;
				for(int i=0;i<n;i++){
					if(adj[now.x][i]!=-1 && adj[now.x][i]+dist[now.x]<dist[i]){
						dist[i]=adj[now.x][i]+dist[now.x];
						q.add(new node(i,dist[i]));
					}
				}
			}
		}
		if(dist[n-1]==1000000000)
			return -1;
		
		return dist[n-1];
	}
	static public int getmin(String[] colormap){
		n = colormap.length;
		dist = new int[n];
		adj = new int[n][n];
		v = new boolean[n];
		q = new PriorityQueue<node>(n*2,compa);
		Arrays.fill(dist,1000000000);
		for(int i=0;i<n;i++){
			int cnt=0;
			for(int j=0;j<n;j++){
				if(colormap[i].charAt(j)=='Y')
					adj[i][j]=cnt++;
				else
					adj[i][j]=-1;
			}
		}
		return dijkstra();
	}
}
