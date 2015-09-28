import java.util.Arrays;
import java.util.Scanner;


class Main {
	static public void main(String[] args){
		int primes[]=new int[1000005];
		boolean[] isPrime=new boolean[1000005];
		Arrays.fill(isPrime, true);
		isPrime[0]=isPrime[1]=false;
		for(int i=2;i*i<=1000003;i++){
			if(isPrime[i]){
				for(int j=i*i;j<=1000003;j+=i)
					isPrime[j]=false;
			}
		}
		int primesCounter=0;
		for(int i=0;i<=1000003;i++){
			if(isPrime[i])
				primes[primesCounter++]=i;
		}
		
		Scanner s=new Scanner(System.in);
		int n;
		while(true){
			n=s.nextInt();
			if(n==0)break;
			int counter=0,temp=n;
			for(int i=0;primes[i]<=temp;i++){
				if(temp%primes[i]==0)counter++;
				while(temp%primes[i]==0)
					temp/=primes[i];
			}
			if(temp!=1)
				counter++;
			System.out.println(n+" : "+counter);
		}
		s.close();
	}
}
