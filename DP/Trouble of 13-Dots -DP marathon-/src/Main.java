import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


class Main {
	static int budget,n;
	static int[][] items=new int[105][3];
	static int[][] memo= new int[22000][205];
;
	
	static int rec(int rem,int last ){
		if(last==n){
			if(rem<0 && budget-rem<=2000) return -(1<<30);
			if(rem<0 &&  budget-rem>2000 && rem+200<0)return -(1<<30);
			if(rem>=0)return 0;
			if(rem<0 && budget-rem>2000 && rem+200>=0)return 0;
		}
		
		if(rem<0 &&budget-rem>2000 && rem+200<0) return -(1<<30);

				
		
		if(memo[budget-rem][last]!=-1){
			return memo[budget-rem][last];
		}
		
		return memo[budget-rem][last] = Math.max(items[last][1]+rec(rem-items[last][0],last+1),rec(rem,last+1));
		
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		String in;
		while(true){
			in=br.readLine();
			if(in==null)break;
			
			String in2[]=in.split(" ");
			budget=Integer.parseInt(in2[0]);
			n=Integer.parseInt(in2[1]);
			
			for(int i=0;i<n;i++){
				in2=br.readLine().split(" ");
				items[i][0]=Integer.parseInt(in2[0]);
				items[i][1]=Integer.parseInt(in2[1]);
			}
			

			
			
			for(int i=0;i<22000;i++)
				for(int j=0;j<205;j++)
					memo[i][j]=-1;
						
			System.out.println(rec(budget,0));
		}
		br.close();
	}
}
