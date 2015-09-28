import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static String str ;
	static int[] b;
	static int max,s;
	
	static void pro(int start,String curr){
		int n = curr.length();
		Arrays.fill(b,0);
		int i=0,j=-1;
		b[0]=-1;
		while(i<n){
			while(j>=0 && curr.charAt(i)!=curr.charAt(j))j=b[j];
			i++;j++;
			b[i]=j;
		}
		
		for(i=n-1;i>=0;i--){
			if(b[i]+1>max && curr.charAt(i)==curr.charAt(b[i])){
				max=b[i]+1;
				s=start;
			}
			else if(b[i]+1==max && curr.charAt(i)==curr.charAt(b[i]) && max>1 && curr.substring(0,b[i]+1).compareTo(str.substring(s, s+max))<0){
				s=start;
			}
		}
	}
	
	static int[] fail = new int[max+1];
	static String p ;
	
	static void kmp1(){
		int i=0,j=-1;
		fail[0]=-1;
		p = str.substring(s, s+max);
		while(i<p.length()){
			while(j>=0 && p.charAt(i)!=p.charAt(j))j=b[j];
			i++;j++;
			b[i]=j;
		}
	}
	static int kmp2(){
		int i=0,j=0,occ=0;
		while(i<str.length()){
			while(j>=0 && str.charAt(i)!=p.charAt(j))j=b[j];
			i++;j++;
			if(j==p.length()){
				occ++;
				j=b[j];
			}
		}
		return occ;
	}
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int z=1;z<=t;z++){
			str = br.readLine();
			b = new int[str.length()+1];
			max=1;
			for(int i=0;i<str.length();i++){
				pro(i,str.substring(i,str.length()));
			}
			if(max==1){
				int[] letters = new int[30];
				for(int i=0;i<str.length();i++)
					letters[str.charAt(i)-'A']++;
				int res=0,max=1;
				for(int i=0;i<30;i++){
					if(letters[i]>max){
						max=letters[i];res=i+'A';
					}
				}
				if(max==1)
					System.out.println("No repetitions found!");
				else
					System.out.println((char)res+" "+max);
			}
			else{
				kmp1();
				int res = kmp2();
				System.out.println(str.substring(s,s+max)+" "+res);
			}
		}
	}
}