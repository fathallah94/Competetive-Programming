import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//NOT SOLVED YET

class Main {
	
	static long limit=(long) Math.pow(2,64);

	static public void main(String[] args){

		long[] primes=new long[100005];
		boolean[] isPrime=new boolean[100005];
		int primesCounter=0;
		Arrays.fill(isPrime, true);
		isPrime[0]=isPrime[1]=false;
		for(int i=2;i*i<100005;i++){
			if(isPrime[i]){
				for(int j=i*i;j<100005;j+=i)
					isPrime[j]=false;
			}
		}
		for(int i=0;i<100005;i++){
			if(isPrime[i])
				primes[primesCounter++]=i;
		}
		PriorityQueue<Long> q=new PriorityQueue<Long>();
		for(int i=0;i<primesCounter;i++){
			//int limit=(int) Math.ceil(64/Math.log10(primes[i])*Math.log10(2.0));
			for(int j=4;j<=64;j++){
				if(!isPrime[j]){
					long res=(long)(Math.pow(primes[i], j));
					if(res<=0 || res>=limit){
						break;
					}
					q.add(res);
					for(int k=i+1;k<primesCounter;k++){
						res=(long)(Math.pow(primes[i], j)*Math.pow(primes[k], j));
						if(res<=0 || res>=limit){
							break;
						}
						q.add(res);
					}
				}
			}
		}
		
		long last=1,num=0;
		while(!q.isEmpty()){
			
			while(!q.isEmpty()){
				if(q.peek()==last)
					last=q.remove();
				else{
					System.out.println(last);
					num++;
					last=q.remove();
					break;
				}
			}
		}
		System.out.println("***"+num+"**"+primesCounter);
	}
}
