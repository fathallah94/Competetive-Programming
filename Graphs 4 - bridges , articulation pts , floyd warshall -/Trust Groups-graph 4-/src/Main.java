import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


class Main {
	static int n,m;
	static boolean[][] adj,adjT;
	static boolean[] visited;
	static Stack<Integer> stack = new Stack();
	static Map map=new HashMap();
	
	static void dfs(int i){
		visited[i]=true;
		for(int j=0;j<n;j++){
			if(adj[i][j] && !visited[j])
				dfs(j);
		}
		stack.push(i);
	}
	
	static void dfs2(int i){
		visited[i]=true;
		for(int j=0;j<n;j++){
			if(adjT[i][j] && !visited[j])
				dfs2(j);
		}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		String[] in;
		String input;
		while(true){
			in=br.readLine().split(" ");
			n=Integer.parseInt(in[0]);
			m=Integer.parseInt(in[1]);
			if(n==0 && m==0)break;
			adj=new boolean[n+2][n+2];
			adjT=new boolean[n+2][n+2];
			for(int i=0;i<n;i++){
				input=br.readLine();
				map.put(input, i);
			}
			for(int i=0;i<m;i++){
				input=br.readLine();
				int from=(int) map.get(input);
				input=br.readLine();
				int to=(int) map.get(input);
				adj[from][to]=true;
				adjT[to][from]=true;
			}
			visited=new boolean[n+2];
			for(int i=0;i<n;i++)
				if(!visited[i])
					dfs(i);
			
			int counter=0;
			Arrays.fill(visited,false);
			
			while(!stack.isEmpty()){
				int x=stack.pop();
				if(!visited[x]){
					counter++;
					dfs2(x);
				}
			}
			map.clear();
			System.out.println(counter);
		}
		br.close();
	}
}
