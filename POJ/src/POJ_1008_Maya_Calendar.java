import java.util.HashMap;
import java.util.Scanner;

public class POJ_1008_Maya_Calendar{
	private static Scanner in;
	private static String h[];
	private static HashMap<String,Integer> ha=new HashMap<String,Integer>();

	public static void main(String[] args){
		h="pop,no,zip,zotz,tzec,xul,yoxkin,mol,chen,yax,zac,ceh,mac,kankin,muan,pax,koyab,cumhu,uayet"
				.split(",");
		for(int i=0;i<h.length;i++)
			ha.put(h[i],i*20);
		h="imix,ik,akbal,kan,chicchan,cimi,manik,lamat,muluk,ok,chuen,eb,ben,ix,mem,cib,caban,eznab,canac,ahau"
				.split(",");
		in=new Scanner(System.in);
		int n=in.nextInt();
		System.out.println(n);
		for(;n>0;n--){
			int d=Integer.parseInt(in.next().replaceAll("\\.",""));
			d+=ha.get(in.next());
			d+=in.nextInt()*365;
			System.out.println(d%260%13+1+" "+h[d%260%20]+" "+d/260);
		}
	}
}
