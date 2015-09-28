import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static String s1,s2;
	static int[]b ;
	
	static void pro(){
		b = new int[s1.length()+1];
		b[0]=-1;
		int i=0,j=-1;
		while(i<s1.length()){
			while(j>=0 && s1.charAt(i)!=s1.charAt(j))j=b[j];
			i++;j++;
			b[i]=j;
		}
	}
	
	static int count(String unit,String str){
		StringBuilder s = new StringBuilder();
		int res = 0;
		if(str.length()%unit.length()!=0)return 0;
		else res = str.length()/unit.length();
		
		for(int i=0;i<res;i++)
			s.append(unit);
		
		if(s.toString().equals(str))
			return res;
		return 0;
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s1 = br.readLine();
		s2 = br.readLine();
		if(s1.length()>s2.length()){
			String temp = s2;
			s2=s1;
			s1=temp;
		}
		pro();
		String unit;
		if(b[s1.length()-1]!=0)
			unit = s1.substring(b[s1.length()-1]+1,s1.length());
		else
			unit = s1;
		
		boolean ones=true;
		for(int i=1;i<s1.length();i++){
			if(s1.charAt(i)!=s1.charAt(0)){
				ones=false;break;
			}
		}
		if(ones)
			unit = s1.charAt(0)+"";
		
		int num1 = count(unit,s1);
		int num2 = count(unit , s2);
		int res = 0;
		for(int i=1;i<=Math.min(num1,num2);i++){
			if(num1%i==0 && num2%i==0)
				res++;
		}
		System.out.println(res);
	}
}
