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

	public static String ubahTanggal(String tanggal)
	{
		return ubahHari(tanggal.substring(0, 3)) + ", " + tanggal.substring(8) + " "+ tanggal.substring(4, 7) + " 2015";
	}

	public static String ubahHari(String hari)
	{
		String hasil;
		switch (hari)
		{
			case "Mon":
				hasil = "Senin";
				break;
			case "Tue":
				hasil = "Selasa";
				break;
			case "Wed":
				hasil = "Rabu";
				break;
			case "Thu":
				hasil = "Kamis";
				break;
			case "Fri":
				hasil = "Jumat";
				break;
			case "Sat":
				hasil = "Sabtu";
				break;
			case "Sun":
				hasil = "Minggu";
				break;
			default:
				hasil = "Senin";
				break;
		}
		
		return hasil;
	}
	
	public static String ubahCuaca(String cuaca)
	{
		String hasil;
		
		switch (cuaca)
		{
			case "Rain":
				hasil = "Hujan";
				break;
			case "Clear":
				hasil = "Cerah";
				break;
			default:
				hasil = cuaca;
				break;
		}
		return hasil;
	}
}
