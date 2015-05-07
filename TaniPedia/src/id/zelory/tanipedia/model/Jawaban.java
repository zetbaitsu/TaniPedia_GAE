package id.zelory.tanipedia.model;

import java.util.Date;

import com.google.appengine.api.datastore.Key;

public class Jawaban
{
	private Key idSoal;
	private String email;
	private Date tanggal;
	private String isi;
	
	public Key getIdSoal()
	{
		return idSoal;
	}
	public void setIdSoal(Key idSoal)
	{
		this.idSoal = idSoal;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public Date getTanggal()
	{
		return tanggal;
	}
	public void setTanggal(Date tanggal)
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
}
