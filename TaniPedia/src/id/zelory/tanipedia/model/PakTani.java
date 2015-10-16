package id.zelory.tanipedia.model;

public class PakTani
{
	private String nama;
	private String email;
	private String password;
	private boolean isMale = true;

	public String getNama()
	{
		return nama;
	}

	public void setNama(String nama)
	{
		this.nama = nama;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public boolean isMale()
	{
		return isMale;
	}

	public void setMale(boolean isMale)
	{
		this.isMale = isMale;
	}
}
