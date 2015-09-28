import java.util.Scanner;


class Main {
	static int n,s;
	static long[][][] memo = new long[66][66][3];
	static long rec(int i,int l,int locked){
		if(l>s )
			return 0;
		if(i==n){
			if(l==s)
				return 1;
			return 0;
		}
		if(memo[i][l][locked]!=-1)
			return memo[i][l][locked];
		if(locked==1 ||  (locked==0 && i==0))
			return memo[i][l][locked]=rec(i+1,l+1,1)+rec(i+1,l,0);
		else
			return memo[i][l][locked]=rec(i+1,l,1)+rec(i+1,l,0);
	}
	static public void main(String[] args){
		Scanner sc=new Scanner(System.in);
		while(true){
			n=sc.nextInt();
			s=sc.nextInt();
			if(n<0 && s<0)
				break;
			for(int i=0;i<66;i++)
				for(int j=0;j<66;j++){
					memo[i][j][0]=-1;
					memo[i][j][1]=-1;
				}
			System.out.println(rec(0,0,0));
		}
		sc.close();
	}
}
