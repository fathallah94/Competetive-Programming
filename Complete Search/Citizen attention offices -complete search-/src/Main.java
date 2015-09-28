import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static public boolean compare(int[] mincase , int[] cases){
		for(int i=0;i<5;i++){
			if(mincase[i]<cases[i])return false;
			else if(mincase[i]>cases[i])return true;
		}
		return false;
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int cnt=0;
		int[][] cases = new int[53130][5];
		for(int i=0;i<(1<<25);i++){
			if(Integer.bitCount(i)==5){
				int cnt2 = 0;
				for(int j=0;j<25;j++){
					if((i&(1<<j))!=0)
						cases[cnt][cnt2++]=j;
				}
				cnt++;
			}
		}
		
		int t = Integer.parseInt(br.readLine());
		String[]in ;
		for(int z=1;z<=t;z++){
			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int[n][3];
			for(int i=0;i<n;i++){
				in = br.readLine().split(" ");
				arr[i][0]=Integer.parseInt(in[0]);
				arr[i][1]=Integer.parseInt(in[1]);
				arr[i][2]=Integer.parseInt(in[2]);
			}
			long min = 100000000000L;
			int[] mincase = new int[5];
			for(int i=0;i<cnt;i++){
				long sum = 0;
				for(int j=0;j<n;j++){
					int curr = 1000000000;
					for(int k=0;k<5;k++){
						int r = cases[i][k]/5,c=cases[i][k]%5;
						int dist = Math.abs(r-arr[j][0])+Math.abs(c-arr[j][1]);
						if(dist<curr)curr=dist;
					}
					sum+=curr*arr[j][2];
				}
				if(sum<min){
					min=sum;
					for(int k=0;k<5;k++)
						mincase[k]=cases[i][k];
				}
				else if(sum==min){
					if(compare(mincase,cases[i])){
						for(int k=0;k<5;k++)
							mincase[k]=cases[i][k];
					}
				}
			}
			for(int i=0;i<4;i++)
				System.out.print(mincase[i]+" ");
			System.out.println(mincase[4]);
		}
		
	}
}
