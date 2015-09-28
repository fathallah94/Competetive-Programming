import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	static public boolean compare(ArrayList<Integer> l1 , ArrayList<Integer> l2){
		
		for(int i=0;i<6;i++){
			if(l1.get(i)>l2.get(i)){
				return true;
			}
			else if(l1.get(i)<l2.get(i))
				return false;
		}
		return false;
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		ArrayList<Integer>[] res = new ArrayList[1000];
		for(int i=0;i<1000;i++)
			res[i]=new ArrayList<Integer>();
		boolean flag=true;
		while(true){
			in = br.readLine().split(" ");
			int k = Integer.parseInt(in[0]);
			if(k==0)break;
			if(!flag)
				System.out.println();
			flag=false;
			int[] arr = new int[k];
			for(int i=1;i<in.length;i++)
				arr[i-1]=Integer.parseInt(in[i]);
			Arrays.sort(arr);
			int ind=0;
			
			for(int i=0;i<(1<<k);i++){
				if(Integer.bitCount(i)==6){
					res[ind].clear();
					for(int j=0;j<k;j++){
						if((i&(1<<j))!=0)
							res[ind].add(arr[j]);
					}
					Collections.sort(res[ind++]);
				}
			}
			
			for(int i = 0 ;i<ind;i++){
				for(int j=0;j<ind-1;j++){
					if(compare(res[j],res[j+1])){
						for(int w=0;w<6;w++){
							int temp = res[j].get(w);
							res[j].set(w,res[j+1].get(w));
							res[j+1].set(w,temp);
						}
					}
				}
			}
			
			for(int i=0;i<ind;i++){
				for(int j=0;j<res[i].size()-1;j++)
					System.out.print(res[i].get(j)+" ");
				System.out.println(res[i].get(5));
			}
		}
	}
}
