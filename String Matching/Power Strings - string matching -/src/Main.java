import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
	static char[] str ;
	static int[] b;
	
	static void pro(){
		int n = str.length;
		b = new int[n+1];
		int i=0,j=-1;
		b[0]=-1;
		while(i<n){
			while(j>=0 && str[i]!=str[j])j=b[j];
			i++;j++;
			b[i]=j;
		}
	}
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int inde = 0;
		while(true){
			str = br.readLine().trim().toCharArray();
			if(str[0]=='.')break;
			
			pro();
			int n = str.length;
			int diff = n-1-b[n-1];
			
			int ind = n-1;
			boolean flag=true;
			boolean one = true;
			for(int i=1;i<n;i++){
				if(str[i]!=str[0]){
					one=false;
					break;
				}
			}
			if(one){
				System.out.println(n);
				continue;
			}
			if(b[n-1]==0){
				System.out.println(1);
				continue;
			}
			while(true){
				int now = b[ind];
				if(ind==diff-1)break;
				if((now==0 && ind!=diff-1) || (ind-now!=diff)){
					flag=false;break;
				}
				
				ind = now;
			}
			if(flag){
				System.out.println(n/diff);
			}				
			else{
				System.out.println(1);
			}
		}
	}
}