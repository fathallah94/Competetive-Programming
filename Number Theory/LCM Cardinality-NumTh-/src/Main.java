import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	static long N;
	
	static long GCD(long x,long y){
		if(y==0)return x;
		if(x==0)return y;
		return GCD(y,x%y);
	}
	
	static long LCM(long x,long y){
		return (x/GCD(x,y))*y;
	}
	
	static public void main(String []args)throws Exception{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		while(true){
			N=Integer.parseInt(br.readLine());
			if(N==0)break;
			if(N==1){
				System.out.println("1 1");
				continue;
			}
			long[] divisors=new long[1000000];
			int divisorsCounter=0;
			boolean[] v=new boolean[1000005];
			
			for(long z=1;z*z<=N;z++){
				if(N%z==0 && !v[(int)z] && z*z!=N){
					divisors[divisorsCounter++]=z;
					divisors[divisorsCounter++]=N/z;
					v[(int)z]=true;
				}
				else if(N%z==0 && !v[(int)z] && z*z==N){
					divisors[divisorsCounter++]=z;
					v[(int)z]=true;
				}
			}

			

			int counter=0;
			for(int i=0;i<divisorsCounter;i++){
				for(int j=i;j<divisorsCounter;j++){
					long x=LCM(divisors[i],divisors[j]);
					if(x==N)
						counter++;
				}
			}
			System.out.println(N+" "+counter);
		}
		br.close();
	}
}
