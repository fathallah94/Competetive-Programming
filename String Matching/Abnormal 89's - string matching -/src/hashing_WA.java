import java.io.BufferedReader;
import java.io.InputStreamReader;

public class hashing_WA {
	static String str ;
	static long b=1009,mod=40000000003L,b2=13,mod2=1000000007;
	static long[] prefix , suffix ,prefix2,suffix2;
	
	static void prepare(){
		long pre=0,suff=0,pre2=0,suff2=0;
		for(int i=0;i<str.length();i++){
			pre*=b;
			pre%=mod;
			pre = (pre + (str.charAt(i)-'a'+1))%mod;
			prefix[i]=pre;
			pre2*=b2;
			pre2%=mod2;
			pre2 = (pre2 + (str.charAt(i)-'a'+1))%mod2;
			prefix2[i]=pre2;
			
			suff*=b;
			suff%=mod;
			suff = (suff + (str.charAt(str.length()-1-i)-'a'+1))%mod;
			suffix[str.length()-1-i]=suff;
			suff2*=b2;
			suff2%=mod2;
			suff2 = (suff2 + (str.charAt(str.length()-1-i)-'a'+1))%mod2;
			suffix2[str.length()-1-i]=suff2;
		}
		
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		StringBuilder out = new StringBuilder();
		prefix = new long[200000+10];
		suffix = new long[200000+10];
		prefix2 = new long[200000+10];
		suffix2 = new long[200000+10];
		long[] powers1 = new long[200000+10] , powers2 = new long[200000+10];
		powers1[0]=powers2[0]=1;
		for(int i=1;i<=200000;i++){
			powers1[i] =( powers1[i-1]*b)%mod;
			powers2[i] = (powers2[i-1]*b2)%mod2;
		}
		
		for(int z=1;z<=t;z++){
			str = br.readLine();
			prepare();
			boolean alindrome = false;
			for(int i=0;i<str.length()-1;i++){	
				if(prefix[i]==(suffix[0]-(suffix[i+1]*powers1[i+1])%mod+mod)%mod && suffix[i+1]==(prefix[str.length()-1]-(prefix[i]*powers1[str.length()-1-i])%mod+mod)%mod){
					if(prefix2[i]==(suffix2[0]-(suffix2[i+1]*powers2[i+1])%mod2+mod2)%mod2 && suffix2[i+1]==(prefix2[str.length()-1]-(prefix2[i]*powers2[str.length()-1-i])%mod2+mod2)%mod2){
						alindrome =true;
						break;
					}
				}
			}
			if(alindrome)
				out.append("alindrome\n");
			else if(prefix[str.length()-1]==suffix[0] && prefix2[str.length()-1]==suffix2[0])
				out.append("palindrome\n");
			else
				out.append("simple\n");
		}
		System.out.print(out);
	}
}
