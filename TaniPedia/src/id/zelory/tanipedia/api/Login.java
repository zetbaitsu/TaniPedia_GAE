package id.zelory.tanipedia.api;

import id.zelory.tanipedia.model.PakTani;
import id.zelory.tanipedia.util.DBHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
public class Login extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("application/json; charset=utf-8");

		String email = req.getParameter("email");
		String password = req.getParameter("pass");
		ObjectMapper mapper = new ObjectMapper();
		String JSON = mapper.writeValueAsString(DBHelper.ambilPakTani(email));
		if (DBHelper.ambilPakTani(email).getPassword() == null
				|| !DBHelper.ambilPakTani(email).getPassword().equals(password))
		{
			PakTani pakTani = new PakTani();
			JSON = mapper.writeValueAsString(pakTani);
		} else
		{
			JSON = mapper.writeValueAsString(DBHelper.ambilPakTani(email));
			
		}
		resp.getWriter().println(JSON);
	}
}
