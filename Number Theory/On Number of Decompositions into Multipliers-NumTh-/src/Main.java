import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
	static int n;
	
	static long[][] memo=new long[25000][600];
			
	static long c(long m,long k){
		if(m<k)return 0;
		if(k==0)return 1;
		if(memo[(int) m][(int) k]!=-1)
			return memo[(int) m][(int) k]%1000000007;
		
		return memo[(int) m][(int) k]=(c(m-1,k)%1000000007+c(m-1,k-1)%1000000007)%1000000007;
	}
	
	static public void main(String []args) throws NumberFormatException, IOException{
		for(int i=0;i<25000;i++)
			for(int j=0;j<600;j++)
				memo[i][j]=-1;
		
		int[]primes=new int[1000008];
		boolean[] isPrime=new boolean[1000008];
		int primesCounter=0;
		Arrays.fill(isPrime,true);
		isPrime[0]=isPrime[1]=false;
		for(int i=2;i*i<1000008;i++){
			if(isPrime[i]){
				for(int j=i*i;j<1000008;j+=i)
					isPrime[j]=false;
			}
		}
		for(int i=0;i<1000008;i++){
			if(isPrime[i])
				primes[primesCounter++]=i;
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		String[]in = br.readLine().split(" ");
		long[] pfactors=new long[32500];
		long[][] remaining=new long[100000][2];
		int remCounter=0;
		
		for(int i=0;i<n;i++){
			int num=Integer.parseInt(in[i]);
			int index=0;
			int pf=primes[index++];
			while(pf<=num/pf && num!=1){
				while(num%pf==0){
					pfactors[pf]++;
					num/=pf;
				}
				if(index>=primesCounter)break;
				pf=primes[index++];
			}
			if(num!=1 && num>32500){
				boolean flag=false;
				for(int j=0;j<remCounter;j++){
					if(num==remaining[j][0]){
						remaining[j][1]++;
						flag=true;
						break;
					}
				}
				if(!flag){
					remaining[remCounter][1]=1;
					remaining[remCounter++][0]=num;
				}	
			}
			else if(num!=1 && num<32500)pfactors[num]++;
		}
		
		long total=1;
		for(int i=0;i<32500;i++){
			if(pfactors[i]!=0){
				total=(total%1000000007 *c(n+pfactors[i]-1,n-1)%1000000007 )%1000000007;
			}
		}
		for(int i=0;i<remCounter;i++){
			total=(total%1000000007 *c(remaining[i][1]+n-1,n-1)%1000000007 )%1000000007;
		}
		System.out.println(total);
	}
}
