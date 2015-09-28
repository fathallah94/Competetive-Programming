import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

public class MagicalSpheres {
	static ArrayList<Integer> primes = new ArrayList<Integer>();
	static public void seive(){
		boolean[] isp = new boolean[100000+1];
		isp[0]=isp[1]=true;
		for(int i=2;i*i<=100000;i++){
			if(isp[i])continue;
			for(int j=i*i;j<=100000;j+=i)
				isp[j]=true;
		}
		for(int i=0;i<isp.length;i++)
			if(!isp[i])primes.add(i);
	}
	static public int C(int n,int p){
		long q = p;
		int res = 0;
		while(q<=n){
			res+=n/q;
			q*=p;
		}
		return res;
	}
	static public boolean factor(int n,int num,int real){
		int pf = primes.get(0),ind=1;
		while(pf*pf<=num){
			int power=0;
			while(num%pf==0){
				num/=pf;
				power++;
			}
			if(power>0 && power>C(n,pf)-C(real,pf)-C(n-real,pf))return false;
			pf=primes.get(ind++);
		}
		if(num!=1 && 1>C(n,num)-C(real,num)-C(n-real,num))return false;
		return true;
	}
	static public int divideWork(int spheresCount, int fakeSpheresCount, int gnomesAvailable){
		seive();
		for(int i=gnomesAvailable;i>0;i--){
			if(factor(spheresCount+fakeSpheresCount,i,spheresCount))return i;
		}
		return 1;
	}
	static public void main(String[] args){
		System.out.println(divideWork(867049395, 827703788, 39160));
	}
}
