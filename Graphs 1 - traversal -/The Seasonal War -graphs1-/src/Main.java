import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int n,count;
	static char[][] grid ;
	static boolean[][] v;
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {0,-1,1,-1,1,0,-1,1};
	static boolean check(int i,int j){
		return i>=0 && i<n && j>=0 && j<n && grid[i][j]=='1' && !v[i][j] ;
	}
	
	static void dfs(int i,int j){
		v[i][j]=true;
		for(int x = 0 ;x<8;x++){
			if(check(i+dx[x],j+dy[x])){
				dfs(i+dx[x],j+dy[x]);
			}
		}
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases=0;
		while(br.ready()){
			n = Integer.parseInt(br.readLine());
			grid = new char[n][n];
			v = new boolean[n][n];
			for(int i=0;i<n;i++)
				grid[i] = br.readLine().toCharArray();
			
			count=0;
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					if(grid[i][j]=='1' && !v[i][j]){
						count++;
						dfs(i,j);
					}
				}
			}
			cases++;
			System.out.println("Image number "+cases+" contains "+count+" war eagles.");
		}
	}
}
