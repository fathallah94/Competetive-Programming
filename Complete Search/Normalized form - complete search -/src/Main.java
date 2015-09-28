import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;


public class Main {
	static public void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//( (((TF)F)T)(TF) )
		int counter = 0;
		Stack<Character> s = new Stack<Character>();
		while(true){
			String exp = br.readLine().trim();
			if(exp.charAt(0)=='(' && exp.charAt(1)==')' && exp.length()==2)break;
			int temp = 1,tmp=0,max=-1;
			for(int i=0;i<exp.length();i++){
				if(exp.charAt(i)=='(')tmp++;
				else if(exp.charAt(i)==')')tmp--;
				if(tmp>max)
					max=tmp;
			}
			temp = max%2;
			int open = 0;
			for(int i=0;i<exp.length();i++){
				if(exp.charAt(i)=='F' || exp.charAt(i)=='T')
					s.push(exp.charAt(i));
				else if(exp.charAt(i)=='('){
					open++;
					s.push('(');
				}
				else{
					boolean flag;
					if(s.pop()=='F')
						flag=false;
					else
						flag=true;
					while(s.peek()!='('){
						boolean curr ;
						if(s.pop()=='F')curr=false;
						else curr = true;
						if(temp==open%2)
							flag&=curr;
						else
							flag|=curr;
					}
					s.pop();
					open--;
					if(flag)s.push('T');
					else s.push('F');
				}
			}
			counter++;
			if(s.pop()=='F')
				System.out.println(counter+". false");
			else
				System.out.println(counter+". true");
		}
	}
}
