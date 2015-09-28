import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;


class Main {
	static int n;
	static LinkedList<Integer>[] adj;
	static int[] low,disc,parent;
	static boolean[] visited;
	static int time=0;
	static PriorityQueue<Edge> q;
	
	static class IntCompare implements Comparator<Edge> {
		 
		public int compare(Edge o1, Edge o2) {
 
			if(Math.min(o1.from,o1.to)>Math.min(o2.from,o2.to))
			{
				return 1;
			}else if(Math.min(o1.from,o1.to)<Math.min(o2.from,o2.to))
			{
				return -1;
			}
			else if((Math.min(o1.from,o1.to)==Math.min(o2.from,o2.to)) && Math.abs(o1.from-o1.to) < Math.abs(o2.from-o2.to) )
				return -1;
			else if((Math.min(o1.from,o1.to)==Math.min(o2.from,o2.to)) && Math.abs(o1.from-o1.to) > Math.abs(o2.from-o2.to) )
				return 1;
			return 0;
		}
 
	} 
	static Comparator<Edge>comp = new IntCompare();
	
	static class Edge{
		Edge(int x,int y){
			from=x;
			to=y;
		}
		int from,to;
	}
	
	static void dfs(int i){
		int children=0;
		disc[i]=low[i]=++time;
		visited[i]=true;
		for(int j=0;j<adj[i].size();j++){
			int current=adj[i].get(j);
			if(!visited[current]){
				children++;
				parent[current]=i;
				dfs(current);
				low[i]=Math.min(low[i], low[current]);
				if(low[current]>disc[i]){
					Edge e=new Edge(i,current);
					q.add(e);
				}
			}
			
			else if(current!=parent[i])
				low[i]=Math.min(low[i], disc[current]);
		}
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String input=" ";
		boolean flag=false;
		
		while(true){
			if(flag)
				input=br.readLine();
			flag=true;
			if(input==null)break;
			
			input=br.readLine();
			if(input==null)break;
			
			n=Integer.parseInt(input);
			adj=new LinkedList[n+1];
			for(int i=0;i<n;i++){
				String[] in=br.readLine().split(" ");
				int curr=Integer.parseInt(in[0]);
				if(adj[curr]==null)
					adj[curr]=new LinkedList();
				for(int j=2;j<in.length;j++)
					adj[curr].add(Integer.parseInt(in[j]));
			}
			low=new int[n+1];
			disc=new int[n+1];
			parent=new int[n+1];
			Arrays.fill(parent,-1);
			visited=new boolean[n+1];
			time=0;
			q=new PriorityQueue<Edge>(n*n+2,comp);
			
			for(int i=0;i<n;i++)
				if(!visited[i])
					dfs(i);
			
			System.out.println(q.size()+" critical links");
			while(!q.isEmpty()){
				Edge e=q.remove();
				System.out.println(Math.min(e.from, e.to)+" - "+Math.max(e.from, e.to));		
			}
			System.out.println();
		}
		
		br.close();
	}
	
}
