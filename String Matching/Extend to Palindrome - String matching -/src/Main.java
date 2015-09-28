import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static char[] str,rev,s;
	static int[] b;
	
	static int kmpProcess(){
		int i=0,j=-1;
		b = new int[s.length+1];
		b[0]=-1;
		while(i<s.length){
			while(j>=0 && s[i]!=s[j])j=b[j];
			i++;j++;
			b[i]=j;
		}
		return b[s.length-1];
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		while(br.ready()){
			str = br.readLine().toCharArray();
			rev = new char[str.length];
			s = new char[2*str.length+1];
			for(int i=0;i<str.length;i++){
				rev[str.length-1-i]=str[i];
			}
			
			for(int i=0;i<str.length;i++)
				s[i]=rev[i];
			s[str.length]='#';
			for(int i=0;i<str.length;i++)
				s[i+str.length]=str[i];
			
			int ans = kmpProcess();
			StringBuilder res = new StringBuilder();
			res.append(str);
			for(int i=ans;i<str.length;i++)
				res.append(rev[i]);
			
			System.out.println(res);
		}
	}
}
