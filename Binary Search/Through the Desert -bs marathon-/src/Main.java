import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Main {
	static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	static int[][] events=new int[55][3];
	static String[] in;
	static int startCon,counter=0;
	
	static boolean calc(double full) {
		double tank=full;
		double consume=startCon;
		int leak=0,lastkm=0,nowkm,count=0;
		while(true){
			nowkm=events[count][0];
			tank-=(consume/100)*(nowkm-lastkm);
			tank-=leak*(nowkm-lastkm);
			if(events[count][1]==1)
				consume=events[count][2];
			else if(events[count][1]==2)
				leak++;
			else if(events[count][1]==3 && tank>=0)
				tank=full;
			else if(events[count][1]==4)
				leak=0;
			else if(events[count][1]==5)
				break;
			lastkm=nowkm;
			count++;
		}
		if(tank>=0)
			return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException{
		while(true){
			in=br.readLine().split(" ");
			startCon=Integer.parseInt(in[3]);
			if(startCon==0)
				break;
			counter=0;
			while(true){
				in=br.readLine().split(" ");
				events[counter][0]=Integer.parseInt(in[0]);
				if(in[1].matches("Goal")){
					events[counter][1]=5;
					counter++;
					break;
				}
				else if(in[1].matches("Fuel")){
					events[counter][1]=1;
					events[counter][2]=Integer.parseInt(in[3]);
				}
				else if(in[1].matches("Leak")){
					events[counter][1]=2;
				}
				else if(in[1].matches("Gas")){
					events[counter][1]=3;
				}
				else if(in[1].matches("Mechanic"))
					events[counter][1]=4;
				counter++;
			}
			double lo=0,hi=1000000000,mid;
			for(int i=0;i<200;i++){
				mid=lo+(hi-lo)/2;
				if(calc(mid))
					hi=mid;
				else
					lo=mid;
			}
			System.out.printf("%.3f\n",lo);
		}
		
	}
}
