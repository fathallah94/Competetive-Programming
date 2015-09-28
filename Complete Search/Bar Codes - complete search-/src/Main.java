import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int n,k,m;
	static long [][]memo = new long[55][55];
	
	static long rec(int ind,int sum){
		if((ind==k && sum!=n) || (sum>n) || (sum==n && ind!=k) )
			return 0;
		if(ind==k && sum==n)
			return 1;
		
		if(memo[ind][sum]!=-1)
			return memo[ind][sum];
		long res=0;
		for(int i=1;i<=m;i++)
			res+= rec(ind+1,sum+i);
			
		return memo[ind][sum]=res;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		String[]in ;
		while(br.ready()){
			in = br.readLine().split(" ");
			n = Integer.parseInt(in[0]);
			k = Integer.parseInt(in[1]);
			m = Integer.parseInt(in[2]);
			for(int i=0;i<55;i++)
				Arrays.fill(memo[i], -1);
			
			System.out.println(rec(0,0));
		}
	}
}
