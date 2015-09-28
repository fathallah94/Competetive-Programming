import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int n;
	static int[] arr,tree;
	
	static void build(int i,int li,int ri){
		if(li==ri){
			tree[i]=arr[li];
			return ;
		}
		build(i*2,li,(li+ri)/2);
		build(i*2+1,(li+ri)/2+1,ri);
		tree[i]=tree[i*2]+tree[i*2+1];
	}
	
	static int query(int i,int li,int ri,int a,int b){
		if(ri<a || li>b)return 0;
		if(li>=a && ri<=b)return tree[i];
		
		return query(i*2,li,(li+ri)/2,a,b)+query(i*2+1,(li+ri)/2+1,ri,a,b);
	}
	
	static void update(int i,int li,int ri,int target,int value){
		if(li==ri){
			tree[i]=value;
			return;
		}
		else if(target<=(li+ri)/2)
			update(i*2,li,(li+ri)/2,target,value);
		else
			update(i*2+1,(li+ri)/2+1,ri,target,value);
		tree[i]=tree[i*2]+tree[i*2+1];
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int cases = 0;
		while(true){
			n = Integer.parseInt(br.readLine().trim());
			if(n==0)break;
			arr = new int[n+1];
			tree = new int[4*(n+1)];
			for(int i=1;i<=n;i++)
				arr[i] = Integer.parseInt(br.readLine().trim());
			build(1,1,n);
			String[]in;
			if(cases>0)
				out.append("\n");
			cases++;
			out.append("Case "+cases+":\n");
			while(true){
				in = br.readLine().split("\\s+");
				if(in[0].charAt(0)=='E')break;
				else if(in[0].charAt(0)=='M'){
					int from = Integer.parseInt(in[1]) , to = Integer.parseInt(in[2]);
					out.append(query(1,1,n,from,to)+"\n");
				}
				else{
					int target = Integer.parseInt(in[1]) , val = Integer.parseInt(in[2]);
					update(1,1,n,target,val);
				}
			}
		}
		System.out.print(out);
	}
}
