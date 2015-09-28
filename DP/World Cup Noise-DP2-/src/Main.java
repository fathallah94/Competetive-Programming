import java.io.BufferedReader;
import java.io.InputStreamReader;


class Main {
	static int n;
	static long[][] memo=new long[55][3];
	static long rec(int i,int last){
		if(i==n)return 1;
		
		if(memo[i][last]!=-1)
			return memo[i][last];
		
		if(last==1)
			return memo[i][last]=rec(i+1,0);
		else
			return memo[i][last]=rec(i+1,1)+rec(i+1,0);
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int cases=Integer.parseInt(br.readLine());
		
		for(int z=1;z<=cases;z++){
			for(int i=0;i<55;i++)
				for(int j=0;j<3;j++)
					memo[i][j]=-1;
			n=Integer.parseInt(br.readLine());
			System.out.println("Scenario #"+z+":");
			System.out.println(rec(0,2));
			System.out.println();
		}
		br.close();
	}
}
