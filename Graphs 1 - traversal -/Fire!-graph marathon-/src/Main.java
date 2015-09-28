import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int r,c,ji,jj;
	static char[][] grid = new char[1001][1001];
	static int[][] distj = new int[1001][1001];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static Queue<cell> q = new LinkedList<cell>();

	static class cell{
		int i,j;
		boolean fire;
		public cell(int x,int y,boolean t){
			i=x;j=y;fire=t;
		}
	}
	static boolean check(int i,int j,boolean t){
		boolean res = i>=0 && i<r && j>=0 && j<c  && grid[i][j]!='#';
		if(!t)
			res = res && grid[i][j]=='.';
		else
			res = res && (grid[i][j]=='.' || grid[i][j]=='J');
		return res;
	}
	static int bfs(){
		q.add(new cell(ji,jj,false));
		distj[ji][jj]=0;
		
		while(!q.isEmpty()){
			cell now = q.poll();
			if((now.i==0 || now.i==r-1 || now.j==0 || now.j==c-1 )&& !now.fire){
				return distj[now.i][now.j];
			}
			for(int i=0;i<4;i++){
				int x= now.i+dx[i] , y = now.j+dy[i];
				boolean ch = check(x,y,now.fire);
				if(now.fire && ch){
					grid[x][y]='F';
					q.add(new cell(x,y,true));
				}
				else if(!now.fire && ch){
					grid[x][y]='J';
					distj[x][y]=distj[now.i][now.j]+1;
					q.add(new cell(x,y,false));
				}
			}
		}
		return -1;
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases=Integer.parseInt(br.readLine().trim());
		String[] in;
		StringBuilder out = new StringBuilder();
		for(int z=1;z<=cases;z++){
			in=br.readLine().split("\\s+");
			r=Integer.parseInt(in[0]);
			c=Integer.parseInt(in[1]);
			String temp ;
			q.clear();
			for(int i=0;i<r;i++){
				temp = br.readLine().trim();
				for(int j=0;j<c;j++){
					grid[i][j] = temp.charAt(j);
					if(grid[i][j]=='J'){
						ji=i;jj=j;
					}
					else if(grid[i][j]=='F'){
						q.add(new cell(i,j,true));
					}
				}
			}
			int answer = bfs();
		
			if(answer!=-1)
				out.append((answer+1)+"\n");
			else
				out.append("IMPOSSIBLE\n");
			
		}
		System.out.print(out);
	}
}
