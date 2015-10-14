package id.zelory.tanipedia.api;

import id.zelory.tanipedia.model.Jawaban;
import id.zelory.tanipedia.model.JawabanTampil;
import id.zelory.tanipedia.model.Soal;
import id.zelory.tanipedia.model.SoalTampil;
import id.zelory.tanipedia.util.DBHelper;
import id.zelory.tanipedia.util.StringUtils;
import id.zelory.tanipedia.util.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
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
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
			ArrayList<Soal> soal = DBHelper.ambilSoal(30);
			ArrayList<SoalTampil> soalList = new ArrayList<SoalTampil>();

			for (Soal s : soal)
			{
				SoalTampil st = new SoalTampil();
				st.setId(s.getId().getName());
				st.setNama(DBHelper.ambilPakTani(s.getEmail()).getNama());
				st.setIsi(s.getIsi());
				st.setTanggal(StringUtils.ubahTanggal(s.getTanggal().toString()
						.substring(0, 10)));
				soalList.add(st);
			}

			String JSON = mapper.writeValueAsString(soalList);

			resp.getWriter().println(JSON);
		}
	}

	@SuppressWarnings("serial")
	public static class KirimSoal extends HttpServlet
	{
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException
		{
			resp.setContentType("application/json; charset=utf-8");
			String email = req.getParameter("email");
			String isi = req.getParameter("isi");
			Date now = Utils.getTodayDate();
			Key id = KeyFactory.createKey("Soal", email + now.toString());

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
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
			String idSoal = req.getParameter("idSoal");
			Key id = KeyFactory.createKey("Soal", idSoal);
			ArrayList<Jawaban> jawaban = DBHelper.ambilJawaban(id);
			ArrayList<JawabanTampil> jawabanList = new ArrayList<JawabanTampil>();
			System.out.println(idSoal);
			for (Jawaban j : jawaban)
			{
				JawabanTampil jt = new JawabanTampil();
				jt.setIdSoal(j.getIdSoal().getName());
				jt.setNama(DBHelper.ambilPakTani(j.getEmail()).getNama());
				jt.setIsi(j.getIsi());
				jt.setTanggal(StringUtils.ubahTanggal(j.getTanggal().toString()
						.substring(0, 10)));
				jawabanList.add(jt);
			}
			
			String JSON = mapper.writeValueAsString(jawabanList);

			resp.getWriter().println(JSON);
		}
	}

	@SuppressWarnings("serial")
	public static class KirimJawaban extends HttpServlet
	{
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException
		{
			resp.setContentType("application/json; charset=utf-8");
			String idSoal = req.getParameter("idSoal");
			Key id = KeyFactory.createKey("Soal", idSoal);
			String email = req.getParameter("email");
			String isi = req.getParameter("isi");
			Date now = Utils.getTodayDate();

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
