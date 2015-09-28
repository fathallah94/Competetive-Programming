import java.util.Arrays;
import java.util.Scanner;


class Main {
	static public void main(String[] args){
		
		
		int[] sums=new int[2000];
		Arrays.fill(sums, -1);
		
		for(int i=1;i<=1001;i++){
			int sum=0;
			for(int j=1;j<=i;j++){
				if(i%j==0){
					sum+=j;
				}
			}
			if(sum<2000)
				sums[sum]=i;
		}
		
		Scanner s= new Scanner (System.in);
		int cases=1;
		while(true){
			int n=s.nextInt();
			if(n==0)break;
			System.out.println("Case "+cases+": "+sums[n]);
			cases++;
		}
		s.close();
	}
}
