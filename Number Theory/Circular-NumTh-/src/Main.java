import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static boolean[] isPrime = new boolean[1000000+50];
	static ArrayList<Integer> circulars = new ArrayList<Integer>();
	
	static boolean check(int num){
		int temp1 = num,temp2,temp3;
		if(!isPrime[temp1])return false;
		temp2=temp1%10;temp1/=10;temp3=temp1;
		while(temp1!=0){
			temp2 = (temp2*10);
			temp1/=10;
		}
		temp2+=temp3;
		if(!isPrime[temp2])return false;
		while(temp1!=num && temp2!=num){
			temp1=temp2;
			temp2=temp1%10;temp1/=10;temp3=temp1;
			while(temp1!=0){
				temp2 = (temp2*10);
				temp1/=10;
			}
			temp2+=temp3;
			if(temp2==num)break;
			if(!isPrime[temp2])return false;
		}
		return true;
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Arrays.fill(isPrime,true);
		isPrime[0]=isPrime[1]=false;
		for(int i=2;i*i<=1000000+10;i++){
			if(!isPrime[i])continue;
			for(int j=i*i;j<=1000000+10;j+=i)
				isPrime[j]=false;
		}
		
		for(int i=2;i<=1000000;i++){
			if(check(i)){
				circulars.add(i);
			}
		}
		String[] in;
		StringBuilder out = new StringBuilder();
		while(true){
			in = br.readLine().split("\\s+");
			int a = Integer.parseInt(in[0]);
			if (a==-1)break;
			int b = Integer.parseInt(in[1]);
			int res=0;
			for(int i=0;i<circulars.size();i++){
				int now = circulars.get(i);
				if(now>=a && now<=b)
					res++;
			}
			if(res==0)
				out.append("No Circular Primes.\n");
			else if(res==1)
				out.append("1 Circular Prime.\n");
			else
				out.append(res+" Circular Primes.\n");
		}
		System.out.print(out);
	}
}
