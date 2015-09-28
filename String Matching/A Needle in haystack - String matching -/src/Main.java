import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int n;
	static String p,t;
	static StringBuilder res ;
	static int[] b;
	
	static void kmpProcess(){
		b = new int[n+1];
		int i=0,j=-1;
		b[0]=-1;
		while(i<n){
			while(j>=0 && p.charAt(i)!=p.charAt(j))j=b[j];
			i++;j++;
			b[i]=j;
		}
	}
	
	static void kmpSearch(){
		int i=0,j=0;
		while(i<t.length()){
			while(j>=0 && t.charAt(i)!=p.charAt(j))j=b[j];
			i++;j++;
			if(j==n){
				res.append((i-j)+"\n");
				j=b[j];
			}
		}
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		boolean flag = false;
		while(br.ready()){
			if(flag)
				System.out.println();
			flag=true;
			n = Integer.parseInt(br.readLine());
			p = br.readLine();
			t = br.readLine();
			res = new StringBuilder();
			kmpProcess();
			kmpSearch();
			System.out.print(res);
		}
	}
}
