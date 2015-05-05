package id.zelory.tanipedia.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Utils
{
	public static int randInt(int min, int max)
	{
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}

	public static String getYesterdayDateString()
	{
		DateFormat dateFormat = new SimpleDateFormat("d M yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return dateFormat.format(cal.getTime());
	}
}
