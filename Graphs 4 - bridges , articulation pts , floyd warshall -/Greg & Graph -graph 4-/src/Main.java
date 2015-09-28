import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
	static int n;
	public static void main(String[] args)throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		long[][] dist=new long[n+5][n+5];
		String[] in;
		for(int i=1;i<=n;i++){
			in=br.readLine().split(" ");
			for(int j=1;j<=n;j++)
				dist[i][j]=Integer.parseInt(in[j-1]);
		}
		int[] removed=new int[n+5];
		in=br.readLine().split(" ");
		for(int i=0;i<n;i++)
			removed[i]=Integer.parseInt(in[i]);
		
		long[] answer=new long[n+5];
		boolean[] taken=new boolean[n+5];
		int counter=0;
		for(int k=1;k<=n;k++){
			long sum=0;
			taken[removed[n-k]]=true;
			for(int i=1;i<=n;i++){
				for(int j=1;j<=n;j++){
					dist[i][j]=Math.min(dist[i][j], dist[i][removed[n-k]]+dist[removed[n-k]][j]);
					if(taken[i] && taken[j]){
						sum+=dist[i][j];
					}
				}			
			}
			answer[counter++]=sum;
		}
		for(int i=n-1;i>0;i--)
			System.out.print(answer[i]+" ");
		
		System.out.println(answer[0]);
		br.close();
	}
}