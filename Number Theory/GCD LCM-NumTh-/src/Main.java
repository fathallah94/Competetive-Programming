import java.util.Scanner;


class Main {
	public static void main(String[] args){
		Scanner s=new Scanner(System.in);
		int cases=s.nextInt();
		for(int z=1;z<=cases;z++){
			int G=s.nextInt();
			int L=s.nextInt();
			if(L%G==0)System.out.println(G+" "+L);
			else System.out.println("-1");
		}
		s.close();
	}
}
