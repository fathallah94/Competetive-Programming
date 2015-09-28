import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
	static int n,m;
	static int[][] grid ;
	static boolean[][] v;
	static Queue<Integer>temp = new LinkedList<Integer>();
	static Stack<node> stack = new Stack<node>();
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int count ;
	
	static class node{
		int i,j;
		public node(int x,int y){
			i=x;j=y;
		}
	}
	static boolean check(int i,int j){
		return i>=0 && i<n && j>=0 && j<m*4 && !v[i][j];
	}
	static void dfs(int i,int j,int target){
		stack.clear();
		v[i][j]=true;
		stack.push(new node(i,j));
		while(!stack.isEmpty()){
			node now = stack.pop();
			for(int w=0;w<4;w++){
				int x = now.i+dx[w],y = now.j+dy[w];
				boolean ch = check(x,y);
				if(ch && target==grid[x][y]){
					v[x][y]=true;
					stack.push(new node(x,y));
				}
				else if(ch && target==1 && grid[x][y]==0){
					dfs2(x,y);
					count++;
				}
			}
		}
	}
	static void dfs2(int i,int j){
		v[i][j]=true;
		for(int w=0;w<4;w++){
			int x = i +dx[w] , y = j+dy[w];
			if(check(x,y) && grid[x][y]==0)
				dfs2(x,y);
		}
	}
	static void convert(char[] line){
		for(int i=0;i<line.length;i++){
			if(line[i]=='0'){
				temp.add(0);temp.add(0);temp.add(0);temp.add(0);
			}
			else if(line[i]=='1'){
				temp.add(0);temp.add(0);temp.add(0);temp.add(1);
			}
			else if(line[i]=='2'){
				temp.add(0);temp.add(0);temp.add(1);temp.add(0);
			}
			else if(line[i]=='3'){
				temp.add(0);temp.add(0);temp.add(1);temp.add(1);
			}
			else if(line[i]=='4'){
				temp.add(0);temp.add(1);temp.add(0);temp.add(0);
			}
			else if(line[i]=='5'){
				temp.add(0);temp.add(1);temp.add(0);temp.add(1);
			}
			else if(line[i]=='6'){
				temp.add(0);temp.add(1);temp.add(1);temp.add(0);
			}
			else if(line[i]=='7'){
				temp.add(0);temp.add(1);temp.add(1);temp.add(1);
			}
			else if(line[i]=='8'){
				temp.add(1);temp.add(0);temp.add(0);temp.add(0);
			}
			else if(line[i]=='9'){
				temp.add(1);temp.add(0);temp.add(0);temp.add(1);
			}
			else if(line[i]=='a'){
				temp.add(1);temp.add(0);temp.add(1);temp.add(0);
			}
			else if(line[i]=='b'){
				temp.add(1);temp.add(0);temp.add(1);temp.add(1);
			}
			else if(line[i]=='c'){
				temp.add(1);temp.add(1);temp.add(0);temp.add(0);
			}
			else if(line[i]=='d'){
				temp.add(1);temp.add(1);temp.add(0);temp.add(1);
			}
			else if(line[i]=='e'){
				temp.add(1);temp.add(1);temp.add(1);temp.add(0);
			}
			else if(line[i]=='f'){
				temp.add(1);temp.add(1);temp.add(1);temp.add(1);
			}
		}
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[]in ;
		int cases=0;
		while(true){
			in = br.readLine().split("\\s+");
			n = Integer.parseInt(in[0]);
			m = Integer.parseInt(in[1]);
			if(n==0 && m==0)break;
			grid = new int[n][m*4];
			v = new boolean[n][m*4];
			
			for(int i=0;i<n;i++){
				convert(br.readLine().toCharArray());
				for(int j=0;j<m*4;j++)
					grid[i][j]=temp.poll();
			}
			////converted binary grid and 2 vs ready for dfs
			for(int i=0;i<n;i++){
				if(grid[i][0]==0 && !v[i][0]){
					dfs(i,0,0);
				}
				if(grid[i][m*4-1]==0 && !v[i][m*4-1]){
					dfs(i,m*4-1,0);
				}
			}
			for(int i=0;i<m*4;i++){
				if(grid[0][i]==0 && !v[0][i]){
					dfs(0,i,0);
				}
				if(grid[n-1][i]==0 && !v[n-1][i]){
					dfs(n-1,i,0);
				}
			}
			ArrayList<Character> res = new ArrayList<Character>();
			for(int i=0;i<n;i++){
				for(int j=0;j<m*4;j++){
					if(grid[i][j]==1 && !v[i][j]){
						count=0;
						dfs(i,j,1);
						if(count==1)
							res.add('A');
						else if(count==3)
							res.add('J');
						else if(count==5)
							res.add('D');
						else if(count==4)
							res.add('S');
						else if(count==0)
							res.add('W');
						else if(count==2)
							res.add('K');
					}
				}
			}
			Collections.sort(res);
			cases++;
			System.out.print("Case "+cases+": ");
			for(int i=0;i<res.size();i++)
				System.out.print(res.get(i));
			System.out.println();
		}
	}
}
