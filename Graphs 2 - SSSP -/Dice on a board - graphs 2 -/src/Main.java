import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	static boolean inf = false , imp = false;
	static long res;
	static int n,m,si,sj,ti,tj;
	static String start ;
	static char[][] grid = new char[10][10];
	static long[][][][][] dist = new long[10][10][7][7][7];
	static int[] opp = new int[7];
	static int[]dx = {-1,1,0,0};
	static int[]dy = {0,0,-1,1};
	///////////////// n,s,w,e//////////////////
	static class node{
		int i,j,b,r,f;
		long dist;
		public node(int a,int b,int x,int y,int z,long d){
			i=a;j=b;this.b=x;r=y;f=z;this.dist=d;
		}
	}
	static class comp implements Comparator<node>{
		public int compare(node n1, node n2) {
			if(n1.dist<n2.dist)return 1;
			else if(n2.dist<n1.dist)return -1;
			return 0;
		}
	}
	static Comparator<node> compa = new comp();
	static PriorityQueue<node> q = new PriorityQueue<node>(7*7*7*100+5,compa);

	static void init(){
		inf=false;imp=false;
		q.clear();
		for(int i=0;i<10;i++)
			for(int j=0;j<10;j++)
				for(int k=0;k<7;k++)
					for(int w=0;w<7;w++)
						for(int p=0;p<7;p++){
							dist[i][j][k][w][p]=Long.MIN_VALUE;
						}
	}
	static boolean check(int i,int j){
		return i>=0 && i<n && j>=0 && j<m && grid[i][j]!='.' && grid[i][j]!='S';
	}
	static void dijkstra(){
		init();
		q.add(new node(si,sj,start.charAt(5)-'0',start.charAt(0)-'0',start.charAt(2)-'0',0));
		dist[si][sj][start.charAt(5)-'0'][start.charAt(0)-'0'][start.charAt(2)-'0']=0;
		int cnt = 0;
		while(!q.isEmpty()){
			node n = q.poll();
			cnt++;
			if(cnt==100000){
				inf=true;return;
			}
			if(dist[n.i][n.j][n.b][n.r][n.f] > n.dist)continue;
			for(int i=0;i<4;i++){
				int x= n.i+dx[i] , y = dy[i]+n.j;
				if(!check(x,y))continue;
				//north
				if(i==0){
					if(n.f==grid[x][y]-'0' && n.b==grid[n.i][n.j]-'0'){
						inf=true;
						return ;
					}
					if(n.f==grid[x][y]-'0' && 2*n.f+n.dist>dist[x][y][n.f][n.r][opp[n.b]]){
						dist[x][y][n.f][n.r][opp[n.b]] = n.dist + n.f*2;
						q.add(new node(x,y,n.f,n.r,opp[n.b],n.dist+n.f*2));
					}
					else if(grid[x][y]!='T' && n.f!=grid[x][y]-'0' && n.dist-(n.f+grid[x][y]-'0')>dist[x][y][n.f][n.r][opp[n.b]]){
						dist[x][y][n.f][n.r][opp[n.b]] = n.dist - n.f - (grid[x][y]-'0');
						q.add(new node(x,y,n.f,n.r,opp[n.b],n.dist-n.f-(grid[x][y]-'0')));
					}
					else if(grid[x][y]=='T')
						dist[ti][tj][0][0][0] = Math.max(dist[ti][tj][0][0][0],n.dist);
				}
				// south
				else if(i==1){
					if(opp[n.f]==grid[x][y]-'0' && grid[n.i][n.j]-'0'==n.b){
						inf=true;
						return ;
					}
					if(opp[n.f]==grid[x][y]-'0' && opp[n.f]*2 + n.dist > dist[x][y][opp[n.f]][n.r][n.b]){
						dist[x][y][opp[n.f]][n.r][n.b] = opp[n.f]*2 + n.dist;
						q.add(new node(x,y,opp[n.f],n.r,n.b,opp[n.f]*2 + n.dist));
					}
					else if(grid[x][y]!='T' &&opp[n.f]!=grid[x][y]-'0' && n.dist - opp[n.f] - (grid[x][y]-'0') >  dist[x][y][opp[n.f]][n.r][n.b]){
						 dist[x][y][opp[n.f]][n.r][n.b] =  n.dist - opp[n.f] - (grid[x][y]-'0');
						 q.add(new node(x,y,opp[n.f],n.r,n.b, n.dist - opp[n.f] - (grid[x][y]-'0')));
					}
					else if(grid[x][y]=='T')
						dist[ti][tj][0][0][0] = Math.max(dist[ti][tj][0][0][0],n.dist);
				}
				// west
				else if(i==2){
					if(opp[n.r]==grid[x][y]-'0' && n.b == grid[n.i][n.j]-'0'){
						inf=true;
						return ;
					}
					if(opp[n.r]==grid[x][y]-'0' && opp[n.r]*2+n.dist > dist[x][y][opp[n.r]][n.b][n.f]){
						dist[x][y][opp[n.r]][n.b][n.f]=opp[n.r]*2+n.dist;
						q.add(new node(x,y,opp[n.r],n.b,n.f,opp[n.r]*2+n.dist));
					}
					else if(grid[x][y]!='T' &&opp[n.r]!=grid[x][y]-'0' && n.dist-opp[n.r]-(grid[x][y]-'0') > dist[x][y][opp[n.r]][n.b][n.f]){
						dist[x][y][opp[n.r]][n.b][n.f] = n.dist-opp[n.r]-(grid[x][y]-'0');
						q.add(new node(x,y,opp[n.r],n.b,n.f,n.dist-opp[n.r]-(grid[x][y]-'0')));
					}
					else if(grid[x][y]=='T')
						dist[ti][tj][0][0][0] = Math.max(dist[ti][tj][0][0][0],n.dist);
				}
				//east 
				else if(i==3){
					if(n.r==grid[x][y]-'0' && n.b==grid[n.i][n.j]-'0'){
						inf=true;
						return ;
					}
					if(n.r==grid[x][y]-'0' && n.r*2+n.dist > dist[x][y][n.r][opp[n.b]][n.f]){
						dist[x][y][n.r][opp[n.b]][n.f] =  n.r*2+n.dist;
						q.add(new node(x,y,n.r,opp[n.b],n.f, n.r*2+n.dist));
					}
					else if(grid[x][y]!='T' &&n.r!=grid[x][y]-'0' && n.dist-n.r-(grid[x][y]-'0') > dist[x][y][n.r][opp[n.b]][n.f]){
						dist[x][y][n.r][opp[n.b]][n.f] =  n.dist-n.r-(grid[x][y]-'0');
						q.add(new node(x,y,n.r,opp[n.b],n.f, n.dist-n.r-(grid[x][y]-'0')));
					}
					else if(grid[x][y]=='T')
						dist[ti][tj][0][0][0] = Math.max(dist[ti][tj][0][0][0],n.dist);
				}
			}
		}
		if(dist[ti][tj][0][0][0]==Long.MIN_VALUE){
			imp=true;
			return ;
		}
		res=dist[ti][tj][0][0][0];
	}
	
	static void dfs(int i,int j,boolean[][]vi){
		vi[i][j]=true;
		for(int w=0;w<4;w++){
			if(check(dx[w]+i,dy[w]+j) && !vi[dx[w]+i][dy[w]+j])
				dfs(dx[w]+i,dy[w]+j,vi);
		}
	}
	static boolean check_imp(){
		boolean[][] vi = new boolean[n][m];
		dfs(si,sj,vi);
		return vi[ti][tj];
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int cases = Integer.parseInt(br.readLine().trim());
		String[]in ;
		char[] temp;
		for(int z=1;z<=cases;z++){
			in = br.readLine().split("\\s+");
			n = Integer.parseInt(in[0]);
			m = Integer.parseInt(in[1]);
			start = br.readLine().trim();
			opp[start.charAt(0)-'0']=start.charAt(1)-'0';
			opp[start.charAt(1)-'0']=start.charAt(0)-'0';

			opp[start.charAt(2)-'0']=start.charAt(3)-'0';
			opp[start.charAt(3)-'0']=start.charAt(2)-'0';

			opp[start.charAt(4)-'0']=start.charAt(5)-'0';
			opp[start.charAt(5)-'0']=start.charAt(4)-'0';
			for(int i=0;i<n;i++){
				temp = br.readLine().trim().toCharArray();
				for(int j=0;j<m;j++){
					grid[i][j]=temp[j];
					if(grid[i][j]=='S'){
						si=i;sj=j;
					}
					else if(grid[i][j]=='T'){
						ti=i;tj=j;
					}
				}
			}
			imp = !check_imp();
			if(imp)
				System.out.println("Impossible");
			else{
				dijkstra();
				if(inf)
					System.out.println("Infinity");
				
				else
					System.out.println(res);
			}
			
		}
	}
}	
