package id.zelory.tanipedia.model;

public class Berita
{
	public static final String TAG_JUDUL = "div.list-block h3";
    public static final String TAG_ALAMAT = "div.list-block h3 a";
    public static final String TAG_GAMBAR = "a.post-thumbnail img";
    public static final String TAG_TANGGAL = "span.meta_date";
    public static final String TAG_DESKRIPSI = "div.maga-excerpt";
    public static final String TAG_ISI = "div.post_content";
    public static final String URL_UTAMA = "http://tabloidsahabatpetani.com/category/berita/page/";
	
	private String judul;
	private String alamat;
	private String gambar;
	private String tanggal;
	private String deskripsi;
	private String isi;
	
	public String getJudul()
	{
		return judul;
	}
	public void setJudul(String judul)
	{
		this.judul = judul;
	}
	public String getAlamat()
	{
		return alamat;
	}
	public void setAlamat(String alamat)
	{
		this.alamat = alamat;
	}
	public String getGambar()
	{
		return gambar;
	}
	public void setGambar(String gambar)
	{
		this.gambar = gambar;
	}
	public String getTanggal()
	{
		return tanggal;
	}
	public void setTanggal(String tanggal)
	{
		this.tanggal = tanggal;
	}
	public String getDeskripsi()
	{
		return deskripsi;
	}
	public void setDeskripsi(String deskripsi)
	{
		this.deskripsi = deskripsi;
	}
	public String getIsi()
	{
		return isi;
	}
	public void setIsi(String isi)
	{
		this.isi = isi;
	}
}
