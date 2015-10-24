package id.zelory.tanipedia.model;

import id.zelory.tanipedia.util.StringUtils;

public class Cuaca
{
	public static final String API = "http://api.openweathermap.org/data/2.5/forecast/daily?cnt=16&mode=json&units=metric&APPID=2a266635e0fb451a9f3171341233a2ef";

	private String lokasi;
	private String suhuMax;
	private String suhuMin;
	private String suhu;
	private String tanggal;
	private String cuaca;
	private String kegiatan;
	private String tekanan;
	private String kelembaban;
	private String kecepatanAngin;
	private String arahAngin;

	public String getLokasi()
	{
		return lokasi;
	}

	public void setLokasi(String lokasi)
	{
		this.lokasi = lokasi;
	}

	public String getSuhuMax()
	{
		return suhuMax;
	}

	public void setSuhuMax(String suhuMax)
	{
		this.suhuMax = suhuMax;
	}

	public String getSuhuMin()
	{
		return suhuMin;
	}

	public void setSuhuMin(String suhuMin)
	{
		this.suhuMin = suhuMin;
	}

	public String getSuhu()
	{
		return suhu;
	}

	public void setSuhu(String suhu)
	{
		this.suhu = suhu;
	}

	public String getTanggal()
	{
		return tanggal;
	}

	public void setTanggal(String tanggal)
	{
		this.tanggal = tanggal;
	}

	public String getCuaca()
	{
		return cuaca;
	}

	public void setCuaca(String cuaca)
	{
		this.cuaca = StringUtils.ubahCuaca(cuaca);
		this.kegiatan = StringUtils.randKegiatan(this.cuaca);
	}

	public String getKegiatan()
	{
		return kegiatan;
	}

	public String getTekanan()
	{
		return tekanan;
	}

	public void setTekanan(String tekanan)
	{
		this.tekanan = tekanan;
	}

	public String getKelembaban()
	{
		return kelembaban;
	}

	public void setKelembaban(String kelembaban)
	{
		this.kelembaban = kelembaban;
	}

	public String getKecepatanAngin()
	{
		return kecepatanAngin;
	}

	public void setKecepatanAngin(String kecepatanAngin)
	{
		this.kecepatanAngin = kecepatanAngin;
	}

	public String getArahAngin()
	{
		return arahAngin;
	}

	public void setArahAngin(String arahAngin)
	{
		this.arahAngin = arahAngin;
	}

	public void setKegiatan(String kegiatan)
	{
		this.kegiatan = kegiatan;
	}
	
	
}
