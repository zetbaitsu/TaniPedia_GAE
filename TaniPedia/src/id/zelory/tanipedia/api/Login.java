package id.zelory.tanipedia.api;

import id.zelory.tanipedia.util.DBHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Login extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("application/json; charset=utf-8");

		String email = req.getParameter("email");
		String password = req.getParameter("pass");

		if (DBHelper.ambilPakTani(email).getPassword() == null
				|| !DBHelper.ambilPakTani(email).getPassword().equals(password))
		{
			resp.getWriter().println("{\"status\": \"Gagal\"}");
		} else
		{
			resp.getWriter().println("{\"status\": \"Berhasil\"}");
		}
	}
}
