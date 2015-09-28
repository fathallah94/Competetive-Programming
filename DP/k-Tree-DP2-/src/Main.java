import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
	static int n,k,d;
	static int[][] memo;
	static int rec(int length,int flag){
		
		if(length==n && flag==0)return 0;
		if(length>n)return 0;
		if(length==n && flag!=0)return 1;
		
		
		int ways=0;		
		if(memo[length][flag]!=-1)
			return memo[length][flag]%1000000007;
		
		for(int j=1;j<=k;j++){
			if(j>=d)
				ways=(ways%1000000007+rec(length+j,flag+1)%1000000007)%1000000007;
			else 
				ways=(ways%1000000007+rec(length+j,flag)%1000000007)%1000000007;
		}
		return memo[length][flag]=(ways%1000000007);
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in=br.readLine().split(" ");
		n=Integer.parseInt(in[0]);
		k=Integer.parseInt(in[1]);
		d=Integer.parseInt(in[2]);
		memo=new int[2*n+k][n+1];
		for(int i=0;i<2*n+k;i++)
			for(int j=0;j<n+1;j++)
				memo[i][j]=-1;
		
		System.out.println(rec(0,0) );
	}
}
