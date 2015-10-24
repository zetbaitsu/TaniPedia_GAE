package id.zelory.tanipedia.api;

import id.zelory.tanipedia.model.Notifikasi;
import id.zelory.tanipedia.model.NotifikasiTampil;
import id.zelory.tanipedia.model.SoalTampil;
import id.zelory.tanipedia.util.DBHelper;
import id.zelory.tanipedia.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@SuppressWarnings("serial")
public class NotifikasiAPI extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("application/json; charset=utf-8");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		String emailPenanya = req.getParameter("emailPenanya");
		ArrayList<Notifikasi> notifikasi = DBHelper.ambilNotifikasi(
				emailPenanya, 30);
		ArrayList<NotifikasiTampil> notifikasiList = new ArrayList<NotifikasiTampil>();
		System.out.println(emailPenanya);
		for (Notifikasi n : notifikasi)
		{
			if (!n.getEmail().equals(emailPenanya))
			{
				NotifikasiTampil nt = new NotifikasiTampil();
				nt.setId(n.getId().getName());
				nt.setSoal(new SoalTampil(DBHelper.ambilSoal(n.getIdSoal())));
				nt.setPakTani(DBHelper.ambilPakTani(n.getEmail()));
				nt.setIsi(n.getIsi());
				nt.setTanggal(StringUtils.ubahTanggal(n.getTanggal().toString()
						.substring(0, 10)));
				notifikasiList.add(nt);
			}
		}

		String JSON = mapper.writeValueAsString(notifikasiList);

		resp.getWriter().println(JSON);
	}
}
