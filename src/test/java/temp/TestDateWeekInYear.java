package temp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TestDateWeekInYear {

	@Test
	public void test() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String date = "2014-11-03";
		Date d = format.parse(date);
		cal.setTime(d);
		cal.add(Calendar.DATE, -1);
		int value1 = cal.get(Calendar.WEEK_OF_YEAR);

		date = "2014-11-09";
		d = format.parse(date);
		cal.setTime(d);
		cal.add(Calendar.DATE, -1);
		int value2 = cal.get(Calendar.WEEK_OF_YEAR);

		System.out.println("value1 = " + value1 + ", value2 = " + value2);
	}
}
