import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
// time limits because java has no unsigned long long but solution is correct
public class Main {
	static public void main(String[] args)throws Exception{
		ArrayList<BigInteger> res=  new ArrayList<BigInteger>();
		Set<BigInteger> set = new HashSet<BigInteger>();
		res.add(new BigInteger("1"));
		boolean[] isp = new boolean[200];
		Arrays.fill(isp,true);
		isp[0]=isp[1]=false;
		for(int i=2;i*i<200;i++){
			if(isp[i]){
				for(int j=i*i;j<200;j+=i){
					isp[j]=false;
				}
			}
		}
		//65536 power 4 equals 2^64 so it is the max
		BigInteger max = new BigInteger("65536");
		max = max.pow(4);
		for(int i=2;i<65536;i++){
			BigInteger now = new BigInteger(Integer.toString(i));
			BigInteger curr = new BigInteger(Integer.toString(i));
			for(int e = 2;;e++){
				curr = curr.multiply(now);
				if(curr.compareTo(max)>-1)break;
				if(!isp[e] && !set.contains(curr)){
					res.add(curr);
					set.add(curr);
				}
			}
		}
		Collections.sort(res);
		StringBuilder out = new StringBuilder();
		for(int i=0;i<res.size();i++){
			out.append(res.get(i)+"\n");
//			if(i<50)System.out.println(res.get(i));
		}
		System.out.print(out);
	}
}
