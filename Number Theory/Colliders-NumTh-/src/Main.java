import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
	static int n,m;
	
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in=br.readLine().split(" ");
		n=Integer.parseInt(in[0]);
		m=Integer.parseInt(in[1]);
		int[] primes=new int[1000001];
		int primesCounter=0;
		boolean[] isPrime=new boolean[1000001];
		Arrays.fill(isPrime, true);
		isPrime[0]=isPrime[1]=false;
		
		for(int i=2;i*i<=1000000;i++){
			if(isPrime[i]){
				for(int j=i*i;j<=1000000;j+=i)
					isPrime[j]=false;
			}
		}
		for(int i=0;i<1000001;i++){
			if(isPrime[i])
				primes[primesCounter++]=i;
		}
		
		boolean[][] factors=new boolean[n+1][400];
		int[] pfactor=new int[n+1];
		
		for(int i=1;i<=n;i++){
			int index=0,temp=i;
			int pf=primes[index++];
			while(pf*pf<=temp && temp!=1){
				while(temp%pf==0){
					factors[i][pf]=true;
					temp/=pf;
				}
				pf=primes[index++];
			}
			if(temp!=1 && temp>400)pfactor[i]=temp;
			if(temp!=1 && temp<400)factors[i][temp]=true;
			
		}
		boolean[] open=new boolean[n+1];
		int[] currentfactors=new int[n+1];
		
		for(int i=0;i<m;i++){
			in=br.readLine().split(" ");
			char sign=in[0].charAt(0);
			int c=Integer.parseInt(in[1]);
			if(sign=='+' && open[c]){
				System.out.println("Already on");
				continue;
			}
			else if(sign=='-' && !open[c]){
				System.out.println("Already off");
				continue;
			}
			else if(sign=='-' && open[c]){
				System.out.println("Success");
				open[c]=false;
				continue;
			}
			boolean flag=true;
			int conflict=-1;
			for(int j=0;j<400;j++){
				if(factors[c][j] && currentfactors[j]!=0 && open[currentfactors[j]]){
					flag=false;
					conflict=currentfactors[j];
					break;
				}
			}
			if(currentfactors[pfactor[c]]!=0 && pfactor[c]!=0 && open[currentfactors[pfactor[c]]]){
				conflict=currentfactors[pfactor[c]];
				flag=false;
			}
			
			if(!flag){
				System.out.println("Conflict with "+conflict);
				continue;
			}
			for(int j=0;j<400;j++){
				if(factors[c][j])
					currentfactors[j]=c;
			}
			if(pfactor[c]!=0)
				currentfactors[pfactor[c]]=c;
			open[c]=true;
			System.out.println("Success");
		}
		br.close();
	}
}
