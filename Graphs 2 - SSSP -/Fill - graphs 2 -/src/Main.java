import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Main {
	static int A,B,C,D,max=-1,mind=1000000000;
	static int[][] dist= new int[210][210] ;
	static boolean[][] v = new boolean[210][210];
	static class node{
		int a,b,best,d;
		public node(int x,int y,int d){
			a=x;b=y;this.d=d;
			best = -1;
			if(a>best && a<=D)best=a;
			if(b>best && b<=D)best=b;
			if(C-a-b>best && C-a-b<=D)best=C-a-b;
		}
	}
	static class comp implements Comparator<node>{
		public int compare(node n1, node n2) {
			if(n1.d<n2.d)
				return -1;
			else if(n1.d>n2.d)
				return 1;
			
			return 0;
		}
	}
	static Comparator<node> compa = new comp();
	static PriorityQueue<node> q = new PriorityQueue<node>(8000000,compa);
	
	static void dijkstra(){
		dist[0][0]=0;
		q.add(new node(0,0,0));
		while(!q.isEmpty()){
			node n = q.poll();
//			System.out.println(n.a+"!!"+n.b+"!!"+(C-n.a-n.b)+"!!"+n.d+"!!"+n.best);
			if(n.best>max && n.best!=-1){
				max=n.best;
				mind=n.d;
			}
			else if(n.best==max && n.best!=-1 && n.d<mind){
				mind=n.d;
			}
			if(!v[n.a][n.b]){
				v[n.a][n.b]=true;
				int rem,c = C-n.a-n.b;
				if(n.a!=0){
					// pour a into b
					rem = B-n.b;
					if(rem>=n.a && n.a+n.d<dist[0][n.b+n.a]){
						dist[0][n.b+n.a]=n.a+n.d;
						q.add(new node(0,n.b+n.a,n.d+n.a));
//						System.out.println(0+" "+(n.b+n.a)+" "+c+" "+(n.d+n.a));
					}
					else if(rem<n.a && n.d+rem<dist[n.a-rem][B]){
						dist[n.a-rem][B]=n.d+rem;
						q.add(new node(n.a-rem,B,n.d+rem));
//						System.out.println((n.a-rem)+" "+(B)+" "+c+" "+(n.d+rem));
					}
					//pour a into c
					rem = C-c;
					if(rem>=n.a && n.a+n.d<dist[0][n.b]){
						dist[0][n.b]=n.a+n.d;
						q.add(new node(0,n.b,n.d+n.a));
//						System.out.println((0)+" "+(n.b)+" "+(n.a+c)+" "+(n.d+n.a));
					}
					else if(rem<n.a && n.d+rem<dist[n.a-rem][n.b]){
						dist[n.a-rem][n.b]=n.d+rem;
						q.add(new node(n.a-rem,n.b,n.d+rem));
//						System.out.println((n.a-rem)+" "+(n.b)+" "+(C)+" "+(n.d+rem));
					}
				}
				if(n.b!=0){
					//pour from b into a
					rem = A-n.a;
					if(rem>=n.b && n.b+n.d<dist[n.a+n.b][0]){
						dist[n.a+n.b][0]=n.b+n.d;
						q.add(new node(n.a+n.b,0,n.d+n.b));
//						System.out.println((n.a+n.b)+" "+(0)+" "+(c)+" "+(n.d+n.b));
					}
					else if(rem<n.b && n.d+rem<dist[A][n.b-rem]){
						dist[A][n.b-rem]=n.d+rem;
						q.add(new node(A,n.b-rem,n.d+rem));
//						System.out.println((A)+" "+(n.b-rem)+" "+(c)+" "+(n.d+rem));
					}
					//pour from b into c
					rem = C-c;
					if(rem>=n.b && n.b+n.d<dist[n.a][0]){
						dist[n.a][0]=n.b+n.d;
						q.add(new node(n.a,0,n.d+n.b));
//						System.out.println((n.a)+" "+(0)+" "+(n.b+c)+" "+(n.d+n.b));
					}
					else if(rem<n.b && n.d+rem<dist[n.a][n.b-rem]){
						dist[n.a][n.b-rem]=n.d+rem;
						q.add(new node(n.a,n.b-rem,n.d+rem));
//						System.out.println((n.a)+" "+(n.b-rem)+" "+(C)+" "+(n.d+rem));
					}
				}
				if(c!=0){
					//pour from c into a
					rem = A-n.a;
					if(rem>=c && c+n.d<dist[n.a+c][n.b]){
						dist[n.a+c][n.b]=c+n.d;
						q.add(new node(n.a+c,n.b,n.d+c));
//						System.out.println((n.a+c)+" "+(n.b)+" "+(0)+" "+(n.d+c));
					}
					else if(rem<c && n.d+rem<dist[A][n.b]){
						dist[A][n.b]=n.d+rem;
						q.add(new node(A,n.b,n.d+rem));
//						System.out.println((A)+" "+(n.b)+" "+(c-rem)+" "+(n.d+rem));
					}
					//pour from c into b
					rem = B-n.b;
					if(rem>=c && c+n.d<dist[n.a][n.b+c]){
						dist[n.a][n.b+c]=c+n.d;
						q.add(new node(n.a,n.b+c,n.d+c));
//						System.out.println((n.a)+" "+(n.b+c)+" "+(0)+" "+(n.d+c));
					}
					else if(rem<c && n.d+rem<dist[n.a][B]){
						dist[n.a][B]=n.d+rem;
						q.add(new node(n.a,B,n.d+rem));
//						System.out.println((n.a)+" "+(B)+" "+(c-rem)+" "+(n.d+rem));
					}
				}
			}
		}
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		String[] in;
		for(int z=1;z<=t;z++){
			in = br.readLine().split("\\s+");
			A = Integer.parseInt(in[0]);
			B = Integer.parseInt(in[1]);
			C = Integer.parseInt(in[2]);
			D = Integer.parseInt(in[3]);
			
			max = -1;mind=1000000000;
			for(int i=0;i<200+10;i++){
				for(int j=0;j<200+10;j++){
					dist[i][j]=1000000000;
					v[i][j]=false;
				}
			}
			
			dijkstra();
			System.out.println(mind+" "+max);
		}
	}
}
