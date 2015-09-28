import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;


class Main {
	static int n,m,root;
	static LinkedList<Integer>[] adj,adjT;
	static int[] visited;
	static int[][] edges;
	static boolean[] scc;
	static Stack<Integer> stack=new Stack();
	
	static void dfs(int i){
		visited[i]=1;
		if(adj[i]!=null){
			for(int j=0;j<adj[i].size();j++)
				if(visited[adj[i].get(j)]==0)
					dfs(adj[i].get(j));
		}
		stack.push(i);
	}
	
	static void dfs2(int i){
		visited[i]=root;
		if(adjT[i]!=null){
			for(int j=0;j<adjT[i].size();j++){
				if(visited[adjT[i].get(j)]==0)
					dfs2(adjT[i].get(j));
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int cases=Integer.parseInt(br.readLine());
		String[] in;
		for(int z=1;z<=cases;z++){
			in=br.readLine().split(" ");
			n=Integer.parseInt(in[0]);
			m=Integer.parseInt(in[1]);
			edges=new int[m+1][2];
			adj=new LinkedList[n+1];
			adjT=new LinkedList[n+1];
			
			int from,to;
			
			for(int i=0;i<m;i++){
				in=br.readLine().split(" ");
				from=Integer.parseInt(in[0]);
				to=Integer.parseInt(in[1]);
				edges[i][0]=from;
				edges[i][1]=to;
				if(adj[from]==null)adj[from]=new LinkedList();
				if(adjT[to]==null)adjT[to]=new LinkedList();
				adj[from].add(to);
				adjT[to].add(from);
			}
			
			visited=new int[n+2];
			scc=new boolean[n+1];

			for(int i=1;i<=n;i++){
				if(visited[i]==0){
					dfs(i);
				}
			}
			
			Arrays.fill(visited,0);
			root=0;
			while(!stack.isEmpty()){
				int x=stack.pop();
				if(visited[x]==0){
					root++;
					dfs2(x);
				}
			}
			

			for(int i=0;i<m;i++){
				from=edges[i][0];
				to=edges[i][1];
				if(visited[from]!=visited[to])
					scc[visited[to]]=true;
			}
			
			int counter=0;
			for(int i=1;i<=root;i++){
				if(!scc[i])
					counter++;
			}
			System.out.println(counter);
		}
		br.close();
	}
}
