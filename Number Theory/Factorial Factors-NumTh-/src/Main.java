import java.util.Arrays;
import java.util.Scanner;


class Main {
	static int n;
	static boolean[] isPrime=new boolean[5000005];
	static int primesCounter=0;
	static long pFactors[]=new long[5000005];
	
	static long check(int num){
		long sum=0;

		while(num!=1){
			sum++;
			num/=pFactors[num];
		}
		return sum;
	}
	
	static public void main(String[] args){
		
		Arrays.fill(isPrime,true);
		isPrime[0]=isPrime[1]=false;
		pFactors[1]=pFactors[0]=-1;
		for(int i=2;i*i<=5000003;i++){
			if(isPrime[i]){
				pFactors[i]=i;
				for(int j=i*i;j<=5000003;j+=i){
					isPrime[j]=false;
					if(i<pFactors[j] || pFactors[j]==0)
						pFactors[j]=i;
				}
			}
		}
		
		for(int i=0;i<=5000003;i++){
			if(isPrime[i]){
				pFactors[i]=i;
			}
		}
		long[] ret=new long[1000005];
		ret[2]=check(2);
		for(int i=3;i<=1000001;i++){
			ret[i]=check(i)+ret[i-1];
		}

		Scanner s=new Scanner(System.in);
		while(s.hasNext()){
			n=s.nextInt();
			long total=0;
			
			
			
			System.out.println(ret[n]);
		}
		s.close();
	}
}
