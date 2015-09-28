import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int n,q;
	static int[] arr,tree;
	
	static void build(int i,int li,int ri){
		if(li==ri){
			tree[i]=li;
			return;
		}
		build(i*2,li,(li+ri)/2);
		build(i*2+1,(li+ri)/2+1,ri);
		if(arr[tree[i*2]]>arr[tree[i*2+1]])
			tree[i]=tree[i*2];
		else
			tree[i]=tree[i*2+1];
	}
	
	static void update(int i,int li,int ri,int target,int value){
		if(li==ri){
			arr[target]=value;
			return;
		}
		else if(target<=(li+ri)/2)
			update(i*2,li,(li+ri)/2,target,value);
		else
			update(i*2+1,(li+ri)/2+1,ri,target,value);
		
		if(arr[tree[i*2]]>arr[tree[i*2+1]])
			tree[i]=tree[i*2];
		else
			tree[i]=tree[i*2+1];	
	}
	
	static int query(int i,int li,int ri,int a,int b){
		if(li>b || ri<a){
			return -1000000000;
		}
		else if(li>=a && ri<=b)return tree[i];
		
		int res1 = query(i*2,li,(li+ri)/2,a,b);
		int res2 = query(i*2+1,(li+ri)/2+1,ri,a,b);
		if(res1==-1000000000)return res2;
		else if(res2==-1000000000)return res1;
		else if(arr[res1]>arr[res2])return res1;
		else return res2;
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		n = Integer.parseInt(br.readLine().trim());
		String[]in = br.readLine().split("\\s+");
		arr = new int[n+5];
		tree = new int[5*(n+1)];
		for(int i=1;i<=n;i++)
			arr[i]=Integer.parseInt(in[i-1]);
		
		q = Integer.parseInt(br.readLine().trim());
		build(1,1,n);
		for(int i=0;i<q;i++){
			in = br.readLine().split("\\s+");
			if(in[0].charAt(0)=='Q'){
				int from = Integer.parseInt(in[1]) , to = Integer.parseInt(in[2]);
				int first=0 , second=0;
				first = query(1,1,n,from,to);
				if(first==from)
					second = query(1,1,n,from+1,to);
				else if(first==to)
					second = query(1,1,n,from,to-1);
				else{
					int r1 =query(1,1,n,from,first-1);
					int r2 = query(1,1,n,first+1,to);
					if(arr[r1]>arr[r2])
						second=r1;
					else
						second=r2;
				}
				long res = arr[first]+arr[second];
				out.append(res+"\n");
			}
			else{
				int target = Integer.parseInt(in[1]) , val = Integer.parseInt(in[2]);
				update(1,1,n,target,val);
			}
		}
		System.out.print(out);
	}
}
