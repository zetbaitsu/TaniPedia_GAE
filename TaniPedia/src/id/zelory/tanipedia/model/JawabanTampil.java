package id.zelory.tanipedia.model;

public class JawabanTampil
{
	private String idSoal;
	private String tanggal;
	private String isi;
	private PakTani pakTani;

	public String getIdSoal()
	{
		return idSoal;
	}

	public void setIdSoal(String idSoal)
	{
		this.idSoal = idSoal;
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
