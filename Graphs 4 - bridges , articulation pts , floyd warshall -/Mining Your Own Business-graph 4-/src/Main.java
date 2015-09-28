import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static int n,m,time=0,children,pts,temp;
	static int[] low,num,parents;
	static ArrayList<Integer>[] adj;
	static boolean[] ap;
	
	static void check(int i){
		num[i]=low[i]=++time;
		for(int j=0;j<adj[i].size();j++){
			int now = adj[i].get(j);
			if(num[now]==0){
				parents[now]=i;
				check(now);
				if(i==1)children++;
				low[i]=Math.min(low[i],low[now]);
				if(low[now]>=num[i]){
					ap[i]=true;
				}
			}
			else if(now!=parents[i])
				low[i]= Math.min(low[i],num[now]);
		}
	}
	
	static void dfs(int i){
		num[i]=++time;
		for(int j=0;j<adj[i].size();j++){
			int now = adj[i].get(j);
			if(num[now]==0 && !ap[now])
				dfs(now);
			else if(ap[now] && num[now]!=temp){
				pts++;
				num[now]=temp;
			}
		}
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[]in;
		int cases = 0;
		int[][] edges = new int[100000][2];
		while(true){
			m = Integer.parseInt(br.readLine().trim());
			if(m==0)break;
			int max = -1;
			for(int i=0;i<m;i++){
				in = br.readLine().split("\\s+");
				edges[i][0]=Integer.parseInt(in[0]);
				edges[i][1]=Integer.parseInt(in[1]);
				if(Math.max(edges[i][0],edges[i][1])>max)
					max=Math.max(edges[i][0],edges[i][1]);
			}
			n = max;
			ap = new boolean[n+1];
			time=children=0;
			low = new int[n+1];
			num = new int[n+1];
			adj = new ArrayList[n+1];
			parents = new int[n+1];
			for(int i=0;i<=n;i++){
				adj[i]=new ArrayList<Integer>();
				parents[i]=i;
			}
			for(int i=0;i<m;i++){
				adj[edges[i][0]].add(edges[i][1]);
				adj[edges[i][1]].add(edges[i][0]);
			}
			
			check(1);
			ap[1] = (children>1);
			Arrays.fill(num,0);
			time=0;
			temp=0;
			long res = 0,ways=1;
			for(int i=1;i<=n;i++){
				if(num[i]==0 && !ap[i]){
					time=pts=0;
					temp++;
					dfs(i);
					if(pts==0){
						res+=2;
						ways*=(time);
						ways*=time-1;
						ways/=2;
					}
					else if(pts==1){
						res++;
						ways*=time;
					}
				}
			}
			cases++;
				System.out.println("Case "+cases+": "+res+" "+ways);
		}
	}
}
