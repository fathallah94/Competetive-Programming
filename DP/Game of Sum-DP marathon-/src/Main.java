import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	static int n;
	static int[] arr;
	static int[][] memo;
	
	static int rec(int l,int r){
		if(l>r)return 0;
		
		if(memo[l][r]!=-(1<<30))
			return memo[l][r];
		
		int res = -(1<<30);
		for(int i=l;i<=r;i++){
			int sum=0;
			if(l==0)
				sum=arr[i];
			else
				sum=arr[i]-arr[l-1];
			res = Math.max(res,sum- rec(i+1,r));
		}
		for(int i=r;i>=l ;i--){
			int sum=0;
			if(i==0)
				sum=arr[r];
			else
				sum=arr[r]-arr[i-1];
			res = Math.max(res,sum-rec(l,i-1));
		}
		return memo[l][r]=res;
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		while(true){
			n=Integer.parseInt(br.readLine());
			if(n==0)break;
			String[] in=br.readLine().split(" ");
			arr=new int[n+5];
			for(int i=0;i<n;i++){
				arr[i]=Integer.parseInt(in[i]);
				if(i>0)
					arr[i]+=arr[i-1];
			}
			memo=new int[n+1][n+1];
			
			for(int i=0;i<n+1;i++)
				for(int j=0;j<n+1;j++)
					memo[i][j]=-(1<<30);
			
			System.out.println(rec(0,n-1) );
		}
		br.close();
	}
}
