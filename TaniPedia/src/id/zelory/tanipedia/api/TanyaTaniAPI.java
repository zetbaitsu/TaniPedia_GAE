package id.zelory.tanipedia.api;

import id.zelory.tanipedia.model.Jawaban;
import id.zelory.tanipedia.model.Soal;
import id.zelory.tanipedia.util.DBHelper;
import id.zelory.tanipedia.util.Utils;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class TanyaTaniAPI
{
	@SuppressWarnings("serial")
	public static class AmbilSoal extends HttpServlet
	{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException
		{
			resp.setContentType("application/json; charset=utf-8");
			ObjectMapper mapper = new ObjectMapper();
			String JSON = mapper.writeValueAsString(DBHelper.ambilSoal(30));

			resp.getWriter().println(JSON);
		}
	}
	
	@SuppressWarnings("serial")
	public static class KirimSoal extends HttpServlet
	{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException
		{
			resp.setContentType("application/json; charset=utf-8");
			String email = req.getParameter("email");
			String isi = req.getParameter("isi");
			Date now  = Utils.getTodayDate();
			Key id = KeyFactory.createKey("Soal",email + now.toString());
			
			Soal soal = new Soal();
			soal.setEmail(email);
			soal.setIsi(isi);
			soal.setId(id);
			soal.setTanggal(now);
			
			DBHelper.simpan(soal);
			
			resp.getWriter().println("{\"status\": \"Berhasil\"}");
		}
	}
	
	@SuppressWarnings("serial")
	public static class AmbilJawaban extends HttpServlet
	{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException
		{
			resp.setContentType("application/json; charset=utf-8");
			ObjectMapper mapper = new ObjectMapper();
			String idSoal = req.getParameter("idSoal");
			Key id = KeyFactory.createKey("Soal",idSoal);
			String JSON = mapper.writeValueAsString(DBHelper.ambilJawaban(id));
			
			resp.getWriter().println(JSON);
		}
	}
	
	@SuppressWarnings("serial")
	public static class KirimJawaban extends HttpServlet
	{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException
		{
			resp.setContentType("application/json; charset=utf-8");
			String idSoal = req.getParameter("idSoal");
			Key id = KeyFactory.createKey("Soal",idSoal);
			String email = req.getParameter("email");
			String isi = req.getParameter("isi");
			Date now  = Utils.getTodayDate();
			
			Jawaban jawaban = new Jawaban();
			jawaban.setIdSoal(id);
			jawaban.setEmail(email);
			jawaban.setTanggal(now);
			jawaban.setIsi(isi);
			
			DBHelper.simpan(jawaban);
			
			resp.getWriter().println("{\"status\": \"Berhasil\"}");
		}
	}
}
