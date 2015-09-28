import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Main {
	static int n,m,k;
	static ArrayList<node>[] adj ;
	static boolean[] v;
	static long[] dist;
	static boolean[] parents ;
	
	static class node{
		int x;
		boolean train ;
		long w;
		public node(int a,long w,boolean t){
			x=a;this.w=w;train=t;
		}
	}
	static class comp implements Comparator<node>{
		public int compare(node n1, node n2) {
			if(n1.w<n2.w)
				return -1;
			else if(n1.w>n2.w)
				return 1;
			return 0;
		}
	}
	static Comparator<node> compa = new comp();
	static PriorityQueue<node> q ;
	
	static void dijkstra(){
		dist[1]=0;
		parents[1]=false;
		q.add(new node(1,0,false));
		
		while(!q.isEmpty()){
			node n = q.poll();
			if(!v[n.x]){
				v[n.x]=true;
				for(int i=0;i<adj[n.x].size();i++){
					node curr = adj[n.x].get(i);
					if(dist[n.x]+curr.w<dist[curr.x]){
						dist[curr.x] = curr.w+dist[n.x];
						parents[curr.x]=curr.train;
						q.add(new node(curr.x,dist[curr.x],curr.train));
					}
					else if(dist[n.x]+curr.w==dist[curr.x] && parents[curr.x] && !curr.train){
						parents[curr.x]=false;
						q.add(new node(curr.x,dist[curr.x],false));
					}
				}
			}
		}
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[]in = br.readLine().split(" ");
		n = Integer.parseInt(in[0]);
		m = Integer.parseInt(in[1]);
		k = Integer.parseInt(in[2]);
		v = new boolean[n+10];
		dist = new long[n+10];
		adj = new ArrayList[n+10];
		parents = new boolean[n+10];
		q = new PriorityQueue<node>(2*n,compa);
		for(int i=0;i<n+10;i++){
			adj[i] = new ArrayList<node>();
			dist[i]= Long.MAX_VALUE;
		}
		for(int i=0;i<m;i++){
			in = br.readLine().split(" ");
			int from = Integer.parseInt(in[0]), to = Integer.parseInt(in[1]) , w = Integer.parseInt(in[2]);
			adj[from].add(new node(to,w,false));
			adj[to].add(new node(from,w,false));
		}
		for(int i=0;i<k;i++){
			in = br.readLine().split(" ");
			int to = Integer.parseInt(in[0]), w = Integer.parseInt(in[1]);
			adj[1].add(new node(to,w,true));
			adj[to].add(new node(1,w,true));
		}
		
		dijkstra();
		int res = 0;
		for(int i=0;i<parents.length;i++){
			if(parents[i])
				res++;
		}
		System.out.println(k-res);
	}
}
