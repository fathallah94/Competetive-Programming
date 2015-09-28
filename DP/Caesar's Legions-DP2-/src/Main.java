import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	static int n1,n2,max1,max2;
	static int[][][][][] memo;
	static int rec(int num1,int num2,int k1,int k2,int last){
		if(num1<0 || num2<0)return 0;

		if(num1==0 && num2==0)return 1;

		if(memo[num1][num2][k1][k2][last]!=-1)
			return memo[num1][num2][k1][k2][last]%100000000 ;
		
		if(last==0)return memo[num1][num2][k1][k2][last]=(rec(num1-1,num2,1,0,1)%100000000+rec(num1,num2-1,0,1,2)%100000000)%100000000;
				
		if(last==1 && k1<max1)return memo[num1][num2][k1][k2][last]=(rec(num1-1,num2,k1+1,0,1)%100000000 +rec(num1,num2-1,0,1,2)%100000000 )%100000000 ;
		else if(last==1 && k1==max1)return memo[num1][num2][k1][k2][last]=rec(num1,num2-1,0,1,2)%100000000 ;
		else if(last==2 && k2<max2)return memo[num1][num2][k1][k2][last]=(rec(num1,num2-1,0,k2+1,2)%100000000 +rec(num1-1,num2,1,0,1)%100000000 )%100000000 ;
		else if(last==2 && k2==max2)return memo[num1][num2][k1][k2][last]=rec(num1-1,num2,1,0,1)%100000000 ;
		else return -(1<<30);
		
	}
	
	static public void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String[] in=br.readLine().split(" ");
		n1=Integer.parseInt(in[0]);
		n2=Integer.parseInt(in[1]);
		max1=Integer.parseInt(in[2]);
		max2=Integer.parseInt(in[3]);
		memo=new int[n1+1][n2+1][max1+1][max2+1][3];
		for(int i=0;i<n1+1;i++)
			for(int j=0;j<n2+1;j++)
				for(int k=0;k<max1+1;k++)
					for(int l=0;l<max2+1;l++)
						for(int q=0;q<3;q++)
							memo[i][j][k][l][q]=-1;
		
		System.out.println(rec(n1,n2,0,0,0));
		br.close();
	}
}
