import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n;
	static String[] adj;
	static int[] parents,v;
	static ArrayList<Integer> l1 = new ArrayList<Integer>();
	static ArrayList<Integer> l2 = new ArrayList<Integer>();
	static int[] ans = new int[3];
	static boolean flag=false;
	
	static void dfs(int i){
		if(flag)return;
		v[i]=1;
		for(int j=0;j<n;j++){
			if(adj[i].charAt(j)=='0')continue;
			if(v[j]==0){
				parents[j]=i;
				dfs(j);
				if(flag)return;
			}
			else if(v[j]==1){
				l1.add(i);
				l2.add(j);
				int p = parents[i];
				if(parents[p]==j){
					ans[0]=j;ans[2]=i;ans[1]=p;
					flag=true;return;
				}
			}
		}
		v[i]=2;
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		v = new int[n];
		parents = new int[n];
		adj = new String[n]; 
		for(int i=0;i<n;i++){
			adj[i] = br.readLine();
			parents[i]=i;
		}
		for(int i=0;i<n;i++){
			if(v[i]==0)
				dfs(i);
			if(flag)break;
		}
		if(!flag){
			for(int i=0;i<l1.size() && !flag;i++){
				for(int j=0;j<n;j++){
					int start = l2.get(i) , end = l1.get(i);
					if(adj[start].charAt(j)=='1' && adj[j].charAt(end)=='1'){
						flag=true;
						ans[0]=start;ans[1]=j;ans[2]=end;break;
					}
				}
			}
		}
		ans[0]++;ans[1]++;ans[2]++;
		if(flag)
			System.out.println(ans[0]+" "+ans[1]+" "+ans[2]);
		else
			System.out.println(-1);
	}
}	
