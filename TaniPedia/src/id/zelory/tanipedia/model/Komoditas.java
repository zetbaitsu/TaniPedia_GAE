package id.zelory.tanipedia.model;

public class Komoditas
{
	public static final String API = "http://m.pip.kementan.org/index.php";
	public static final String POST_BERAS = "LHP-1";
	public static final String POST_PALAWIJA = "LHP-2";
	public static final String POST_SAYURAN = "LHP-3b";
	public static final String POST_BUAH = "LHP-4";
	
	private String nama;
	private String harga;
	
	public String getNama()
	{
		return nama;
	}
	public void setNama(String nama)
	{
		this.nama = nama;
	}
	public String getHarga()
	{
		return harga;
	}
	public void setHarga(String harga)
	{
		this.harga = harga;
	}
}
