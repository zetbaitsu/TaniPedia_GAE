package id.zelory.tanipedia.util;

import id.zelory.tanipedia.model.Berita;
import id.zelory.tanipedia.model.Jawaban;
import id.zelory.tanipedia.model.Komoditas;
import id.zelory.tanipedia.model.PakTani;
import id.zelory.tanipedia.model.Soal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Text;

public class DBHelper
{
	private static DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();

	public static void simpan(Berita berita)
	{
		Entity item = new Entity("Berita", berita.getAlamat());
		item.setProperty("judul", berita.getJudul());
		item.setProperty("alamat", berita.getAlamat());
		item.setProperty("gambar", berita.getGambar());
		item.setProperty("tanggal",
				StringUtils.resolveDate(berita.getTanggal()));
		item.setProperty("deskripsi",
				StringUtils.cleanFirst(berita.getDeskripsi()));

		if (berita.getIsi() == null)
		{
			Berita tmp = ambilBerita(berita.getAlamat(), true);
			if (tmp.getIsi() == null
					|| tmp.getIsi().equals("Berita belum diambil"))
				item.setProperty("isi", new Text("Berita belum diambil"));
			else
				item.setProperty("isi", new Text(tmp.getIsi()));
		} else
		{
			item.setProperty("isi", new Text(berita.getIsi()));
		}

		datastore.put(item);
	}

	public static boolean simpan(PakTani pakTani)
	{
		Entity item = new Entity("PakTani", pakTani.getEmail());
		item.setProperty("email", pakTani.getEmail());
		item.setProperty("nama", pakTani.getNama());
		item.setProperty("password", pakTani.getPassword());
		item.setProperty("isMale", pakTani.isMale());

		Key key = datastore.put(item);
		if (key.equals(item.getKey()))
			return true;
		else
			return false;
	}
	
	public static void simpan(Komoditas komoditas)
	{
		Entity item = new Entity("Komoditas", komoditas.getNama());
		item.setProperty("nama", komoditas.getNama());
		item.setProperty("harga", komoditas.getHarga());
		datastore.put(item);
	}

	public static boolean simpan(Soal soal)
	{
		Entity item = new Entity("Soal", soal.getId());
		item.setProperty("id", soal.getId());
		item.setProperty("email", soal.getEmail());
		item.setProperty("tanggal", soal.getTanggal());
		item.setProperty("isi", soal.getIsi());

		Key key = datastore.put(item);
		if (key.equals(item.getKey()))
			return true;
		else
			return false;
	}

	public static boolean simpan(Jawaban jawaban)
	{
		Key id = KeyFactory.createKey("Jawaban", jawaban.getIdSoal().toString()
				+ jawaban.getTanggal().toString());
		Entity item = new Entity("Jawaban", id);
		item.setProperty("idSoal", jawaban.getIdSoal());
		item.setProperty("email", jawaban.getEmail());
		item.setProperty("tanggal", jawaban.getTanggal());
		item.setProperty("isi", jawaban.getIsi());

		Key key = datastore.put(item);
		if (key.equals(item.getKey()))
			return true;
		else
			return false;
	}

	public static ArrayList<Berita> ambilBerita(int jumlah, boolean isi)
	{
		ArrayList<Berita> arrayListBerita = new ArrayList<Berita>();

		Query query = new Query("Berita").addSort("tanggal",
				Query.SortDirection.DESCENDING);
		List<Entity> items = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(jumlah));

		for (Entity item : items)
		{
			Berita berita = new Berita();
			berita.setJudul(item.getProperty("judul").toString());
			berita.setAlamat(item.getProperty("alamat").toString());
			berita.setGambar(item.getProperty("gambar").toString());
			String tanggal = item.getProperty("tanggal").toString();
			berita.setTanggal(tanggal.substring(8, 10) + " "
					+ tanggal.substring(4, 7) + " "
					+ tanggal.substring(tanggal.length() - 4));
			berita.setDeskripsi(item.getProperty("deskripsi").toString());

			if (isi)
			{
				Text text = (Text) item.getProperty("isi");
				berita.setIsi(text.getValue());
			}

			arrayListBerita.add(berita);
		}

