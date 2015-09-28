import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


class Main {
	static int n,m;
	static int[] rank;
	static boolean[][] adj;
	static int[] indegree;
	
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int cases=Integer.parseInt(br.readLine());
		String[] in;
		for(int z=1;z<=cases;z++){
			n=Integer.parseInt(br.readLine());
			rank=new int[n+5];
			indegree=new int[n+5];
			adj=new boolean[n+5][n+5];
			
			
			in=br.readLine().split(" ");
			for(int i=0;i<n;i++)
				rank[i]=Integer.parseInt(in[i]);
			
			for(int i=0;i<n;i++){
				for(int j=i+1;j<n;j++){
					adj[rank[i]][rank[j]]=true;
					indegree[rank[j]]++;
				}
			}
			
			m=Integer.parseInt(br.readLine());
			int from,to;
			boolean flag=false;
			
			for(int i=0;i<m;i++){
				in=br.readLine().split(" ");
				from=Integer.parseInt(in[0]);
				to=Integer.parseInt(in[1]);
				if(adj[from][to]){
					flag=true;
				}
				if(adj[to][from]==false){
					flag=true;
				}
				adj[from][to]=true;
				adj[to][from]=false;
				for(int j=1;j<=n;j++){
					if(adj[to][j])
						adj[from][j]=true;
				}
				indegree[to]++;
				indegree[from]--;
			}
			
			if(flag){
				System.out.println("IMPOSSIBLE");
				continue;
			}
			
			Queue<Integer> q=new LinkedList();
			for(int i=1;i<=n;i++){
				if(indegree[i]==0)
					q.add(i);
			}
			
			int[] answer=new int[n+5];
			int counter=0;
			while(!q.isEmpty()){
				if(q.size()>1)
					answer[counter++]=-1;
				else
					answer[counter++]=q.peek();
				
				for(int i=1;i<=n;i++){
					if(adj[q.peek()][i]){
						indegree[i]--;
						if(indegree[i]==0)
							q.add(i);
					}
					
				}
				q.remove();
			}
			
			if(counter!=n)
				System.out.println("IMPOSSIBLE");
			else{
				for(int i=0;i<counter-1;i++){
					if(answer[i]==-1)
						System.out.print("? ");
					else
						System.out.print(answer[i]+" ");
				}
				if(answer[counter-1]==-1)
					System.out.println("?");
				else
					System.out.println(answer[counter-1]);
			}
		}
		br.close();
	}
}
