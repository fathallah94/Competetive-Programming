import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;


class Main {
	static boolean[] v=new boolean[50],exists=new boolean[50];
	static Stack<Integer> s=new Stack();
	static boolean[][] adj=new boolean[50][50];
	
	static void dfs(int i){
		v[i]=true;
		for(int j=0;j<26;j++){
			if(adj[i][j] && !v[j])
				dfs(j);
		}
		s.push(i);
	}
	
	static public void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		String now,last;
		last=br.readLine();

		while(true){
			now=br.readLine();
			if(now.charAt(0)=='#')break;
			int i,size=Math.min(now.length(), last.length());
			
			for(i=0;i<now.length();i++)
				exists[now.charAt(i)-'A']=true;
			for(i=0;i<last.length();i++)
				exists[last.charAt(i)-'A']=true;
			
			for(i=0;i<size;i++){
				if(now.charAt(i)!=last.charAt(i))
					break;
			}
			if(i==size){
				last=now;
				continue;
			}
			int from=last.charAt(i)-'A',to=now.charAt(i)-'A';
			adj[from][to]=true;
			last=now;
		}
		for(int w=0;w<26;w++){
			if(exists[w] && !v[w])
				dfs(w);
		}
		
		
		while(!s.isEmpty()){
			char temp=(char) (s.pop()+'A');
			
			System.out.print(temp);
		}
		System.out.println();
		br.close();
	}
}
