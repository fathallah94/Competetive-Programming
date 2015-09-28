import java.io.BufferedReader;
import java.io.InputStreamReader;


class Main {
	static int length,n;
	static int[] cuts=new int[55];
	static int[][] memo=new int[55][55];
	
	static int rec(int l,int r){
		if(r-l==1)
			return 0;
	
		if(memo[l][r]!=-1)
			return memo[l][r];
		
		int cost=(1<<30);
		for(int j=l+1;j<r;j++){
			cost= Math.min(cost, (cuts[r]-cuts[l]) + rec(l,j) + rec(j,r));
		}
		return memo[l][r]=cost;
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		while(true){
			length=Integer.parseInt(br.readLine());
			if(length==0)break;
			n=Integer.parseInt(br.readLine());
			String[] in = br.readLine().split(" ");
			cuts[0]=0;
			for(int i=0;i<n;i++){
				cuts[i+1]=Integer.parseInt(in[i]);
			}
			cuts[n+1]=length;
			for(int i=0;i<55;i++)
				for(int j=0;j<55;j++)
					memo[i][j]=-1;
			
			System.out.println("The minimum cutting is "+rec(0,n+1)+".");
			
		}
	}
}
