package id.zelory.tanipedia.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils
{
	public static Date resolveDate(String tanggal)
	{
		SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy");
		Date date = null;
		try
		{
			date = format.parse(tanggal.substring(3));
		} catch (ParseException e)
		{
			format.applyPattern("dd MMM yyyy");
			try
			{
				date = format.parse(tanggal);
			} catch (ParseException e1)
			{
				e1.printStackTrace();
			}
		}

		return date;
	}

	public static String cleanFirst(String string)
	{
		int i = 0;
		for (i = 0; i < string.length(); i++)
		{
			System.out.println(string.charAt(i));
			if (string.charAt(i) != ' ')
				break;
		}
		System.out.println(i);
		return string.substring(i);
	}
}
