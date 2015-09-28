import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static 	ArrayList<Integer>[] divisors = new ArrayList[100000+10];
	static boolean[] set = new boolean[100000+10];
	static int[] parents = new int[100000+10];
	
	static void div(int i){
		for(int j=1;j*j<=i;j++){
			if(i%j==0 && j*j!=i){
				divisors[i].add(j);
				divisors[i].add(i/j);
			}
			else if(i%j==0)
				divisors[i].add(j);
		}
	}
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=1;i<=100000;i++){
			divisors[i] = new ArrayList<Integer>();
			div(i);
		}
		Arrays.fill(parents,-1);
		int n = Integer.parseInt(br.readLine());
		String[]in ;
		StringBuilder out = new StringBuilder();
		for(int i=0;i<n;i++){
			in = br.readLine().split("\\s+");
			int x = Integer.parseInt(in[0]);
			int y = Integer.parseInt(in[1]);
			int counter = 0;
			for(int j=0;j<divisors[x].size();j++){
				int now = divisors[x].get(j);
				if(!set[now]){
					counter++;
					set[now]=true;
					parents[now]=i;
				}
				else{
					if(i-y>parents[now]){
						counter++;
					}
					parents[now]=i;
				}
			}
			out.append(counter+"\n");
		}
		System.out.print(out);
	}
}
