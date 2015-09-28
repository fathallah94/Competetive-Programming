import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


class Main {
	static long r,s,e;
	static int n;
	static int rec(long ind,long r,long c){
		if(ind==1)
			return 1;
		
		int res;
		if(r<ind/2){
			if(c<ind/2){
				res = rec(ind/2,r,c);
			}
			else if(c<ind-1){
				res = rec(ind/2,r,ind/2)+rec(ind/2,r,c-ind/2);
			}
			else
				res = rec(ind/2,r,ind/2)*2;
		}
		else{
			if(c<ind/2){
				res = rec(ind/2,r-ind/2,c);
			}
			else if(c<ind-1){
				res = rec(ind/2,r-ind/2,ind/2)-rec(ind/2,r-ind/2,c-ind/2);
			}
			else
				res = 0;
		}
		return res;
	}
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[]in ;
		
		while(true){
			in = br.readLine().trim().split(" ");
			n=-2;r=-2;s=-2;e=-2;
			for(int i=0;i<in.length;i++){
				if(in[i].length()==0)continue;
				if(n==-2)
					n = Integer.parseInt(in[i]);
				else if(r==-2)
					r = Long.parseLong(in[i]);
				else if(s==-2)
					s = Long.parseLong(in[i]);
				else if(e==-2)
					e = Long.parseLong(in[i]);
			}
			
			if(n==-1 && r==-1 && s==-1 && e==-1)break;
			long shifted = (1L<<n);
			int end = rec(shifted,r,e);
			int start ;
			if(s>0)
				start = rec(shifted,r,s-1);
			else
				start = 0;
			
			System.out.println(end-start);
			
		}
		br.close();
	}
}
