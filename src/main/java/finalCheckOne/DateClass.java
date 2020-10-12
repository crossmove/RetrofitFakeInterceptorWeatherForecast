package finalCheckOne;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateClass {

	public static String getTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DATE, 1);
        return new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());
    }
}
