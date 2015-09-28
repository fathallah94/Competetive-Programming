import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


class Main {
	static int n,m;
	static long[][] memo;
	static long rec(int n,int m){
		if(n<m )return 0;
		if(m==0 )return 1;
		if(memo[n][m]!=-1)
			return memo[n][m];
		return memo[n][m]=rec(n-1,m)+rec(n-1,m-1);
	}
	
	static public void main(String[] args)throws Exception{
		Scanner s=new Scanner(System.in);
		while(true){
			n=s.nextInt();
			m=s.nextInt();
			if(n==0 && m==0)break;
			memo=new long[n+1][m+1];
			for(int i=0;i<n+1;i++)
				for(int j=0;j<m+1;j++)
					memo[i][j]=-1;
			System.out.println(n+" things taken "+m+" at a time is "+rec(n,m)+" exactly.");
		}
		s.close();
	}
}
