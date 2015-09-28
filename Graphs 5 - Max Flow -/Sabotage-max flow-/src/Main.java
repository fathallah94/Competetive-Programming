import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


class Main {
	static int n,s=1,t=2,m;
	static long[][] flow,cap,original;
	static 	boolean[] v;
	static boolean[][] cut;
	
	static long maxFlow() {
        long totalFlow = 0;
        while (true) {
                Queue<Integer> q = new LinkedList<>();
                int[] parent = new int[cap.length];
                Arrays.fill(parent, -1);
                q.add(s);
                while (!q.isEmpty()) {
                        int node = q.poll();
                        for (int j = 1; j <= n; j++) {
                                if (cap[node][j] - flow[node][j] > 0 && parent[j] == -1) {
                                        parent[j] = node;
                                        q.add(j);
                                }
                        }
                }
                if (parent[t] == -1)
                        break;
                long cflow = Long.MAX_VALUE;
                int current = t;
                while (current != s) {
                        cflow = Math.min(cflow, cap[parent[current]][current]
                                        - flow[parent[current]][current]);
                        current = parent[current];
                }
                current = t;
                while (current != s) {
                        flow[parent[current]][current] += cflow;
                        flow[current][parent[current]] -= cflow;
                        current=parent[current];
                }
                totalFlow += cflow;
        }
        return totalFlow;
	}
	
	
	static void dfs(int i){
		v[i]=true;
		for(int j=1;j<=n;j++){
			if(original[i][j]==flow[i][j] && original[i][j]>0){
				cut[i][j]=true;
			}
			
			else if(!v[j] && original[i][j]>0 && original[i][j]>flow[i][j] && flow[i][j]>0)
				dfs(j);
		}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader (new InputStreamReader(System.in));
		String[] in;
		while(true){
			in=br.readLine().split(" ");
			n=Integer.parseInt(in[0]);
			m=Integer.parseInt(in[1]);
			if(n==0 && m==0)break;
			
			cap=new long[n+5][n+5];
			original=new long[n+5][n+5];
			cut=new boolean[n+5][n+5];
			flow=new long[n+5][n+5];
			
			for(int i=0;i<m;i++){
				in=br.readLine().split(" ");
				int from=Integer.parseInt(in[0]),to=Integer.parseInt(in[1]),w=Integer.parseInt(in[2]);
				cap[from][to]=w;
				original[from][to]=w;
				cap[to][from]=w;
				original[to][from]=w;
			}
			v=new boolean[n+5];
			
			maxFlow();
			
			dfs(1);
			
			for(int i=1;i<=n;i++){
				for(int j=1;j<=n;j++){
					if(cut[i][j]){
						System.out.println(i+" "+j);
						cut[j][i]=false;
					}
				}
			}
			
			System.out.println();
		}
		br.close();
	}
}
