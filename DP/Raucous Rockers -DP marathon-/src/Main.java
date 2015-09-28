import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	static int n,T,m;
	static int[] songs;
	static int[][][] memo;
	
	static int rec(int last,int rem,int cds){
		
		if(cds==m || last==n )
			return 0;
		
		if(memo[last][rem][cds]!=-1)
			return memo[last][rem][cds];
		
		if(rem>=songs[last])
			return memo[last][rem][cds]= Math.max(1+rec(last+1,rem-songs[last],cds),rec(last+1,rem,cds)) ;
				
		else
			return memo[last][rem][cds]=Math.max( rec(last+1,rem,cds) , rec(last,T,cds+1)  );
	
	}
	
	
	static public void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int cases=Integer.parseInt(br.readLine());
		for(int z=1;z<=cases;z++){
			
			br.readLine();
			String[] in=br.readLine().split(" ");
			n=Integer.parseInt(in[0]);
			T=Integer.parseInt(in[1]);
			m=Integer.parseInt(in[2]);
			in=br.readLine().split(", ");
			songs=new int[n+1];
			for(int i=0;i<n;i++){
				songs[i]=Integer.parseInt(in[i]);
			}
			memo=new int[n+5][T+5][m+5];
			for(int i=0;i<n+5;i++)
				for(int j=0;j<T+5;j++)
					for(int k=0;k<m+5;k++)
						memo[i][j][k]=-1;
			
			System.out.println(rec(0,T,0));
			if(z<cases)
				System.out.println();
		}
		br.close();
	}
	
}
