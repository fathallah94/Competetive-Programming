import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static char[] str,rev,s;
	static int[] b = new int[200000+10];
	static int n;
	
	static void pro(){
		int i=0,j=-1;
		b[0]=-1;
		while(i<n){
			while(j>=0 && rev[i]!=rev[j])j=b[j];
			i++;j++;
			b[i]=j;
		}
	}
	
	static boolean kmp(){
		int i=0,j=0;
		while(i<2*n){
			while(j>=0 && s[i]!=rev[j])j=b[j];
			i++;j++;
			if(j==n ){
				if(i-j!=0 && i-j!=n)
					return true;
				j=b[j];
			}
		}
		return false;
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		for(int z=1;z<=t;z++){
			str = br.readLine().toCharArray();
			n = str.length;
			rev = new char[n];
			s = new char[n*2];
			for(int i=0;i<n;i++){
				rev[i]=str[n-1-i];
				s[i]=str[i];
				s[n+i]=str[i];
			}
			
			pro();
			if(kmp())
				System.out.println("alindrome");
			else{
				boolean flag = true;
				for(int i=0;i<n/2;i++){
					if(str[i]!=str[n-1-i]){
						flag=false;break;
					}
				}
				if(flag)
					System.out.println("palindrome");
				else
					System.out.println("simple");
			}
		}
	}
}
