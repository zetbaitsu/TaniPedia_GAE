package id.zelory.tanipedia.api;

import id.zelory.tanipedia.model.PakTani;
import id.zelory.tanipedia.util.DBHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@SuppressWarnings("serial")
public class PakTaniAPI extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("application/json; charset=utf-8");

		String email = req.getParameter("email");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		String JSON;
		JSON = mapper.writeValueAsString(DBHelper.ambilPakTani(email));
		resp.getWriter().println(JSON);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("application/json; charset=utf-8");

		String email = req.getParameter("email");
		String nama = req.getParameter("nama");
		String password = req.getParameter("pass");
		boolean isMale = req.getParameter("male").equals("true");
		
		PakTani pakTani = new PakTani();
		pakTani.setEmail(email);
		pakTani.setNama(nama);
		pakTani.setPassword(password);
		pakTani.setMale(isMale);
		
		DBHelper.simpan(pakTani);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		String JSON;
		JSON = mapper.writeValueAsString(pakTani);
		resp.getWriter().println(JSON);
	}
}
