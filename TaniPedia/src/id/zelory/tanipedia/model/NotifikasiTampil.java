package id.zelory.tanipedia.model;

public class NotifikasiTampil
{
	private String id;
	private String idSoal;
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
