package id.zelory.tanipedia.model;


public class SoalTampil
{
	private String id;
	private String tanggal;
	private String isi;
	private PakTani pakTani;
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getTanggal()
	{
		return tanggal;
	}
	public void setTanggal(String tanggal)
	{
		this.tanggal = tanggal;
	}
	public String getIsi()
	{
		return isi;
	}
	public void setIsi(String isi)
	{
		this.isi = isi;
	}
	public PakTani getPakTani()
	{
		return pakTani;
	}
	public void setPakTani(PakTani pakTani)
	{
		this.pakTani = pakTani;
	}
}
