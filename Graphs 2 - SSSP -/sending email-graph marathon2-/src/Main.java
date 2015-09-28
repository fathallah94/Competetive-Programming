import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;


class Main {
	static int n,m,s,t;
	
	static class Node{
		int currNode;
		int dis;
		
	}
	static class IntCompare implements Comparator<Node> {

		
		
			

			public int compare(Node o1, Node o2) {
			
				if(o1.dis<o2.dis)
				{
					return -1;
				}else if(o1.dis>o2.dis)
				{
					return 1;
				}
				return 0;
			}

	}
	
	public static void main(String[] args)throws Exception {
		Comparator comp = new IntCompare();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] in;
		
		for(int z=1;z<=N;z++){
			in=br.readLine().split(" ");
			n=Integer.parseInt(in[0]);
			m=Integer.parseInt(in[1]);
			s=Integer.parseInt(in[2]);
			t=Integer.parseInt(in[3]);
			LinkedList<Node>[] adj=new LinkedList[n+1];
			for(int i=0;i<m;i++){
				in=br.readLine().split(" ");
				int from=Integer.parseInt(in[0]);
				int to=Integer.parseInt(in[1]);
				int w=Integer.parseInt(in[2]);
				if(adj[from]==null)
					adj[from]=new LinkedList<Node>();
				if(adj[to]==null)
					adj[to]=new LinkedList<Node>();
				Node n=new Node();
				n.currNode=from;
				n.dis=w;
				adj[to].add(n);
				Node m=new Node();
				m.currNode=to;
				m.dis=w;
				adj[from].add(m);
			}
			
			int[] dist=new int[n+1];
			boolean v[] = new boolean[n+1];
			PriorityQueue<Node> q=new PriorityQueue<Node>(n+1,comp);
			Arrays.fill(dist, 1000000000);
			dist[s]=0;
			Node node = new Node();
			node.currNode=s;
			node.dis=0;
			q.add(node);
			while(!q.isEmpty()){
				Node x=q.remove();
				if(!v[x.currNode]){
					v[x.currNode]=true;
					int size;
					if(adj[x.currNode]==null)
						size=0;
					else
						size=adj[x.currNode].size();
					for(int i=0;i<size;i++){
						if(dist[x.currNode]+adj[x.currNode].get(i).dis< dist[adj[x.currNode].get(i).currNode]){
							dist[adj[x.currNode].get(i).currNode]=dist[x.currNode]+adj[x.currNode].get(i).dis;
							Node w=new Node();
							w.currNode=adj[x.currNode].get(i).currNode;
							w.dis=dist[adj[x.currNode].get(i).currNode];
							q.add(w);
						}
					}
				}
			}
			System.out.printf("Case #%d: ",z);
			if(dist[t]==1000000000)
				System.out.printf("unreachable\n");
			else
				System.out.printf("%d\n",dist[t]);
		}
		br.close();
	}

}
