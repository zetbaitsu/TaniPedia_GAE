package id.zelory.tanipedia.api;

import id.zelory.tanipedia.model.Cuaca;
import id.zelory.tanipedia.util.StringUtils;
import id.zelory.tanipedia.util.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@SuppressWarnings("serial")
public class CuacaAPI extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("application/json; charset=utf-8");

		String lat, lon;

		lat = req.getParameter("lat");
		lon = req.getParameter("lon");

		if (lat == null || lon == null)
		{
			lat = "-7.78";
			lon = "110.36";
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(new URL(Cuaca.API + "&lat=" + lat
				+ "&lon=" + lon));

		ArrayList<Cuaca> cuacas = new ArrayList<Cuaca>();
		String lokasi = root.findValue("city").findValue("name").asText();
		JsonNode items = root.findValue("list");
		Iterator<JsonNode> iterator = items.elements();
		while (iterator.hasNext())
		{
			JsonNode item = iterator.next();
			Cuaca cuaca = new Cuaca();
			cuaca.setLokasi(lokasi);
			Date tanggal = new Date(item.findValue("dt").asLong() * 1000);
			cuaca.setTanggal(StringUtils.ubahTanggal(tanggal.toString()
					.substring(0, 10)));
			cuaca.setSuhuMin(item.findValue("temp").findValue("min").asText());
			cuaca.setSuhuMax(item.findValue("temp").findValue("max").asText());
			cuaca.setSuhu(item.findValue("temp").findValue("day").asText());
			cuaca.setCuaca(item.findValue("weather").get(0).findValue("main")
					.asText());
			cuaca.setTekanan(item.findValue("pressure").asText());
			cuaca.setKelembaban(item.findValue("humidity").asText());
			cuaca.setKecepatanAngin(item.findValue("speed").asText());
			cuaca.setArahAngin(Utils.getArahAngin(item.findValue("deg").asDouble()));
			cuacas.add(cuaca);
		}
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		String JSON = mapper.writeValueAsString(cuacas);
		resp.getWriter().println(JSON);
	}
}
