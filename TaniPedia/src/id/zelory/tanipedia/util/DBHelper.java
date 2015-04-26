package id.zelory.tanipedia.util;

import id.zelory.tanipedia.model.Berita;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
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
}
