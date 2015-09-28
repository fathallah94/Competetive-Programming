import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Main {
	static ArrayList<String> dic = new ArrayList<String>();
	static Map<String,Integer> map = new HashMap<String,Integer>();
	static Set<String> set = new HashSet<String>();
	static ArrayList<Integer>[] adj;
	static int[] v , parents ;
	static int count=0 ;
	static StringBuilder out = new StringBuilder();
	static Queue<Integer> q = new LinkedList<Integer>();
	
	static String to_s(char[] tem){
		StringBuilder s = new StringBuilder();
		for(int i=0;i<tem.length;i++)
			s.append(tem[i]);
		return s.toString();
	}
	
	static void bfs(int s,int t){
		q.clear();
		q.add(s);
		v[s]=++count;
		while(!q.isEmpty()){
			int now = q.poll();
			if(now==t){
				get_path(s,t);
				return;
			}
			for(int i=0;i<adj[now].size();i++){
				int curr = adj[now].get(i);
				if(v[curr]!=count){
					v[curr]=count;
					parents[curr]=now;
					q.add(curr);
				}
			}
		}
		out.append("No solution.\n");
	}
	static void get_path(int s,int curr){
		if(curr==s){
			out.append(dic.get(s)+"\n");
			return ;
		}
		get_path(s,parents[curr]);
		out.append(dic.get(curr)+"\n");
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int ind=0;
		while(true){
			String s = br.readLine();
			if(s.length()==0)break;
			dic.add(s);
			map.put(s, ind++);
			set.add(s);
		}
		v = new int[dic.size()];
		parents = new int[dic.size()];
		adj = new ArrayList[dic.size()];
		char[] temp;
		for(int w=0;w<dic.size();w++){
			parents[w]=w;
			adj[w] = new ArrayList<Integer>();
			String str = dic.get(w);
			for(int i=0;i<str.length();i++){
				temp = str.toCharArray();
				for(int j=0;j<26;j++){
					if('a'+j!=str.charAt(i)){
						temp[i]=(char)('a'+j);
						String curr = to_s(temp);
						if(set.contains(curr)){
							adj[w].add(map.get(curr));
						}
					}
				}
			}
		}
		
		String[] in ;
		boolean flag=true;
		while(br.ready()){
			if(!flag)
				out.append("\n");
			flag=false;
			in = br.readLine().split(" ");
			if(in[0].length()!=in[1].length() || !set.contains(in[0]) || !set.contains(in[1]))
				out.append("No solution.\n");
			
			else{
				int s = map.get(in[0]);
				int t = map.get(in[1]);
				bfs(s,t);
			}
		}
		
		System.out.print(out);
	}
}
