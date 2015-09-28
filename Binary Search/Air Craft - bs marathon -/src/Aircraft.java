
public class Aircraft {
	public static double predicate(double t,int[] p1, int[] v1, int[] p2, int[] v2){
		double[] now1=new double[3],now2=new double[3];
		for(int i=0;i<3;i++){
			now1[i]=p1[i]+v1[i]*t;
			now2[i]=p2[i]+v2[i]*t;
		}
		return Math.sqrt((now1[0]-now2[0])*(now1[0]-now2[0])+(now1[1]-now2[1])*(now1[1]-now2[1])+(now1[2]-now2[2])*(now1[2]-now2[2]));
	}
	
	public static String nearMiss(int[] p1, int[] v1, int[] p2, int[] v2, int R){
		double lo=0,hi=1<<30,m1,m2;
		for(int i=0;i<200;i++){
			m1=lo+(hi-lo)/3;
			m2=lo+((hi-lo)*2)/3;
			if(predicate(m1,p1,v1,p2,v2)>predicate(m2,p1,v1,p2,v2))
				lo=m1;
			else
				hi=m2;
				
		}
		double x = predicate(lo,p1,v1,p2,v2);
		if(x<R || Math.abs(x-R)<0.0000000001){
			return "YES";
		}
		
		return "NO";
	}
	public static void main(String[] args){
		int[] p1={3731,8537,5661}

,v1={-70,71,32}

,p2={8701,-1886,-5115}

,v2={28,-13,7};





		System.out.println(nearMiss(p1,v1,p2,v2,9766));
	}
	
}
