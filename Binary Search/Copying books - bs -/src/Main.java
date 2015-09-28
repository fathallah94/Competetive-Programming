import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
	static int n,k,largest=-1;
	static int[] books ;
	static int predicate(int max){
		int needed=1,curr=0;
		for(int i=0;i<n;i++){
			if(curr+books[i]>max){
				needed++;
				curr=books[i];
			}
			else
				curr+=books[i];	
		}
		return needed;
	}
	static int bs(){
		int lo = largest,hi = 1000000000,mid;
		while(lo<hi){
			mid = (lo+hi)/2;
			if(predicate(mid)>k)
				lo = mid+1;
			else
				hi = mid;
		}
		return lo;
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int z=1;z<=t;z++){
			largest=-1;
			String[] in = br.readLine().split("\\s+");
			n =Integer.parseInt(in[0]);
			k = Integer.parseInt(in[1]);
			in = br.readLine().split("\\s+");
			books = new int[n];
			for(int i=0;i<n;i++){
				books[i]=Integer.parseInt(in[i]);
				if(books[i]>largest)
					largest=books[i];
			}
			int temp = k;
			ArrayList<Integer>[] res = new ArrayList[k+1];
			for(int i=0;i<=k;i++)
				res[i]=new ArrayList<Integer>();
			int max = bs();
			int curr = 0;
			k--;
			for(int i=n-1;i>=0;i--){
				if(curr+books[i]>max || i+1==k){
					k--;
					curr=0;
				}
				curr+=books[i];
				res[k].add(books[i]);
			}
			for(int i=0;i<temp-1;i++){
				for(int j=res[i].size()-1;j>=0;j--)
					System.out.print(res[i].get(j)+" ");
				System.out.print("/ ");
			}
			for(int j=res[temp-1].size()-1;j>0;j--){
				System.out.print(res[temp-1].get(j)+" ");
			}
			System.out.println(res[temp-1].get(0));
		}
	}
}
