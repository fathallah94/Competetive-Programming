import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


class Main {
	static int n,time=0,counter=0;
	static int[] low,disc,parent;
	static boolean[][] adj;
	static boolean[] visited;
	static boolean[] ap;
	
	static void dfs(int i){
		visited[i]=true;
		int children=0;
		disc[i]=low[i]=++time;
		
		for(int j=1;j<=n;j++){
			if(!adj[i][j]){
				continue;
			}
			
			if(!visited[j] ){
				children++;
				parent[j]=i;
				dfs(j);
				low[i]=Math.min(low[i],low[j]);
				if(parent[i]==-1 && children>1){
					ap[i]=true;
				}
				if(parent[i]!=-1 && low[j]>=disc[i]){
					ap[i]=true;
				}
			}
			else if(j!=parent[i] )
				low[i]=Math.min(low[i],disc[j]);
		}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		while(true){
			n=Integer.parseInt(br.readLine());
			if(n==0)break;
			adj=new boolean[n+1][n+1];
			while(true){
				in=br.readLine().split(" ");
				int curr=Integer.parseInt(in[0]);
				if(curr==0 && in.length==1)break;
				for(int i=1;i<in.length;i++){
					int now=Integer.parseInt(in[i]);
					adj[curr][now]=adj[now][curr]=true;
				}
			}
			
			low=new int[n+5];
			parent=new int[n+5];
			disc=new int[n+5];
			ap=new boolean[n+5];
			visited=new boolean[n+5];
			Arrays.fill(parent, -1);
			time=0;
			for(int w=1;w<=n;w++){
				if(!visited[w])
					dfs(w);
			}
			
			int x=0;
			for(int w=1;w<=n;w++)
				if(ap[w]){
					x++;
				}
			System.out.println(x);
		}
		br.close();
	}
}
