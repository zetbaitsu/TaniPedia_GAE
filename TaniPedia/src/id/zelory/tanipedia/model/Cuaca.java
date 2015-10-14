package id.zelory.tanipedia.model;

import id.zelory.tanipedia.util.StringUtils;

public class Cuaca
{
	public static final String API = "http://api.openweathermap.org/data/2.5/forecast/daily?cnt=7&mode=json&units=metric&APPID=2a266635e0fb451a9f3171341233a2ef";

	private String lokasi;
	private String suhuMax;
	private String suhuMin;
	private String suhu;
	private String tanggal;
	private String cuaca;
	private String kegiatan;

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
}
