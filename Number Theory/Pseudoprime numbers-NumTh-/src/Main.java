import java.io.BufferedReader;
import java.io.InputStreamReader;


class Main {
	static int p,a;
	
	static long pow(int base,int power){
		if(power==0)return 1;
		
		long res=pow(base,power/2);
		
		if(power%2==0) return ((res%p)*(res%p))%p;
		else return ((((res%p)*(res%p))%p)*(base%p))%p;
	}
	
	static boolean isPrime(int num){
		boolean flag=true;
		for(int i=2;i<num/i;i++){
			if(num%i==0){
				flag=false;
				break;
			}
		}
		return flag;
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		while(true){
			String[] in=br.readLine().split(" ");
			p=Integer.parseInt(in[0]);
			a=Integer.parseInt(in[1]);
			if(p==0 && a==0)break;
			long res=pow(a,p);
			if(res==a && !isPrime(p))
				System.out.println("yes");
			else
				System.out.println("no");
		}
		br.close();
	}
}
