package id.zelory.tanipedia.api;

import id.zelory.tanipedia.util.Scraper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
public class KomoditasAPI extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("application/json; charset=utf-8");
		ObjectMapper mapper = new ObjectMapper();
		String JSON = mapper.writeValueAsString(Scraper.ambilKomoditas());

		resp.getWriter().println(JSON);
	}
}
