import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static public void main(String[] ags)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[] arr = new long[1000000+10];
		for(int i=1000000;i>3;i--){
			long start = i-1, end = (i+1)/2 +1;
			long sum = (start+end)*(start-end+1);

			sum-=i*(start-end+1);
			sum-=start-end+1;
			if(sum>0)
				arr[i]=sum;
		}
		for(int i=1;i<=1000000;i++)
			arr[i]+=arr[i-1];
		
		while(true){
			int n = Integer.parseInt(br.readLine());
			if(n<3)break;
			System.out.println(arr[n]);
		}
	}
}
