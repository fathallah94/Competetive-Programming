import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


class Main {
	static boolean isPrime[]=new boolean[1000005];
	static long[] pFactors=new long[1000005],primes=new long[1000005];
	static long n,d,primesCounter=0;
	
	static public void main(String[] args) throws IOException{
		
		Arrays.fill(isPrime,true);
		isPrime[0]=isPrime[1]=false;
		pFactors[0]=pFactors[1]=-1;
		
		for(int i=2;i*i<=1000003;i++){
			if(isPrime[i]){
				for(int j=i*i;j<=1000003;j+=i){
					isPrime[j]=false;
					if(i<pFactors[j] || pFactors[j]==0)
						pFactors[j]=i;
				}
			}
		}
		
		for(int i=0;i<=1000003;i++){
			if(isPrime[i]){
				pFactors[i]=i;
				primes[(int) primesCounter++]=i;
			}
		}
		int[][] Nfactors=new int[101][1000];
		for(int i=2;i<=100;i++){
			for(int q=2;q<=i;q++){
				long temp=q;
				while(temp!=1){
					Nfactors[i][(int) pFactors[(int) temp]]++;					
					temp/=pFactors[(int) temp];
				}
			}
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		while(true){
			in=br.readLine().split(" ");
			n=Integer.parseInt(in[0]);
			d=Integer.parseInt(in[1]);
			if(n==0 && d==0)break;
			if(d<0)d*=-1;
			int[] Dfactors=new int[1000];	
			
			long temp=d;
			int index=0;
			boolean flag=true;
			long pf=primes[index++];
			if((n==0 || n==1) && d!=1)flag=false;
			if((n==0 || n==1) && d==1){
				System.out.println("1");
				continue;
			}
			
			while(pf<=temp/pf && temp!=1){
				if(!flag)break;
				while(temp%pf==0){
					if(pf>=1000){
						flag=false;
						break;
					}
					Dfactors[(int) pf]++;
					temp/=pf;
				}
				if(index>=primesCounter)break;
				pf=primes[index++];
			}
			
			if(temp!=1 && temp<1000)Dfactors[(int) temp]++;
			if(temp>=1000)
				flag=false;

			long total=1;

			for(int i=0;i<1000;i++){
				if(!flag)break;
				if(Nfactors[(int)n][i]-Dfactors[i]>0)
					total*=(Nfactors[(int)n][i]-Dfactors[i]+1);
				else if(Nfactors[(int)n][i]-Dfactors[i]<0)
					flag=false;
			}
			
			
			
			if(flag)
				System.out.println(total);
			else 
				System.out.println("0");
		}
		br.close();
	}
}
