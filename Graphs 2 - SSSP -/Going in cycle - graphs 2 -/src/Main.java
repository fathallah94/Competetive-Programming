import java.util.Arrays;
import java.util.Scanner;

 public class Main {
	static int n,m;
	static int[][] edges;
	static double[] dist ,weights ,temp ;
	
	static boolean cycle(double r){
		for(int i=0;i<m;i++)
			weights[i]=temp[i]-r;
		Arrays.fill(dist,1000000000);
		dist[1]=0;
		for(int i=0;i<n-1;i++){
			for(int j=0;j<m;j++){
				if(dist[edges[j][0]]+weights[j] < dist[edges[j][1]])
					dist[edges[j][1]] = dist[edges[j][0]]+weights[j];
			}
		}
		for(int j=0;j<m;j++){
			if(dist[edges[j][0]]+weights[j]< dist[edges[j][1]])
				return true;
		}
		return false;
	}
	static public void main(String[] args)throws Exception{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int z=1;z<=t;z++){
			n = sc.nextInt();m=sc.nextInt();
			edges = new int[m+10][2];
			weights = new double[m+10];
			temp = new double[m+10];
			dist = new double[n+10];
			for(int i=0;i<m;i++){
				edges[i][0]=sc.nextInt();
				edges[i][1]=sc.nextInt();
				weights[i]=sc.nextInt();
				temp[i]=weights[i];
			}
			
			double lo = 0,hi = 1000000000,mid;
			for(int i=0;i<200;i++){
				mid = lo+(hi-lo)/2;
				if(cycle(mid)){
					hi=mid;
				}
				else{
					lo=mid;
				}
			}
			if(Math.abs(lo-1000000000)<=0.0000000001)
				System.out.println("Case #"+z+": No cycle found.");
			else
				System.out.printf("Case #%d: %.2f\n",z,lo);
		}
	}
}
