import java.util.Arrays;
import java.util.Scanner;


class Main {
	static int a,b;
	static boolean[] isPrime=new boolean[5000005];
	static int primesCounter=0;
	static long pFactors[]=new long[5000005];
	
	static boolean check(int num){
		long sum=0;

		while(num!=1){
			sum+=pFactors[num];
			long temp=pFactors[num];
			num/=pFactors[num];

			while(pFactors[num]==temp)
				num/=pFactors[num];
		}
		if(isPrime[(int)sum])return true;
		return false;
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
		boolean ch[]=new boolean[5000005];
		for(int i=2;i<=5000000;i++){
			if(isPrime[i]){
				ch[i]=true;
				continue;
			}
			if(check(i))ch[i]=true;
		}
		
		Scanner s=new Scanner(System.in);
		while(true){
			a=s.nextInt();
			if(a==0)break;
			b=s.nextInt();
			int counter=0;
			for(int i=a;i<=b;i++){
				if(ch[i])counter++;
			}
			System.out.println(counter);
		}
		s.close();
	}
}
