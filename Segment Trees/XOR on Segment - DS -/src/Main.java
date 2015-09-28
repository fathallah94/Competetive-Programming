import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int n,m;
	static long[] arr;
	static int[][] tree,lazy;
	
	
	static void build(int bit,int i,int li,int ri){
		if(li==ri){
			if((arr[li]&(1L<<bit))!=0)
				tree[bit][i]=1;
			return;
		}
		build(bit,i*2,li,(li+ri)/2);
		build(bit,i*2+1,(li+ri)/2+1,ri);
		tree[bit][i]= tree[bit][i*2]+tree[bit][i*2+1];
	}
	
	static int query(int bit,int i,int li,int ri,int a,int b){
		if(li!=ri){
			lazy[bit][i*2]^=lazy[bit][i];
			lazy[bit][i*2+1]^=lazy[bit][i];
		}
		tree[bit][i]+=-(lazy[bit][i]*tree[bit][i]) + (lazy[bit][i]*(ri-li+1-tree[bit][i]));
		lazy[bit][i]=0;
		if(li>=a && ri<=b){
			return tree[bit][i];
		}
		else if(li>b || ri<a){
			return 0;
		}
		return query(bit,i*2,li,(li+ri)/2,a,b)+query(bit,i*2+1,(li+ri)/2+1,ri,a,b);
	}
	
	static void update(int bit,int i,int li,int ri,int a,int b){
		if(li!=ri){
			lazy[bit][i*2]^=lazy[bit][i];
			lazy[bit][i*2+1]^=lazy[bit][i];
		}
		tree[bit][i]+=-(lazy[bit][i]*tree[bit][i]) + (lazy[bit][i]*(ri-li+1-tree[bit][i]));
		lazy[bit][i]=0;
		if(li>=a && ri<=b){
			lazy[bit][i]^=1;
			if(li!=ri){
				lazy[bit][i*2]^=lazy[bit][i];
				lazy[bit][i*2+1]^=lazy[bit][i];
			}
			tree[bit][i]+=-(lazy[bit][i]*tree[bit][i]) + (lazy[bit][i]*(ri-li+1-tree[bit][i]));
			lazy[bit][i]=0;
			return;
		}
		else if(li>b || ri<a)return;
		
		update(bit,i*2,li,(li+ri)/2,a,b);
		update(bit,i*2+1,(li+ri)/2+1,ri,a,b);
		tree[bit][i] = tree[bit][i*2]+tree[bit][i*2+1];
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		String[]in = br.readLine().split(" ");
		arr = new long[n+5];
		tree = new int[37][3*(n+10000)];
		lazy = new int[37][3*(n+10000)];
		for(int i=0;i<n;i++)
			arr[i+1]=Long.parseLong(in[i]);
		
		m = Integer.parseInt(br.readLine());
		
		for(int i=0;i<37;i++)
			build(i,1,1,n);
	
		for(int w=0;w<m;w++){
			in = br.readLine().split(" ");
			if(in[0].charAt(0)=='1'){
				int from = Integer.parseInt(in[1]) , to =Integer.parseInt(in[2]);
				long res = 0;
				int carry = 0,temp=0;
				for(int i=0;i<37;i++){
					temp=0;
					temp = query(i,1,1,n,from,to)+carry;
					carry=0;
					if(temp%2==1)
						res+=(1L<<i);
					carry+=temp/2;
				}
				out.append(res+"\n");
			}
			else{
				int from = Integer.parseInt(in[1]) , to =Integer.parseInt(in[2]) ;
				long val = Long.parseLong(in[3]);
				for(int i=0;i<37;i++){
					if((val&(1L<<i))!=0){
						update(i,1,1,n,from,to);
					}
				}
			}
		}
		
		System.out.print(out);
	}
}