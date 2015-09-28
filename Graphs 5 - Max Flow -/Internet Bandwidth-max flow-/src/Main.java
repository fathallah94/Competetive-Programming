import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


class Main {
	static int n,s,t,c;
	static int[][] flow,cap;
	
	static long maxFlow() {
        flow = new int[cap.length][cap.length];
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
                int cflow = Integer.MAX_VALUE;
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
	static public void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int cases=0;
		while(true){
			cases++;
			n=Integer.parseInt(br.readLine());
			if(n==0)break;
			String[] in=br.readLine().split(" ");
			s=Integer.parseInt(in[0]);
			t=Integer.parseInt(in[1]);
			c=Integer.parseInt(in[2]);
			
			cap=new int[n+5][n+5];
			
			for(int i=0;i<c;i++){
				in=br.readLine().split(" ");
				int from=Integer.parseInt(in[0]),to=Integer.parseInt(in[1]),w=Integer.parseInt(in[2]);
				cap[from][to]+=w;
				cap[to][from]+=w;
			}
			
			System.out.println("Network "+cases);
			System.out.println("The bandwidth is "+maxFlow()+".");
			System.out.println();
		}
		br.close();
	}
}
