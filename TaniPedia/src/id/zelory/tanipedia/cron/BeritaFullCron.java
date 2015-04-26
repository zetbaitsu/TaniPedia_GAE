package id.zelory.tanipedia.cron;

import id.zelory.tanipedia.model.Berita;
import id.zelory.tanipedia.util.DBHelper;
import id.zelory.tanipedia.util.Scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.ThreadManager;

@SuppressWarnings("serial")
public class BeritaFullCron extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("application/json; charset=utf-8");
		final ArrayList<Berita> beritaArrayList = DBHelper.ambilBeritaKosong(3);

		if (!beritaArrayList.isEmpty())
		{
			boolean masih = true;
			Thread threads[] = new Thread[beritaArrayList.size()];
			ThreadFactory factory = ThreadManager.currentRequestThreadFactory();

			for (int i = 0; i < beritaArrayList.size(); i++)
			{
				final int x = i;
				threads[i] = factory.newThread(new Runnable()
				{
					@Override
					public void run()
					{
						beritaArrayList.get(x).setIsi(
								Scraper.getIsi(beritaArrayList.get(x)
										.getAlamat()));
					}
				});
				threads[i].start();
			}

			while (masih)
			{
				masih = false;
				for (int i = 0; i < beritaArrayList.size(); i++)
				{
					if (threads[i].isAlive())
					{
						masih = true;
						break;
					}
				}
			}

			for (Berita berita : beritaArrayList)
			{
				DBHelper.simpan(berita);
			}

			ObjectMapper mapper = new ObjectMapper();
			String JSON = mapper.writeValueAsString(beritaArrayList);
			resp.getWriter().println(JSON);
		}
		else
		{
			resp.getWriter().println("Semua berita sudah diambil :)");
		}
	}
}
