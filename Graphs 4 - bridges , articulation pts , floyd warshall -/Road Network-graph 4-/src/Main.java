import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Main {
	static int n,q,p;
	static int[] ranking=new int[155];
	static int[][][] dist=new int[155][155][155];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int cases=Integer.parseInt(br.readLine());
		String[] in;
		for(int z=1;z<=cases;z++){
			StringBuilder out=new StringBuilder();
			
			n=Integer.parseInt(br.readLine());
			for(int i=1;i<=n;i++){
				dist[0][i][i]=0;
			}
			for(int i=1;i<n;i++){
				in=br.readLine().split(" ");
				for(int j=0;j<in.length;j++){
					dist[0][j+1+i][i]=dist[0][i][j+1+i]=Integer.parseInt(in[j]);
					if(dist[0][i][j+i+1]==-1)
						dist[0][i][j+i+1]=dist[0][j+i+1][i]=100000;
				}
			}
			
			p=Integer.parseInt(br.readLine());
			in=br.readLine().split(" ");
			
			for(int i=0;i<p;i++)
				ranking[i]=Integer.parseInt(in[i]);
			
			q=Integer.parseInt(br.readLine());			

			for(int k=0;k<p;k++){
				for(int i=1;i<=n;i++){
					for(int j=1;j<=n;j++){
						dist[k+1][i][j]=Math.min(dist[k][i][j],dist[k][ranking[k]][i]+dist[k][ranking[k]][j]);
						dist[k+1][j][i]=dist[k+1][i][j];
					}
				}
			}
			out.append("Case "+z+":");
			int w,from,to;
			for(int i=0;i<q;i++){
				in=br.readLine().split(" ");
				w=Integer.parseInt(in[0]);
				from=Integer.parseInt(in[1]);
				to=Integer.parseInt(in[2]);
				if(dist[w][from][to]==100000)
					out.append(" -1");
				else
					out.append(" "+dist[w][from][to]);
			}
			System.out.println(out);
		}
		br.close();
	}
}
