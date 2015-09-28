import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	static int n,m,k,s,t;
	static long[] distance ,distance2;
	static boolean[] v;
	static ArrayList<node>[] adjj,adjj2;
	
	static class node{
		int x;
		long w;
		public node(int a, long d){
			x=a;w=d;
		}
	}
	static class comp implements Comparator<node>{
		public int compare(node n1, node n2) {
			if(n1.w<n2.w)return -1;
			else if(n1.w>n2.w)return 1;
			return 0;
		}
	}
	static Comparator<node> compa = new comp();
	static PriorityQueue<node> q ;

	static void dijkstra(ArrayList<node>[] adj , long[]dist ,int start){
		dist[start]=0;
		q.add(new node(start,0));
		while(!q.isEmpty()){
			node n = q.poll();
			if(!v[n.x]){
				v[n.x]=true;
				for(int i=0;i<adj[n.x].size();i++){
					node curr = adj[n.x].get(i);
					if(dist[n.x]+curr.w<dist[curr.x] ){
						dist[curr.x]=curr.w+dist[n.x];
						q.add(new node(curr.x,dist[curr.x]));
					}
				}
			}
		}
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine().trim());
		String[]in ;
		for(int z=1;z<=cases;z++){
			in = br.readLine().trim().split("\\s+");
			n = Integer.parseInt(in[0]);
			m = Integer.parseInt(in[1]);
			k = Integer.parseInt(in[2]);
			s = Integer.parseInt(in[3]);
			t = Integer.parseInt(in[4]);
			distance = new long[n+10];
			distance2 = new long[n+10];
			v = new boolean[n+10];
			adjj = new ArrayList[n+10];
			adjj2 = new ArrayList[n+10];
			q = new PriorityQueue<node>(n*2,compa);
			for(int i=0;i<n+10;i++){
				adjj[i]= new ArrayList<node>();
				adjj2[i]= new ArrayList<node>();
				distance[i]=1000000000;
				distance2[i]=1000000000;
			}
			
			for(int i=0;i<m;i++){
				in = br.readLine().split("\\s+");
				int from = Integer.parseInt(in[0]) , to = Integer.parseInt(in[1]) , w = Integer.parseInt(in[2]);
				adjj[from].add(new node(to,w));
				adjj2[to].add(new node(from,w));
			}
			dijkstra(adjj,distance,s);
			Arrays.fill(v,false);
			dijkstra(adjj2,distance2,t);
			long res = Math.min(distance[t],distance2[s]);
			for(int i=0;i<k;i++){
				in = br.readLine().split("\\s+");
				int from = Integer.parseInt(in[0]) , to = Integer.parseInt(in[1]) ;
				long w = Long.parseLong(in[2]);
				res = Math.min(res,Math.min(distance[from]+w+distance2[to] , distance[to]+w+distance2[from]));
			}
			if(res==1000000000)
				System.out.println(-1);
			else
				System.out.println(res);
		}
	}
}
