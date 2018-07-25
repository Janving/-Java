package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {

	public static String getStringID() {
		String id= null;
		Date date= new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmssSSS");
		id=sdf.format(date);
		return id;
	}
	
	public static String DatetoString(Date date) {
		
		String s="";
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  s=sdf.format(date);
			return s;
	}
}
