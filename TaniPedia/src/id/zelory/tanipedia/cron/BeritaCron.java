package id.zelory.tanipedia.cron;

import id.zelory.tanipedia.model.Berita;
import id.zelory.tanipedia.util.DBHelper;
import id.zelory.tanipedia.util.Scraper;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
public class BeritaCron extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("application/json; charset=utf-8");
		final ArrayList<Berita> beritaArrayList = Scraper.ambilListBerita(
				Berita.URL_UTAMA, 5);
		
		for (Berita berita : beritaArrayList)
		{
			DBHelper.simpan(berita);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String JSON = mapper.writeValueAsString(beritaArrayList);
		resp.getWriter().println(JSON);
	}
}
