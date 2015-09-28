import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int n;
	static long[][] arrs ;
	static long[][]memo ;
	static String[][] s;
	
	static long rec(int i,int j){
		if(i==j){
			s[i][j]="A"+(i+1);
			return 0;
		}
		if(memo[i][j]!=-1)return memo[i][j];
		
		long res = 10000000000000000L;
		for(int k=i;k<j;k++){
			long now = rec(i,k)+rec(k+1,j)+arrs[i][0]*arrs[k][1]*arrs[j][1];
			if(res>now){
				res = now;
				s[i][j] = "("+s[i][k]+" x "+s[k+1][j]+")";
			}
		}
		return memo[i][j]=res;
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		int cases = 0;
		while(true){
			n = Integer.parseInt(br.readLine().trim());
			if(n==0)break;
			arrs = new long[n][2];
			memo = new long[n+1][n+1];
			s = new String[n+1][n+1];
			for(int i=0;i<n;i++){
				in = br.readLine().split("\\s+");
				arrs[i][0] = Long.parseLong(in[0]);
				arrs[i][1]=Long.parseLong(in[1]);
				Arrays.fill(memo[i],-1);
			}
			rec(0,n-1);
			cases++;
			if(n==1)
				System.out.println("Case "+cases+": "+"("+s[0][n-1]+")");
			else
				System.out.println("Case "+cases+": "+s[0][n-1]);

		}
	}
}
