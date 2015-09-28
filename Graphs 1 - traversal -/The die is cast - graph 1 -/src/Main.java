import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class Main {
	static int n,m,count=0;
	static char[][] grid ;
	static boolean[][] v,vx;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean check_die(int i,int j){
		return i>=0 && i<n && j>=0 && j<m && !v[i][j] && (grid[i][j]=='*' || grid[i][j]=='X');
	}
	static boolean checkx(int i,int j){
		return i>=0 && i<n && j>=0 && j<m && !vx[i][j] && grid[i][j]=='X';
	}
	
	static void dfs_x(int i,int j){
		vx[i][j]=true;
		for(int z=0;z<4;z++){
			if(checkx(i+dx[z],j+dy[z]))
				dfs_x(i+dx[z],j+dy[z]);
		}
	}
	
	static void dfs(int i,int j){
		v[i][j]=true;
		for(int z=0;z<4;z++){
			if(checkx(i+dx[z],j+dy[z])){
				count++;
				dfs_x(i+dx[z],dy[z]+j);
			}
			if(check_die(i+dx[z],j+dy[z]))
				dfs(i+dx[z],j+dy[z]);
		}
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int cases=0;
		while(true){
			String[]in = br.readLine().split(" ");
			m = Integer.parseInt(in[0]);
			n = Integer.parseInt(in[1]);
			if(m==0 && n==0)break;

			grid = new char[n][m];
			v = new boolean[n][m];
			vx = new boolean[n][m];
			for(int i=0;i<n;i++)
				grid[i]=br.readLine().toCharArray();
			
			ArrayList<Integer> res = new ArrayList<Integer>();
			for(int i=0;i<n;i++){
				for(int j=0;j<m;j++){
					if(!v[i][j] && (grid[i][j]=='*')){
						count=0;
						dfs(i,j);
						res.add(count);
					}
					else if(!v[i][j] && grid[i][j]=='X'){
						count=1;
						dfs_x(i,j);
						dfs(i,j);
						res.add(count);
					}
				}
			}
			cases++;
			System.out.println("Throw "+cases);
			Collections.sort(res);
			System.out.print(res.get(0));
			for(int i=1;i<res.size();i++)
				System.out.print(" "+res.get(i));
			System.out.println();
			System.out.println();
		}
	}
}
