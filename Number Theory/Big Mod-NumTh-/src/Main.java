import java.util.Scanner;


class Main {
	static int b,p,M;
	
	static long pow(int base,int power){
		if(power==0)return 1;
		
		long res=pow(base,power/2);
		
		if(power%2==0) return ((res%M)*(res%M))%M;
		else return ((((res%M)*(res%M))%M)*(base%M))%M;
	}
	
	public static void main(String[] args){
		Scanner s= new Scanner(System.in);
		while(s.hasNext()){
			b=s.nextInt();
			p=s.nextInt();
			M=s.nextInt();
			System.out.println(pow(b,p));
		}
		s.close();
	}
}
