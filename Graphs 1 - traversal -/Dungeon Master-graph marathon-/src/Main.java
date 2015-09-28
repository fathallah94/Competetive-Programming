import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Main {
	static int l,r,c,si,sj,sz,ei,ej,ez;
	static char[][][] grid ;
	static boolean[][][] v;
	static int[] dx = {-1,1,0,0,0,0};
	static int[] dy = {0,0,-1,1,0,0};
	static int[] dl = {0,0,0,0,1,-1};
	
	static class node{
		int z,i,j,dist;
		public node(int z,int x,int y,int m){
			this.z=z;i=x;j=y;dist=m;
		}
	}
	
	static boolean check(int z,int i,int j){
		return z>=0 && z<l && i>=0 && i<r && j>=0 && j<c && !v[z][i][j] && (grid[z][i][j]=='.'|| grid[z][i][j]=='E');
	}
	
	static int bfs(){
		Queue<node> q = new LinkedList<node>();
		q.add(new node(sz,si,sj,0));
		v[sz][si][sj]=true;
		
		while(!q.isEmpty()){
			node now = q.poll();
			if(now.z==ez && now.i==ei && now.j==ej){
				return now.dist;
			}
			for(int i=0;i<6;i++){
				if(check(now.z+dl[i] , now.i+dx[i], now.j+dy[i])){
					q.add(new node(now.z+dl[i] , now.i+dx[i], now.j+dy[i] , now.dist+1));
					v[now.z+dl[i] ][ now.i+dx[i]][ now.j+dy[i] ]=true;
				}
			}
		}
		return -1;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[]in ;
		while(true){
			in = br.readLine().split(" ");
			l = Integer.parseInt(in[0]);
			r = Integer.parseInt(in[1]);
			c = Integer.parseInt(in[2]);
			if(l==0 && r==0 && c==0)break;
			grid = new char[l][r][c];
			v = new boolean[l][r][c];
			for(int i=0;i<l;i++){
				for(int j=0;j<r;j++){
					grid[i][j] = br.readLine().toCharArray();
					for(int w=0;w<c;w++){
						if(grid[i][j][w]=='S'){
							sz=i;si=j;sj=w;
						}
						else if(grid[i][j][w]=='E'){
							ez=i;ei=j;ej=w;
						}
					}
				}
				br.readLine();
			}
			int res = bfs();
			if(res==-1)
				System.out.println("Trapped!");
			else
				System.out.println("Escaped in "+res+" minute(s).");
		}
	}
}
