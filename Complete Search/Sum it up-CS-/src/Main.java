import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Main {
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer>[] res = new ArrayList[1000000];
		for(int i=0;i<res.length;i++)
			res[i] = new ArrayList<Integer>();
		
		while(true){
			String[] in = br.readLine().split(" ");
			int t = Integer.parseInt(in[0]);
			int n = Integer.parseInt(in[1]);
			if(n==0 )break;
			int[] arr = new int[n];
			for(int i=2;i<in.length;i++){
				arr[i-2]=Integer.parseInt(in[i]);
			}
			Arrays.sort(arr);
			int cnt = 0;
			for(int i=(1<<n)-1;i>0;i--){
				int sum = 0;
				for(int j=n-1;j>=0;j--){
					if((i&(1<<j))!=0){
						sum+=arr[j];
					}
				}
				if(sum!=t)continue;
				res[cnt].clear();
				for(int j=n-1;j>=0;j--){
					if((i&(1<<j))!=0){
						res[cnt].add(arr[j]);
					}
				}
				cnt++;
			}
			System.out.println("Sums of "+t+":");
			if(cnt==0)System.out.println("NONE");
			else{
				Set<String> output = new HashSet<String>();
				for(int i=0;i<cnt;i++){
					StringBuilder out = new StringBuilder();
					for(int j=0;j<res[i].size();j++){
						if(j<res[i].size()-1)
							out.append(res[i].get(j)+"+");
						else
							out.append(res[i].get(j));
					}
					output.add(out.toString());
				}
				String[] heh = new String[output.size()];
				int count=0;
				for(String s :output)
					heh[count++]=s;
				Arrays.sort(heh);
				for(int i=heh.length-1;i>=0;i--)
					System.out.println(heh[i]);
			}
		}
	}
}
