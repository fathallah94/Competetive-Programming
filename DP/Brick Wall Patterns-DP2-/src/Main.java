import java.io.BufferedReader;
import java.io.InputStreamReader;


class Main {
	static int num;
	static int[] memo;
	static int rec(int n){
		if(n<=1)return 1;
		if(memo[n]!=-1)return memo[n];
		return memo[n]=rec(n-1)+rec(n-2);
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			num=Integer.parseInt(br.readLine());
			if(num==0)break;
			memo=new int[num+5];
			for(int i=0;i<num+5;i++)
				memo[i]=-1;
			System.out.println(rec(num));
		}
		br.close();
	}
}
