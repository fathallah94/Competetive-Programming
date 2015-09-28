import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;


class Main {
	static int n,m;
	static int[] parents=new int[1005];
	static int[] extra=new int[25005]; 
	
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
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] in;
	
		while(true){
			in=br.readLine().split(" ");
			n=Integer.parseInt(in[0]);
			m=Integer.parseInt(in[1]);
			if(n==0 && m==0)
				break;
			PriorityQueue<Edge> q=new PriorityQueue<Edge>(m+5,comp);
			for(int i=0;i<m;i++){
				in=br.readLine().split(" ");
				Edge e=new Edge();
				e.from=Integer.parseInt(in[0]);
				e.to=Integer.parseInt(in[1]);
				e.cost=Integer.parseInt(in[2]);
				q.add(e);
			}
			int edges=0,extraC=0;
			init();
			while(!q.isEmpty()){
				Edge e=q.remove();
				if(union(e.from,e.to))
					edges++;
				else
					extra[extraC++]=e.cost;
			}
			if(extraC!=0){
				for(int i=0;i<extraC-1;i++)
					System.out.print(extra[i]+" ");
				System.out.println(extra[extraC-1]);
			}
			else
				System.out.println("forest");
		}
		br.close();
	}
}
