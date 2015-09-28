import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


class Main {
	static int[] primes=new int[1000005];
	static boolean isPrime[]=new boolean[1000005];
	static long x,primesCounter=0;
		
	static long factorize(long num){
		long ans=0;
		int index=0;
		long pf=primes[index++];
		while(pf<=num/pf && num!=1){
			boolean flag=false;
			long power=0;
			while(num%pf==0){
				power++;
				num/=pf;
				flag=true;
			}
			if(flag){
				ans=GCD(ans,power);	
			}
			if(index>=primesCounter)break;
			pf=primes[index++];
		}
		
		if(num!=1){
			ans=GCD(ans,1L);
		}
		
		return ans;
	}
	
	static long GCD(long x,long y){
		if(y==0)return x;
		if(x==0)return y;
		
		return GCD(y,x%y);
	}
	
	static public void main(String[] args) throws  IOException{
		
		Arrays.fill(isPrime, true);
		isPrime[0]=isPrime[1]=false;
		for(int i=2;i*i<=1000004;i++){
			if(isPrime[i]){
				for(int j=i*i;j<=1000004;j+=i){
					isPrime[j]=false;
				}
			}
		}
		for(int i=0;i<1000005;i++){
			if(isPrime[i])
				primes[(int) primesCounter++]=i;
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while(true){
			
			x=Integer.parseInt(br.readLine());
			if(x==0)break;
			boolean neg=false;
			if(x<0){
				neg=true;
				x*=-1;
			}
						
			if(x<1000005 ){
				if(isPrime[(int) x]){
					System.out.println("1");
					continue;
				}
			}
			
			long res=factorize(x);
			
			
			if(neg){
				while(res%2==0)
					res/=2;
			}
			System.out.println(res);
		}
		br.close();
	}
}
