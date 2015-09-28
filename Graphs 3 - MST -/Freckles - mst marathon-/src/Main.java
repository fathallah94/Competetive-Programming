import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;


 class Main {
	static int cases,n;
	static int parents[]=new int[105];
	static double[][] arr=new double[105][2];
	static double[][] distances= new double[105][105];
	
	static class IntCompare implements Comparator<Edge> {
		 
		public int compare(Edge o1, Edge o2) {
 
			if(o1.cost>o2.cost)
			{
				return 1;
			}else if(o1.cost<o2.cost)
			{
				return -1;
			}
			return 0;
		}
 
	} 
 
	static Comparator<Edge>comp = new IntCompare(); 
	
	static class Edge{
		int from,to;
		double cost;
	}
	
	static void init (){
		for(int i=0;i<=n;i++)
			parents[i]=i;
	}
	
	static int find(int i){
		if(i==parents[i])return i;
		return parents[i]=find(parents[i]);
	}
	
	static boolean union(int u,int v){
		int r_u=find(u);
		int r_v=find(v);
		if(r_v==r_u)return false;
		parents[r_v]=r_u;
		return true;
	}
	
	static public void main(String[] args)throws Exception{
		Scanner s= new Scanner(System.in);
		cases=s.nextInt();
		for(int z=0;z<cases;z++){
			
			n=s.nextInt();
			for(int i=0;i<n;i++){
				arr[i][0]=s.nextDouble();
				arr[i][1]=s.nextDouble();
			}
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					if(i==j)
						distances[i][j]=0;
					double dist=Math.sqrt((arr[i][0]-arr[j][0])*(arr[i][0]-arr[j][0])+(arr[i][1]-arr[j][1])*(arr[i][1]-arr[j][1]));
					distances[i][j]=dist;
				
				}
			}
			PriorityQueue<Edge> q=new PriorityQueue<Edge>(1000000+5,comp);
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					Edge e=new Edge();
					e.from=i;
					e.to=j;
					e.cost=distances[i][j];
					q.add(e);
				}
			}
			int edges=0;
			double sum=0;
			init();
			while(!q.isEmpty()){
				Edge e=q.remove();
				if(union(e.from,e.to)){
					sum+=e.cost;
					edges++;
				}
				if(edges==n-1)
					break;
			}
			System.out.printf("%.2f\n",sum);
			if(z<cases-1)
				System.out.println();
			
		}
	}
}
