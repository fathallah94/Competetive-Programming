import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
	static boolean check(long num){
		if(num%2==0){
			if(num==2)return true;
			return false;
		}
		for(long i=3;i*i<=num;i+=2){
			if(num%i==0)return false;
		}
		return true;
	}
	static long gcd(long a,long b){
		if(b==0)return a;
		return gcd(b,a%b);
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int w=0;w<t;w++){
			long n = Integer.parseInt(br.readLine());
			long bef=0,aft=0;
			for(bef=n;!check(bef);bef--){}
			for(aft=n+1;!check(aft);aft++){}
			
//			If p and q are consecutive prime numbers then there are q - p each of them equal to 1/pq.
//			Notice that q-p/pq = 1/p - 1/q Then we sum all of them 1/p - 1/q + 1/q - 1/r = 1/p - 1/r
//			so our result = 1/2 - 1/after
//			but there are some extra elements added = (after-n+1)/(before*after)
//			so final answer = 1/2 - 1/after - (after-n+1)/(before*after)
			
			long den = 2*bef*aft;
			long num = bef*aft - 2*bef - 2*aft + 2*n +2;
			long g = gcd(num,den);
			num/=g;den/=g;
			System.out.println(num+"/"+den);
		}
	}
}
