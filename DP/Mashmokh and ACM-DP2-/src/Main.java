import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Main {
	static int n,k;
	static int[][] memo;
	
	static int rec(int i,int last){
		if(i==k)return 1;
		if(i>=k)return 0;
		
		if(memo[i][last]!=-1)
			return memo[i][last]%1000000007 ;
		
		int ways=0;
		for(int j=last;j<=n;j+=last){
			ways=(ways%1000000007+rec(i+1,j)%1000000007)%1000000007;
		}
		return memo[i][last]=ways%1000000007;
	
	}
	
	static public void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in=br.readLine().split(" ");
		n=Integer.parseInt(in[0]);
		k=Integer.parseInt(in[1]);
		
		memo=new int[k+1][n+1];
		
		for(int i=0;i<k+1;i++)
			for(int j=0;j<n+1;j++)
					memo[i][j]=-1;
		

		System.out.println(rec(0,1));
		br.close();
	}
}
