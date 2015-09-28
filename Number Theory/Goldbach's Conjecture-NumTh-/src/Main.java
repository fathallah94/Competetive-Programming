import java.util.Arrays;
import java.util.Scanner;

//both I & II problems
class Main {
	static public void main(String[] args){
		Scanner s=new Scanner(System.in);
		int n,primesCounter=0;
		int[] primes=new int[1000005];
		boolean[] isPrime=new boolean[1000005];
		Arrays.fill(isPrime,true);
		
		isPrime[0]=isPrime[1]=false;
		
		for(int i=2;i*i<=1000001;i++){
			if(isPrime[i]){
				for(int j=i*i;j<=1000001;j+=i)
					isPrime[j]=false;
			}
		}
		for(int i=2;i<1000001;i++){
			if(isPrime[i])
				primes[primesCounter++]=i;
		}		
		boolean[] v=new boolean[primesCounter];
		
		while(true){
			n=s.nextInt();
			if(n==0)break;
			int pairs=0;
			Arrays.fill(v,false);
			for(int i=0;primes[i]<=n;i++){
				if(isPrime[n-primes[i]] && !v[primes[i]]){
					pairs++;
					v[primes[i]]=true;
					v[n-primes[i]]=true;
				}
			}
			System.out.println(pairs);
			
		}
		s.close();
	}
}
