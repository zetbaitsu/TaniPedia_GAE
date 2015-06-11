package id.zelory.tanipedia.api;

import id.zelory.tanipedia.model.PakTani;
import id.zelory.tanipedia.util.DBHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Register extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("application/json; charset=utf-8");
		
		String email = req.getParameter("email");
		String nama = req.getParameter("nama");
		String password = req.getParameter("pass");
		
		PakTani pakTani = new PakTani();
		pakTani.setEmail(email);
		pakTani.setNama(nama);
		pakTani.setPassword(password);
		
		if (DBHelper.ambilPakTani(email).getEmail() == null)
		{
			DBHelper.simpan(pakTani);
			resp.getWriter().println("{\"status\": \"Berhasil\"}");
		}
		else
		{
			resp.getWriter().println("{\"status\": \"Gagal\"}");
		}
	}
}
