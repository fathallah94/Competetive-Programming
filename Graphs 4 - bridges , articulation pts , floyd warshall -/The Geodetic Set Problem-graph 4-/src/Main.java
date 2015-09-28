import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int n,q;
	static int[][] dist;
	static long[][] parents ;
	
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		dist = new int[n+1][n+1];
		parents = new long[n+1][n+1];
		for(int i=0;i<=n;i++){
			for(int j=0;j<=n;j++){
				if(i==j)dist[i][j]=0;
				else dist[i][j]=100000000;
			}
		}
		String[]in ;
		for(int i=1;i<=n;i++){
			in = br.readLine().split("\\s+");
			for(int j=0;j<in.length;j++){
				int now = Integer.parseInt(in[j]);
				dist[i][now]=dist[now][i]=1;
				parents[i][now] |= (1L<<i);
				parents[i][now] |=(1L<<now);
				parents[now][i]=parents[i][now];
			}
		}
		
		for(int k=1;k<=n;k++)
			for(int i=1;i<=n;i++)
				for(int j=1;j<=n;j++){
					if(dist[i][k]+dist[k][j]<dist[i][j]){
						dist[i][j]=dist[i][k]+dist[k][j];
						parents[i][j]=(parents[i][k]|parents[k][j]);
					}
					else if(dist[i][k]+dist[k][j]==dist[i][j]){
						parents[i][j] |=(parents[i][k]|parents[k][j]);
					}
				}
		
		for(int i=1;i<=n;i++){
			for(int j=1;j<=n;j++){
				parents[i][j] = (parents[i][j]>>1);
			}
		}
		q = Integer.parseInt(br.readLine().trim());
		for(int w=0;w<q;w++){
			in = br.readLine().split("\\s+");
			long res = 0;
			for(int i=0;i<in.length;i++){
				for(int j=i+1;j<in.length;j++){
					res|=(parents[Integer.parseInt(in[i])][Integer.parseInt(in[j])]);
				}
			}
			if(res==((1L<<n)-1))
					System.out.println("yes");
			else
				System.out.println("no");
		}
	}
}
