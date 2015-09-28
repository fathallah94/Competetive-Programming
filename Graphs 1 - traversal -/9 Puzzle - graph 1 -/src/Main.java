import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Main {
	static int[][] s = new int[3][3];
	static String[] moves = new String[362885];
	static int[] parents = new int[362885] , dist = new int[362885];
	static StringBuilder out = new StringBuilder();
	static Set<String> set = new HashSet<String>();
	static Map<String,Integer> map = new HashMap<String,Integer>();
	static int counter =0;
	
	static class node{
		int[][] grid ;
		String str;
		public node(int[][] grid){
			this.grid = new int[3][3];
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					this.grid[i][j]=grid[i][j];
			str = convert(this.grid);
		}
	}
	
	static void bfs(){
		Queue<node> q = new LinkedList<node>();
		int[][] temp = new int[3][3];
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				temp[i][j]=i*3+j+1;
		String str = convert(temp);
		q.add(new node(temp));
		set.add(str);
		map.put(str, counter++);
		dist[counter-1]=0;
		while(!q.isEmpty()){
			node n = q.poll();
			int now = map.get(n.str);
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					temp[i][j]=n.grid[i][j];
			///////h1
			int tm = temp[0][0];
			temp[0][0]=temp[0][1];
			temp[0][1]=temp[0][2];
			temp[0][2]=tm;
			str = convert(temp);
			if(!set.contains(str)){
				map.put(str, counter++);
				set.add(str);
				moves[counter-1]="H1";
				parents[counter-1]=now;
				dist[counter-1]=dist[now]+1;
				q.add(new node(temp));
 
			}
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					temp[i][j]=n.grid[i][j];
			///////h2
			tm = temp[1][0];
			temp[1][0]=temp[1][1];
			temp[1][1]=temp[1][2];
			temp[1][2]=tm;
			str = convert(temp);
			if(!set.contains(str)){
				set.add(str);
				map.put(str, counter++);
				moves[counter-1]="H2";
				parents[counter-1]=now;
				dist[counter-1]=dist[now]+1;
				q.add(new node(temp));
			}
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					temp[i][j]=n.grid[i][j];
			///////h3
			tm = temp[2][0];
			temp[2][0]=temp[2][1];
			temp[2][1]=temp[2][2];
			temp[2][2]=tm;
			str = convert(temp);
			if(!set.contains(str)){
				set.add(str);
				map.put(str, counter++);
				moves[counter-1]="H3";
				parents[counter-1]=now;
				dist[counter-1]=dist[now]+1;
				q.add(new node(temp));
			}
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					temp[i][j]=n.grid[i][j];
			///////v1
			tm = temp[2][0];
			temp[2][0]=temp[1][0];
			temp[1][0]=temp[0][0];
			temp[0][0]=tm;
			str = convert(temp);
			if(!set.contains(str)){
				set.add(str);
				map.put(str, counter++);
				moves[counter-1]="V1";
				parents[counter-1]=now;
				dist[counter-1]=dist[now]+1;
				q.add(new node(temp));
			}
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					temp[i][j]=n.grid[i][j];
			///////v2
			tm = temp[2][1];
			temp[2][1]=temp[1][1];
			temp[1][1]=temp[0][1];
			temp[0][1]=tm;
			str = convert(temp);
			if(!set.contains(str)){
				set.add(str);
				map.put(str, counter++);
				moves[counter-1]="V2";
				parents[counter-1]=now;
				dist[counter-1]=dist[now]+1;
				q.add(new node(temp));
			}
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					temp[i][j]=n.grid[i][j];
			/////v3
			tm = temp[2][2];
			temp[2][2]=temp[1][2];
			temp[1][2]=temp[0][2];
			temp[0][2]=tm;
			str = convert(temp);
			if(!set.contains(str)){
				set.add(str);
				map.put(str, counter++);
				moves[counter-1]="V3";
				parents[counter-1]=now;
				dist[counter-1]=dist[now]+1;
				q.add(new node(temp));
			}			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					temp[i][j]=n.grid[i][j];
			
		}
	}
	
	static String convert(int[][] m){
		StringBuilder res = new StringBuilder();
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				res.append(m[i][j]);
			}
		}
		return res.toString();
	}
	static void answer(int curr){
		int start = map.get("123456789");
		while(curr!=start){
			out.append(moves[curr]);
			curr=parents[curr];
		}
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[]in ;
		boolean flag=true;
		for(int i=0;i<1000;i++)
			parents[i]=i;
		
		bfs();
		while(true){
			for(int i=0;i<3;i++){
				in = br.readLine().split(" ");
				if(in[0].charAt(0)=='0'){
					flag=false;break;
				}
				for(int j=0;j<3;j++)
					s[i][j]=Integer.parseInt(in[j]);
			}
			if(!flag)break;
			String str = convert(s);
			if(!set.contains(str))
				out.append("Not solvable\n");
			else{
				int num = map.get(str);
				out.append(dist[num]+" ");
				answer(num);
				out.append("\n");
			}
		}
		System.out.print(out);
	}
}
