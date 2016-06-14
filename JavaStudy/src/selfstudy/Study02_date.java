package selfstudy;

import java.util.Calendar;
import java.util.Date;

public class Study02_date {
	public static void main(String[] args) {
//		getDate();
		getCal();
	}
	
	private static void getDate() {
		Date date = new Date();
		System.out.println(date.toString());
		Date date2 = new Date(1989, 1, 12);
		Date date3 = new Date(1990, 2, 8);
		long gap = date2.getTime() - date3.getTime();
		if (gap < 0) {
			gap = -gap;
		}
		gap = gap / 1000 / 60 / 60 / 24;
		System.out.println("Gap between " + gap);
	}
	
	private static void getCal() {
		Calendar calendar = Calendar.getInstance();
//		calendar.set(1989, 2 - 1, 12);
		calendar.set(Calendar.YEAR, 1989);
		calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
		calendar.set(Calendar.DATE, 12);
		System.out.println(calendar.getTimeInMillis());
		
	}
}
