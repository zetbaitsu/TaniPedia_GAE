package id.zelory.tanipedia.servlet;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class TaniPediaServlet extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException
	{
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
