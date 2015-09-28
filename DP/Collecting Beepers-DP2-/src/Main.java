import java.io.BufferedReader;
import java.io.InputStreamReader;


class Main {
	static int X,Y,n;
	static int[][] indices=new int[20][3];
	static int[][] memo;
	
	static int rec(int beeper,int mask){
		if(mask==(1<<(n+1))-1)return memo[beeper][mask]=dist(0,beeper);
		
		if(memo[beeper][mask]!=-1)
			return memo[beeper][mask];
		int res=(1<<30);
		for(int i=1;i<=n;i++){
			if((mask&(1<<i))==0)
				res=Math.min(res, dist(i,beeper)+rec(i,(mask|(1<<i))));
		}
		return memo[beeper][mask]=res;
	}
	
	static int dist(int i,int beeper){
		int dx=Math.abs(indices[i][0]-indices[beeper][0]);
		int dy=Math.abs(indices[i][1]-indices[beeper][1]);
		return dx+dy;
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int cases=Integer.parseInt(br.readLine());
		for(int z=1;z<=cases;z++){
			String[] in=br.readLine().split(" ");
			X=Integer.parseInt(in[0]);
			Y=Integer.parseInt(in[1]);
			in=br.readLine().split(" ");
			indices[0][0]=Integer.parseInt(in[0]);
			indices[0][1]=Integer.parseInt(in[1]);
			n=Integer.parseInt(br.readLine());
			for(int i=1;i<=n;i++){
				in=br.readLine().split(" ");
				indices[i][0]=Integer.parseInt(in[0]);
				indices[i][1]=Integer.parseInt(in[1]);
			}
			memo=new int[n+1][(1<<(n+5))];
			for(int i=0;i<n+1;i++)
				for(int j=0;j<(1<<(n+5));j++)
					memo[i][j]=-1;
			System.out.println("The shortest path has length "+rec(0,1));
		}
		br.close();
	}
}
