import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int n,m,mod=1000000007;
	static int[][][] memo = new int[105][11][(1<<11)];
	
	static int rec(int ind,int last,int mask){
		if(ind>m){
			return 0;
		}
		if(ind==m && mask==((1<<n)-1)){
			return 1;
		}
	
		if(memo[ind][last][mask]!=-1)
			return memo[ind][last][mask];
		int ways = 0;
		if(ind<m && mask==((1<<n)-1)){
			ways++;ways%=mod;
		}
		if(last+1>=0 && last+1<n){			
			ways = (ways%mod + rec(ind+1,last+1,(mask|(1<<(last+1))))%mod)%mod;
		}
		if(last-1>=0 && last-1<n){
			ways = (ways%mod + rec(ind+1,last-1,(mask|(1<<(last-1))))%mod)%mod;
		}
		return memo[ind][last][mask] = ways;
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		for(int z=1;z<=t;z++){
			String[]in = br.readLine().split("\\s+");
			n = Integer.parseInt(in[0]);m=Integer.parseInt(in[1]);
			int res = 0;
			for(int i=0;i<105;i++){
				for(int j=0;j<11;j++){
					for(int k=0;k<(1<<11);k++)
						memo[i][j][k]=-1;
				}
			}
			for(int i=1;i<n;i++){
				res = (res%mod + rec(1,i,(1<<i))%mod)%mod;
			}
			System.out.println(res);
		}
	}
}
