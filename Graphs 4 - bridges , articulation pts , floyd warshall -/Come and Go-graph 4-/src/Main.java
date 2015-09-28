import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;


class Main {
	static int n,m;
	static boolean adj[][];
	static boolean adjT[][];
	static boolean visited[];
	
	static void dfs(int i){
		visited[i]=true;
		for(int j=1;j<=n;j++){
			if(adj[i][j] && !visited[j])
				dfs(j);
		}
	}
	
	static void dfs2(int i){
		visited[i]=true;
		for(int j=1;j<=n;j++){
			if(adjT[i][j] && !visited[j])
				dfs2(j);
		}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		while(true){
			in=br.readLine().split(" ");
			n=Integer.parseInt(in[0]);
			m=Integer.parseInt(in[1]);
			if(n==0 && m==0)break;
			
			adj=new boolean[n+2][n+2];
			adjT=new boolean[n+2][n+2];
			for(int i=0;i<m;i++){
				in=br.readLine().split(" ");
				int v=Integer.parseInt(in[0]),w=Integer.parseInt(in[1]),k=Integer.parseInt(in[2]);
				adj[v][w]=true;
				adjT[w][v]=true;
				if(k==2){
					adjT[v][w]=true;
					adj[w][v]=true;
				}
			}
			visited=new boolean[n+2];
			boolean flag=true;
			dfs(1);
			
			for(int i=1;i<=n;i++){
				if(!visited[i]){
					flag=false;
					break;
				}
			}
			if(!flag){
				System.out.println("0");
				continue;
			}
			
			Arrays.fill(visited, false);
			dfs2(1);
			for(int i=1;i<=n;i++){
				if(!visited[i]){
					flag=false;
					break;
				}
			}
			if(flag)
				System.out.println("1");
			else
				System.out.println("0");
		}
		br.close();
	}
}
