package id.zelory.tanipedia.api;

import id.zelory.tanipedia.util.DBHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
public class BeritaAPI extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("application/json; charset=utf-8");
		String url = req.getParameter("url");
		ObjectMapper mapper = new ObjectMapper();
		String JSON;

		if (url == null)
		{
			JSON = mapper.writeValueAsString(DBHelper.ambilBerita(20, false));
		} else
		{
			JSON = mapper.writeValueAsString(DBHelper.ambilBerita(url, true));
		}

		resp.getWriter().println(JSON);
	}
}
