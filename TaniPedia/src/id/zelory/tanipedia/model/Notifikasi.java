package id.zelory.tanipedia.model;

import java.util.Date;

import com.google.appengine.api.datastore.Key;

public class Notifikasi
{
	private Key id;
	private Key idSoal;
	private String email;
	private String emailPenanya;
	private Date tanggal;
	private String isi;
	
	public Key getId()
	{
		return id;
	}

	public void setId(Key id)
	{
		this.id = id;
	}

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
	
	public String getEmailPenanya()
	{
		return emailPenanya;
	}

	public void setEmailPenanya(String emailPenanya)
	{
		this.emailPenanya = emailPenanya;
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
