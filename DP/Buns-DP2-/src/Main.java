import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
	static int n,m,c0,d0;
	static int[][] arr;
	static int[][][] memo;
	static public int rec(int remdough,int remstuff,int last){
		if(remdough<=0)return 0;
		if(last==m)return 0;
		
		if(memo[remdough][remstuff][last]!=-1)
			return memo[remdough][remstuff][last];
		
		if(remstuff>=arr[last][1] && remdough>=arr[last][2] && remdough>=c0)
			return memo[remdough][remstuff][last]=Math.max( d0+rec(remdough-c0,remstuff,last) ,Math.max( arr[last][3]+rec(remdough-arr[last][2],remstuff-arr[last][1],last) ,rec(remdough,arr[last+1][0],last+1)) );
		else if(remstuff>=arr[last][1] && remdough>=arr[last][2])
			return memo[remdough][remstuff][last]=Math.max( arr[last][3]+rec(remdough-arr[last][2],remstuff-arr[last][1],last) ,rec(remdough,arr[last+1][0],last+1));
		else if(remdough>=c0)
			return memo[remdough][remstuff][last]=Math.max(d0+rec(remdough-c0,remstuff,last), rec(remdough,arr[last+1][0],last+1));
		else
			return memo[remdough][remstuff][last]=rec(remdough,arr[last+1][0],last+1);
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in=br.readLine().split(" ");
		n=Integer.parseInt(in[0]);
		m=Integer.parseInt(in[1]);
		c0=Integer.parseInt(in[2]);
		d0=Integer.parseInt(in[3]);
		arr=new int[m+1][5];
		int max=-1;
		for(int i=0;i<m;i++){
			in=br.readLine().split(" ");
			arr[i][0]=Integer.parseInt(in[0]);
			arr[i][1]=Integer.parseInt(in[1]);
			arr[i][2]=Integer.parseInt(in[2]);
			arr[i][3]=Integer.parseInt(in[3]);
			if(arr[i][0]>max)max=arr[i][0];
		}
		memo=new int[n+1][max+1][m+1];
		for(int i=0;i<n+1;i++)
			for(int j=0;j<max+1;j++)
				for(int k=0;k<m+1;k++)
					memo[i][j][k]=-1;
		System.out.println(rec(n,arr[0][0],0));
		br.close();
	}
}
