package id.zelory.tanipedia.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return dateFormat.format(cal.getTime());
	}

	public static Date getTodayDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss, d M yyyy");
		Calendar cal = Calendar.getInstance();

		try
		{
			return dateFormat.parse(dateFormat.format(cal.getTime()));
		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public static String getArahAngin(double degree)
	{
		if ((degree < 22.5 && degree >= 0) || (degree < 360 && degree >= 337.5))
		{
			return "Utara";
		} else if (degree < 67.5)
		{
			return "Timur Laut";
		} else if (degree < 112.5)
		{
			return "Timur";
		} else if (degree < 157.5)
		{
			return "Tenggara";
		} else if (degree < 202.5)
		{
			return "Selatan";
		} else if (degree < 247.5)
		{
			return "Barat Daya";
		} else if (degree < 292.5)
		{
			return "Barat";
		} else
		{
			return "Barat Laut";
		}
	}
}
