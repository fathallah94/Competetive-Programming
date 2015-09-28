import java.util.Arrays;
import java.util.Scanner;


class Main {
	static int x;
	static int[][] memo=new int[1005][11],winds=new int[11][1005];
	
	static int minCost(int altitude,int i){
		if(i==x && altitude!=0)
			return 1<<30;
		if(altitude==0 && i!=x&& i!=0)
			return 1<<30;
		if(i==x && altitude==0)
			return 0;
		if(altitude >=10 || altitude<0 )
			return 1<<30;
		if(memo[i][altitude]!=-1)
			return memo[i][altitude];
		return memo[i][altitude]=Math.min(60-winds[altitude][i]+minCost(altitude+1,i+1),Math.min(30-winds[altitude][i]+minCost(altitude,i+1), 20-winds[altitude][i]+minCost(altitude-1,i+1)));
		
	}
	
	static public void main(String[] args){
		Scanner s=new Scanner(System.in);
		int T=s.nextInt();
		for(int z=0;z<T;z++){
			x=s.nextInt();
			x/=100;
			for(int i=9;i>-1;i--){
				for(int j=0;j<x;j++)
					winds[i][j]=s.nextInt();
			}
			for(int i=0;i<1005;i++)
				for(int j=0;j<11;j++)
					memo[i][j]=-1;
			
			System.out.println(minCost(0,0));
			System.out.println();
		}
		s.close();
	}
	
}
