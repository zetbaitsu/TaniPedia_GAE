package id.zelory.tanipedia.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils
{
	private static final String cerah[] = { "Waktu yang tepat untuk memupuk.",
			"Tidak ada salahnya untuk panen hari ini.",
			"Membersihkan ladang nampaknya bisa dilakukan." };
	private static final String berawan[] = {
			"Tidak ada salahnya untuk menanam benih yang baru saja kamu beli.",
			"Apakah ladangmu sudah dibersihkan hari ini?",
			"Kamu bisa kepasar untuk membeli beberapa benih atau pupuk hari ini." };
	private static final String hujan[] = {
			"Nampaknya bukan hari yang tepat untuk memanen hari ini.",
			"Mungkin besok adalah hari yang lebih baik untuk memanen.",
			"Istirahat sejenak tidak ada salahnya kok." };

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
			if (string.charAt(i) != ' ')
				break;
		}
		System.out.println(i);
		return string.substring(i);
	}

	public static String ubahTanggal(String tanggal)
	{
		return ubahHari(tanggal.substring(0, 3)) + ", " + tanggal.substring(8)
				+ " " + tanggal.substring(4, 7) + " 2015";
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
			case "Clouds":
				hasil = "Berawan";
				System.out.println(cuaca);
				break;
			default:
				hasil = cuaca;
				break;
		}
		return hasil;
	}

	public static String randKegiatan(String cuaca)
	{
		String kegiatan = null;

		switch (cuaca)
		{
			case "Hujan":
				kegiatan = hujan[Utils.randInt(0, 2)];
				break;
			case "Cerah":
				kegiatan = cerah[Utils.randInt(0, 2)];
				break;
			case "Berawan":
				kegiatan = berawan[Utils.randInt(0, 2)];
				break;
			default:
				kegiatan = "Tidak ada.";
				break;
		}

		return kegiatan;
	}
}
