package id.zelory.tanipedia.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Entities;

import com.google.appengine.api.ThreadManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadFactory;

import id.zelory.tanipedia.model.Berita;

public class Scraper
{
	public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; rv:37.0) Gecko/20100101 Firefox/37.0";

	public static ArrayList<Berita> ambilListBerita(final String url,
			int jumlahHalaman)
	{
		int jumlahBerita = 0;
		boolean masih = true;
		ArrayList<Berita> beritaArrayList = new ArrayList<>();
		Thread threads[] = new Thread[jumlahHalaman];
		ThreadFactory factory = ThreadManager.currentRequestThreadFactory();

		final Document documents[] = new Document[jumlahHalaman];

		for (int i = 0; i < jumlahHalaman; i++)
		{
			final int x = i + 1;
			threads[i] = factory.newThread(new Runnable()
			{
				@Override
				public void run()
				{
					documents[x - 1] = null;
					while (documents[x - 1] == null)
					{
						try
						{
							documents[x - 1] = Jsoup.connect(url + x)
									.userAgent(USER_AGENT).timeout(180000)
									.ignoreHttpErrors(true)
									.followRedirects(true).get();
						} catch (IOException e)
						{
							e.printStackTrace();
						}
					}
				}
			});
			threads[i].start();
		}

		while (masih)
		{
			masih = false;
			for (int i = 0; i < jumlahHalaman; i++)
			{
				if (threads[i].isAlive())
				{
					masih = true;
					break;
				}
			}
		}

		for (int i = 0; i < jumlahHalaman; i++)
		{
			Document document = documents[i];
			Elements elements = document.select(Berita.TAG_JUDUL);
			for (int j = 0; j < elements.size(); j++)
			{
				Berita berita = new Berita();
				berita.setJudul(elements.get(j).text());
				beritaArrayList.add(berita);
			}

			elements = document.select(Berita.TAG_ALAMAT);
			for (int j = 0; j < elements.size(); j++)
			{
				beritaArrayList.get(j + jumlahBerita).setAlamat(
						elements.get(j).attr("href"));
			}

			elements = document.select(Berita.TAG_GAMBAR);
			for (int j = 0; j < elements.size(); j++)
			{
				beritaArrayList.get(j + jumlahBerita).setGambar(
						elements.get(j).attr("src"));
			}

			elements = document.select(Berita.TAG_TANGGAL);
			for (int j = 0; j < elements.size(); j++)
			{
				beritaArrayList.get(j + jumlahBerita).setTanggal(
						elements.get(j).text());
			}

			elements = document.select(Berita.TAG_DESKRIPSI);
			for (int j = 0; j < elements.size(); j++)
			{
				beritaArrayList.get(j + jumlahBerita).setDeskripsi(
						elements.get(j).text());
			}

			jumlahBerita = beritaArrayList.size();
		}

		return beritaArrayList;
	}

	public static String getIsi(String url)
	{
		Document document = null;
		while (document == null)
		{
			try
			{
				document = Jsoup.connect(url).userAgent(USER_AGENT)
						.timeout(180000).ignoreHttpErrors(true)
						.followRedirects(true).get();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		Elements elements = document.select(Berita.TAG_ISI);
		Document tmp = new Document("");
		String html = elements.get(0).text();
		tmp.append(html);
		tmp.outputSettings().escapeMode(Entities.EscapeMode.extended);
		tmp.outputSettings().charset("ASCII");

		return tmp.toString();
	}
}