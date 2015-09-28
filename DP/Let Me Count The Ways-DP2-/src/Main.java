import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


class Main {
	static int n;
	static int[] arr={1,5,10,25,50};
	static long[][] memo=new long[6][30000+5];
	
	static long rec(int index,int rem){
		if(rem==0)return 1;
		if(rem<0 )return 0;
		if(index==5)return 0;
		
		if(memo[index][rem]!=-1)
			return memo[index][rem];
		
		return memo[index][rem]=rec(index,rem-arr[index])+rec(index+1,rem);
	}
	
	static public void main(String[] args)throws Exception{
		Scanner s=new Scanner(System.in);
		for(int i=0;i<6;i++)
			for(int j=0;j<30005;j++)
				memo[i][j]=-1;
		int set[]={1000,5000,10000,20000,30000};
		long z;
		for(int i=0;i<5;i++)
			z=rec(0,set[i]);
		
		while(s.hasNext()){
			n=s.nextInt();
						
			long res=rec(0,n);
			if(res==1)
				System.out.println("There is only 1 way to produce "+n+" cents change.");
			else
				System.out.println("There are "+res+" ways to produce "+n+" cents change." );
		}
		s.close();
	}
}
