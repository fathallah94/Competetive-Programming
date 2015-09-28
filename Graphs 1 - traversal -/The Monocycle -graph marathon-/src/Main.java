import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Main {
	static int n,m,si,sj,ei,ej;
	static char[][] grid;
	static boolean[][][][] v;
	
	static class node{
		int i,j,dist,color;
		char dir ;
		public node(int x,int y,int d,int c,char di){
			i=x;j=y;dist=d;color=c;dir=di;
		}
	}
	static int convert(char di){
		int temp;
		if(di=='N')temp=0;
		else if(di=='E')temp = 1;
		else if(di=='W')temp=2;
		else temp=3;
		return temp;
	}
	static boolean check(int x,int y,int d,int c,char di){
		return x>=0 && x<n && y>=0 && y<m && !v[x][y][c][convert(di)] && grid[x][y]!='#';
	}
	
	static int bfs(){
		Queue<node> q = new LinkedList<node>();
		q.add(new node(si,sj,0,0,'N'));
		v[si][sj][0][0]=true;
		while(!q.isEmpty()){
			node now = q.poll();
			if(now.i==ei && now.j==ej && now.color==0){
				return now.dist;
			}
			//////////////////////////////////
			if(now.dir=='N'){
				if(check(now.i,now.j,now.dist+1,now.color,'E')){
					q.add(new node(now.i,now.j,now.dist+1,now.color,'E'));
					v[now.i][now.j][now.color][convert('E')]=true;
				}
				if(check(now.i,now.j,now.dist+1,now.color,'W')){
					q.add(new node(now.i,now.j,now.dist+1,now.color,'W'));
					v[now.i][now.j][now.color][convert('W')]=true;
				}
				if(check(now.i-1,now.j,now.dist+1,(now.color+1)%5,'N')){
					q.add(new node(now.i-1,now.j,now.dist+1,(now.color+1)%5,'N'));
					v[now.i-1][now.j][(now.color+1)%5][convert('N')]=true;
				}
			}
			//////////////////////////////////////////////////////////////////////////
			else if(now.dir=='E'){
				if(check(now.i,now.j,now.dist+1,now.color,'N')){
					q.add(new node(now.i,now.j,now.dist+1,now.color,'N'));
					v[now.i][now.j][now.color][convert('N')]=true;
				}
				if(check(now.i,now.j,now.dist+1,now.color,'S')){
					q.add(new node(now.i,now.j,now.dist+1,now.color,'S'));
					v[now.i][now.j][now.color][convert('S')]=true;
				}
				if(check(now.i,now.j+1,now.dist+1,(now.color+1)%5,'E')){
					q.add(new node(now.i,now.j+1,now.dist+1,(now.color+1)%5,'E'));
					v[now.i][now.j+1][(now.color+1)%5][convert('E')]=true;
				}
			}
			///////////////////////////////////////////////////////////////////////////////
			else if(now.dir=='W'){
				if(check(now.i,now.j,now.dist+1,now.color,'N')){
					q.add(new node(now.i,now.j,now.dist+1,now.color,'N'));
					v[now.i][now.j][now.color][convert('N')]=true;
				}
				if(check(now.i,now.j,now.dist+1,now.color,'S')){
					q.add(new node(now.i,now.j,now.dist+1,now.color,'S'));
					v[now.i][now.j][now.color][convert('S')]=true;
				}
				if(check(now.i,now.j-1,now.dist+1,(now.color+1)%5,'W')){
					q.add(new node(now.i,now.j-1,now.dist+1,(now.color+1)%5,'W'));
					v[now.i][now.j-1][(now.color+1)%5][convert('W')]=true;
				}
			}
			////////////////////////////////////////////////////////////////////////
			else{
				if(check(now.i,now.j,now.dist+1,now.color,'E')){
					q.add(new node(now.i,now.j,now.dist+1,now.color,'E'));
					v[now.i][now.j][now.color][convert('E')]=true;
				}
				if(check(now.i,now.j,now.dist+1,now.color,'W')){
					q.add(new node(now.i,now.j,now.dist+1,now.color,'W'));
					v[now.i][now.j][now.color][convert('W')]=true;
				}
				if(check(now.i+1,now.j,now.dist+1,(now.color+1)%5,'S')){
					q.add(new node(now.i+1,now.j,now.dist+1,(now.color+1)%5,'S'));
					v[now.i+1][now.j][(now.color+1)%5][convert('S')]=true;
				}
			}
			///////////////////////////////////////////////////////////////////////////
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		int cases=0;
		while(true){
			in = br.readLine().split(" ");
			n = Integer.parseInt(in[0]);
			m = Integer.parseInt(in[1]);
			if(n==0 && m==0)break;
			if(cases>0)
				System.out.println();
			grid = new char[n][m];
			v = new boolean[n][m][5][4];
			for(int i=0;i<n;i++){
				grid[i] = br.readLine().toCharArray();
				for(int j=0;j<m;j++){
					if(grid[i][j]=='S'){
						si=i;sj=j;
					}
					else if(grid[i][j]=='T'){
						ei=i;ej=j;
					}
				}
			}
			cases++;
			System.out.println("Case #"+cases);
			int res = bfs();
			if(res==-1)
				System.out.println("destination not reachable");
			else
				System.out.println("minimum time = "+res+" sec");
		}
	}	
}
