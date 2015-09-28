import java.io.BufferedReader;
import java.io.InputStreamReader;


class Main {
	static int r,c,n;
	static int[] dx={1,-1,0,0,1,1,-1,-1};
	static int[] dy={0,0,1,-1,1,-1,1,-1};
	static int[] xnow={2,-2,0,0,2,2,-2,-2};
	static int[]ynow={0,0,2,-2,2,-2,2,-2};
	
	static boolean check(int x,int y){
		if(x>=0 && x<r	&& y>=0 && y<c)
			return true;
		return false;
	}
	static boolean checkGrid(int[][] grid){
		int counter=0;
		for(int i=0;i<r;i++){
			for(int j=0;j<c;j++){
				if(grid[i][j]==1)counter++;
			}
		}
		if(counter==1)return true;
		return false;
	}
	static int rec(int[][] grid,int jumps){
		if(jumps==n-1 && !checkGrid(grid))return 0;
		if(jumps==n-1 && checkGrid(grid))return 1;
		
		int count=0;
		for(int i=0;i<r;i++){
			for(int j=0;j<c;j++){
				if(grid[i][j]==1){
					for(int k=0;k<8;k++){
						if(check(i+dx[k],j+dy[k])){
							if(grid[i+dx[k]][j+dy[k]]==1 && check(i+xnow[k],j+ynow[k])){
								if(grid[i+xnow[k]][j+ynow[k]]==0){
									grid[i][j]=0;
									grid[i+dx[k]][j+dy[k]]=0;
									grid[i+xnow[k]][j+ynow[k]]=1;
									count += rec(grid,jumps+1);
								}
							}
								
						}
					}
				}
			}
		}
		return count;
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int cases=Integer.parseInt(br.readLine());
		for(int z=1;z<=cases;z++){
			String[] in=br.readLine().split(" ");
			r=Integer.parseInt(in[0]);
			c=Integer.parseInt(in[1]);
			n=Integer.parseInt(in[2]);
			int[][] board=new int[r+1][c+1];
			for(int i=0;i<n;i++){
				in=br.readLine().split(" ");
				int x=Integer.parseInt(in[0]),y=Integer.parseInt(in[1]);
				board[x-1][y-1]=1;
			}
			System.out.println(rec(board,0));
		}
	}
}
