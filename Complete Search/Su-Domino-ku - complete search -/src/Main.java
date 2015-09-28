import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
	static int[][] grid = new int[10][10],res = new int[9][9];
	static int[] rows=new int[10],cols=new int[10],squares=new int[10],dominos=new int[10];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean flag =false;
	
	static public boolean check(int i,int j){
		if(i>=0 && i<9 && j>=0 && j<9 && grid[i][j]==0)
			return true;
		return false;
	}
	static public int find_square(int i,int j){
		int r = i/3, c =j/3;
		if(r==0){
			if(c==0)return 0;
			else if(c==1)return 1;
			else return 2;
		}
		else if(r==1){
			if(c==0)return 3;
			else if(c==1)return 4;
			else return 5;
		}
		else {
			if(c==0)return 6;
			else if(c==1)return 7;
			else return 8;
		}
	}
	
	static public void rec(int row,int col){
		if(flag)return;
		if(col==9){
			rec(row+1,0);
			if(flag)return;
		}
		if(row==9){
			flag=true;
			for(int i=0;i<9;i++)
				for(int j=0;j<9;j++)
					res[i][j]=grid[i][j];
			return;
		}
		
		if(grid[row][col]==0){
		int square = find_square(row,col);
		for(int j=0;j<4;j++){
			int r = row+dx[j],c = col+dy[j];
			if(check(r,c)){
				int square2 = find_square(r,c);
				for(int i=1;i<=9;i++){
					if((rows[row]&(1<<i))==0 && (cols[col]&(1<<i))==0 && (squares[square]&(1<<i))==0){
						for(int k=1;k<=9;k++){
							if(i!=k && (rows[r]&(1<<k))==0 && (cols[c]&(1<<k))==0 && (squares[square2]&(1<<k))==0 && (dominos[i]&(1<<k))==0){
								rows[row]|=(1<<i);
								rows[r]|=(1<<k);
								cols[col]|=(1<<i);
								cols[c]|=(1<<k);
								squares[square]|=(1<<i);
								squares[square2]|=(1<<k);
								dominos[i]|=(1<<k);
								dominos[k]|=(1<<i);
								grid[row][col]=i;
								grid[r][c]=k;
								
								rec(row,col+1);
								if(flag)return ;
								rows[row]&=~(1<<i);
								rows[r]&=~(1<<k);
								cols[col]&=~(1<<i);
								cols[c]&=~(1<<k);
								squares[square]&=~(1<<i);
								squares[square2]&=~(1<<k);
								dominos[i]&=~(1<<k);
								dominos[k]&=~(1<<i);
								grid[row][col]=0;
								grid[r][c]=0;
							}//bit condns 2
						}// choosing 2nd number
					}// bit condns
				}// choosing first number
			}//end if check(r,c)
		}//end of dx,dy
		}
		else{
			rec(row,col+1);
			if(flag)return;
		}
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		String[] in ;
		int cnt = 0;
		while(true){
			int n = Integer.parseInt(br.readLine());
			if(n==0)break;
			///initialize
			for(int i=0;i<9;i++)
				Arrays.fill(grid[i],0);
			for(int i=0;i<10;i++){
				rows[i]=0;cols[i]=0;squares[i]=0;dominos[i]=0;
			}
			flag=false;
			////
			for(int i=0;i<n;i++){
				in = br.readLine().split(" ");
				int r = in[1].charAt(0)-'A';
				int c = Integer.parseInt(in[1].charAt(1)+"")-1;
				
				grid[r][c]=Integer.parseInt(in[0]);
				rows[r]|=(1<<Integer.parseInt(in[0]));
				cols[c]|=(1<<Integer.parseInt(in[0]));
				squares[find_square(r,c)]|=(1<<Integer.parseInt(in[0]));
			
				r = in[3].charAt(0)-'A';
				c = Integer.parseInt(in[3].charAt(1)+"")-1;

				grid[r][c]=Integer.parseInt(in[2]);
				rows[r]|=(1<<Integer.parseInt(in[2]));
				cols[c]|=(1<<Integer.parseInt(in[2]));
				squares[find_square(r,c)]|=(1<<Integer.parseInt(in[2]));
			
				dominos[Integer.parseInt(in[0])]|=(1<<Integer.parseInt(in[2]));
				dominos[Integer.parseInt(in[2])]|=(1<<Integer.parseInt(in[0]));
			}
			in = br.readLine().split(" ");
			for(int i=0;i<9;i++){
				int r = in[i].charAt(0)-'A';
				int c = Integer.parseInt(in[i].charAt(1)+"")-1;
				grid[r][c]=i+1;
				rows[r]|=(1<<(i+1));
				cols[c]|=(1<<(i+1));
				squares[find_square(r,c)]|=(1<<(i+1));
			}
			///reading
			rec(0,0);
			cnt++;
			System.out.println("Puzzle "+cnt);
			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++)
					System.out.print(res[i][j]);
				System.out.println();
			}
			//output
		}
	}
}
