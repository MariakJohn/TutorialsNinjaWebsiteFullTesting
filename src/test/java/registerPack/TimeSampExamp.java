package registerPack;

import java.util.Date;

public class TimeSampExamp {

	public static void main(String[] args) {
		
		//String timestamp = new SimpleDateFormat("yyy_MM_dd_hh_mm_ss").format(new Date());
				//String x = "maria" + timestamp + "@gmail.com";
				
				//String x = "maria" + new SimpleDateFormat("yyy_MM_dd_hh_mm_ss").format(new Date()) + "@gmail.com";
				//String xxx=new Date().toString().replaceAll("\\s","").replaceAll("\\:","");
				//String x = "maria" + xxx + "@gmail.com";
				
				//String x = "maria" + new SimpleDateFormat("yyy_MM_dd_hh_mm_ss").format(new Date()) + "@gmail.com";
				//String x = "maria" + new Date().toString().replaceAll("\\s","").replaceAll("\\:","") + "@gmail.com";
				String x = "maria" + new Date().toString().replaceAll("\\s|\\:","") + "@gmail.com";
				System.out.println(x);
	}

}