		return arrayListBerita;
	}

	public static Berita ambilBerita(String url, boolean lengkap)
	{
		Query query = new Query("Berita");
		Filter filter = new Query.FilterPredicate("alamat",
				FilterOperator.EQUAL, url);
		query.setFilter(filter);
		PreparedQuery pq = datastore.prepare(query);
		Entity item = pq.asSingleEntity();

		Berita berita = new Berita();
		if (lengkap)
		{
			try
			{
				berita.setJudul(item.getProperty("judul").toString());
				berita.setAlamat(item.getProperty("alamat").toString());
				berita.setGambar(item.getProperty("gambar").toString());
				String tanggal = item.getProperty("tanggal").toString();
				berita.setTanggal(tanggal.substring(8, 10) + " "
						+ tanggal.substring(4, 7) + " "
						+ tanggal.substring(tanggal.length() - 4));
				berita.setDeskripsi(item.getProperty("deskripsi").toString());
			} catch (Exception e)
			{

			}
		}
		try
		{
			Text text = (Text) item.getProperty("isi");
			berita.setIsi(text.getValue());
		} catch (Exception e)
		{
			berita.setIsi("Berita belum diambil");
		}

		return berita;
	}

	public void hapusBerita(String url)
	{
		Query query = new Query("Berita");
		Filter filter = new Query.FilterPredicate("alamat",
				FilterOperator.EQUAL, url);
		query.setFilter(filter);
		PreparedQuery pq = datastore.prepare(query);
		Entity item = pq.asSingleEntity();

		datastore.delete(item.getKey());
	}

	public static ArrayList<Berita> ambilBeritaKosong(int jumlah)
	{
		ArrayList<Berita> arrayListBerita = new ArrayList<Berita>();
		Query query = new Query("Berita");
		List<Entity> items = datastore.prepare(query).asList(
				FetchOptions.Builder.withDefaults());

		for (Entity item : items)
		{
			Text text = (Text) item.getProperty("isi");
			if (text.getValue().equals("Berita belum diambil"))
			{
				Berita berita = new Berita();
				berita.setJudul(item.getProperty("judul").toString());
				berita.setAlamat(item.getProperty("alamat").toString());
				berita.setGambar(item.getProperty("gambar").toString());
				String tanggal = item.getProperty("tanggal").toString();
				berita.setTanggal(tanggal.substring(8, 10) + " "
						+ tanggal.substring(4, 7) + " "
						+ tanggal.substring(tanggal.length() - 4));
				berita.setDeskripsi(item.getProperty("deskripsi").toString());
				berita.setIsi(text.getValue());
				arrayListBerita.add(berita);
			}
			if (arrayListBerita.size() >= jumlah)
				break;
		}

		return arrayListBerita;
	}

	public static PakTani ambilPakTani(String email)
	{
		Query query = new Query("PakTani");
		Filter filter = new Query.FilterPredicate("email",
				FilterOperator.EQUAL, email);
		query.setFilter(filter);
		PreparedQuery pq = datastore.prepare(query);
		Entity item = pq.asSingleEntity();

		PakTani pakTani = new PakTani();

		try
		{
			pakTani.setEmail(item.getProperty("email").toString());
			pakTani.setNama(item.getProperty("nama").toString());
			pakTani.setPassword(item.getProperty("password").toString());
			pakTani.setMale((boolean) item.getProperty("isMale"));
		} catch (Exception e)
		{

		}

		return pakTani;
	}

	public static ArrayList<Soal> ambilSoal(int jumlah)
	{
		ArrayList<Soal> soal = new ArrayList<Soal>();
		Query query = new Query("Soal").addSort("tanggal",
				Query.SortDirection.DESCENDING);
		List<Entity> items = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(jumlah));

		for (Entity item : items)
		{
			Soal s = new Soal();
			s.setId((Key) item.getProperty("id"));
			s.setEmail(item.getProperty("email").toString());
			s.setTanggal((Date) item.getProperty("tanggal"));
			s.setIsi(item.getProperty("isi").toString());

			soal.add(s);
		}

		return soal;
	}
	
	public static ArrayList<Soal> ambilSoal(String email, int jumlah)
	{
		ArrayList<Soal> soal = new ArrayList<Soal>();
		Query query = new Query("Soal").addSort("tanggal",
				Query.SortDirection.DESCENDING);
		Filter filter = new Query.FilterPredicate("email",
				FilterOperator.EQUAL, email);
		query.setFilter(filter);
		List<Entity> items = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(jumlah));

		for (Entity item : items)
		{
			Soal s = new Soal();
			s.setId((Key) item.getProperty("id"));
			s.setEmail(item.getProperty("email").toString());
			s.setTanggal((Date) item.getProperty("tanggal"));
			s.setIsi(item.getProperty("isi").toString());

			soal.add(s);
		}

		return soal;
	}

	public static ArrayList<Jawaban> ambilJawaban(Key idSoal)
	{
		ArrayList<Jawaban> jawaban = new ArrayList<Jawaban>();
		Query query = new Query("Jawaban").addSort("tanggal",
				Query.SortDirection.ASCENDING);
		Filter filter = new Query.FilterPredicate("idSoal",
				FilterOperator.EQUAL, idSoal);
		query.setFilter(filter);
		List<Entity> items = datastore.prepare(query).asList(
				FetchOptions.Builder.withDefaults());

		for (Entity item : items)
		{
			Jawaban j = new Jawaban();
			j.setIdSoal((Key) item.getProperty("idSoal"));
			j.setEmail(item.getProperty("email").toString());
			j.setTanggal((Date) item.getProperty("tanggal"));
			j.setIsi(item.getProperty("isi").toString());

			jawaban.add(j);
		}

		return jawaban;
	}
	
	public static ArrayList<Komoditas> ambilKomoditas()
	{
		ArrayList<Komoditas> komoditas = new ArrayList<Komoditas>();
		Query query = new Query("Komoditas");
		List<Entity> items = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());

		for (Entity item : items)
		{
			Komoditas k = new Komoditas();
			k.setNama(item.getProperty("nama").toString());
			k.setHarga(item.getProperty("harga").toString());
			komoditas.add(k);
		}

		return komoditas;
	}
}
