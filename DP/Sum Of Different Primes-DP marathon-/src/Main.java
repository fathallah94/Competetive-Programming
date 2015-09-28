import java.util.Arrays;
import java.util.Scanner;


class Main {
	
	static long[] primes= new long[664580];
	static boolean[] v=new boolean[10000001];
	static int n,k;
	static int[][][] memo= new int[15][1120+5][200];
	
	static int rec(int i,long rem,int last){
		if(rem==0){
			if(i==k)
				return 1;
			else
				return 0;
		}
		if(rem<0){
			return 0;
		}
		if(i>=k)
			return 0;
		if(primes[last]>rem || last>=197)
			return 0;
		
		if(memo[i][(int)rem][last]!=-1)
			return memo[i][(int)rem][last];
		
		return memo[i][(int)rem][last]=rec(i+1,rem-primes[last],last+1)+rec(i,rem,last+1);

	}
	
	static public void main(String[] args){
		v[0]=true;
		v[1]=true;
		long i, j;
        // Sieve of Eratosthenes
        for (i = 2; i * i < 10000001; i++) {
                if (!v[(int)i]) {
                        for (j = i * i; j < 10000001; j += i) {
                                v[(int)j] = true;
                        }
                }
        }
 
        int index = 0;
        for (i = 2; i < 10000001; i++) {
                if (!v[(int)i]) {
                        primes[index++] = i;
                }
        }
        
        Scanner s = new Scanner(System.in);
       
        while(true){
        	n=s.nextInt();
            k=s.nextInt();
        	if(n==0 && k==0)
        		break;
        	for(int l=0;l<15;l++)
        		for(int m=0;m<1125;m++)
        			for(int k=0;k<200;k++)
        				memo[l][m][k]=-1;
        	//call recursive;
        	System.out.println(rec(0,n,0));
        }
	}
}
