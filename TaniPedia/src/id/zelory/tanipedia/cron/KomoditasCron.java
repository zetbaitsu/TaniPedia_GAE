package id.zelory.tanipedia.cron;

import id.zelory.tanipedia.model.Komoditas;
import id.zelory.tanipedia.util.DBHelper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.appengine.api.ThreadManager;

@SuppressWarnings("serial")
public class KomoditasCron extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("application/json; charset=utf-8");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

		List<Komoditas> komoditas = ambilKomoditas();

		for (Komoditas k : komoditas)
		{
			DBHelper.simpan(k);
		}
		
		String JSON = mapper.writeValueAsString(komoditas);
		resp.getWriter().println(JSON);
	}

	private List<Komoditas> getBeras()
	{
		List<Komoditas> komoditas = new ArrayList<Komoditas>();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null, data = null;

		try
		{
			root = mapper.readTree(new URL(Komoditas.API_BERAS));
		} catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (root != null)
		{
			data = root.findValue("aaData");
		}

		if (data != null)
		{
			if (!data.get(0).get(2).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Beras Medium Grosir");
				k.setHarga(data.get(0).get(2).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(3).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Beras Medium Eceran");
				k.setHarga(data.get(0).get(3).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(4).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Beras Premium Grosir");
				k.setHarga(data.get(0).get(4).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(5).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Beras Premium Eceran");
				k.setHarga(data.get(0).get(5).asText());
				komoditas.add(k);
			}
		}

		return komoditas;
	}

	private List<Komoditas> getPalawija()
	{
		List<Komoditas> komoditas = new ArrayList<Komoditas>();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null, data = null;

		try
		{
			root = mapper.readTree(new URL(Komoditas.API_PALAWIJA));
		} catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (root != null)
		{
			data = root.findValue("aaData");
		}

		if (data != null)
		{
			if (!data.get(0).get(2).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Jagung Grosir");
				k.setHarga(data.get(0).get(2).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(3).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Jagung Eceran");
				k.setHarga(data.get(0).get(3).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(4).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Kedelai Grosir");
				k.setHarga(data.get(0).get(4).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(5).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Kedelai Eceran");
				k.setHarga(data.get(0).get(5).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(6).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Kacang Tanah Grosir");
				k.setHarga(data.get(0).get(6).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(7).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Kacang Tanah Eceran");
				k.setHarga(data.get(0).get(7).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(8).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Kacang Hijau Grosir");
				k.setHarga(data.get(0).get(8).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(9).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Kacang Hijau Eceran");
				k.setHarga(data.get(0).get(9).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(10).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Ubi Kayu Grosir");
				k.setHarga(data.get(0).get(10).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(11).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Ubi Kayu Eceran");
				k.setHarga(data.get(0).get(11).asText());
				komoditas.add(k);
			}
		}

		return komoditas;
	}

	private List<Komoditas> getSayuran()
	{
		List<Komoditas> komoditas = new ArrayList<Komoditas>();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null, data = null;

		try
		{
			root = mapper.readTree(new URL(Komoditas.API_SAYURAN));
		} catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (root != null)
		{
			data = root.findValue("aaData");
		}

		if (data != null)
		{
			if (!data.get(0).get(2).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Kol Bulat");
				k.setHarga(data.get(0).get(2).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(3).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Kol Gepeng");
				k.setHarga(data.get(0).get(3).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(4).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Wortel");
				k.setHarga(data.get(0).get(4).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(5).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Tomat");
				k.setHarga(data.get(0).get(5).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(6).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Cabe Merah Besar");
				k.setHarga(data.get(0).get(6).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(7).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Cabe Merah Kriting");
				k.setHarga(data.get(0).get(7).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(8).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Bawang Merah");
				k.setHarga(data.get(0).get(8).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(9).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Bawang Putih Impor");
				k.setHarga(data.get(0).get(9).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(10).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Kentang Mutu ABC");
				k.setHarga(data.get(0).get(10).asText());
				komoditas.add(k);
			}
		}

		return komoditas;
	}

	private List<Komoditas> getBuah()
	{
		List<Komoditas> komoditas = new ArrayList<Komoditas>();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null, data = null;

		try
		{
			root = mapper.readTree(new URL(Komoditas.API_BUAH));
		} catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (root != null)
		{
			data = root.findValue("aaData");
		}

		if (data != null)
		{
			if (!data.get(0).get(2).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Jeruk Siam Grosir");
				k.setHarga(data.get(0).get(2).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(3).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Jeruk Siam Eceran");
				k.setHarga(data.get(0).get(3).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(4).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Pisang Grosir");
				k.setHarga(data.get(0).get(4).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(5).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Pisang Eceran");
				k.setHarga(data.get(0).get(5).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(6).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Mangga Grosir");
				k.setHarga(data.get(0).get(6).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(7).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Mangga Eceran");
				k.setHarga(data.get(0).get(7).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(8).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Manggis Grosir");
				k.setHarga(data.get(0).get(8).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(9).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Manggis Eceran");
				k.setHarga(data.get(0).get(9).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(10).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Nanas Grosir");
				k.setHarga(data.get(0).get(10).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(11).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Nanas Eceran");
				k.setHarga(data.get(0).get(11).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(12).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Salak Grosir");
				k.setHarga(data.get(0).get(12).asText());
				komoditas.add(k);
			}

			if (!data.get(0).get(13).asText().equals("0"))
			{
				Komoditas k = new Komoditas();
				k.setNama("Salak Eceran");
				k.setHarga(data.get(0).get(13).asText());
				komoditas.add(k);
			}
		}

		return komoditas;
	}

	private List<Komoditas> ambilKomoditas()
	{
		List<Komoditas> komoditas = new ArrayList<Komoditas>();
		Thread threads[] = new Thread[4];
		ThreadFactory factory = ThreadManager.currentRequestThreadFactory();
		final ArrayList<List<Komoditas>> tmp = new ArrayList<List<Komoditas>>();

		for (int i = 0; i < 4; i++)
		{
			final int x = i;
			threads[i] = factory.newThread(new Runnable()
			{
				@Override
				public void run()
				{
					switch (x)
					{
						case 0:
							tmp.add(getBeras());
							break;
						case 1:
							tmp.add(getPalawija());
							break;
						case 2:
							tmp.add(getSayuran());
							break;
						case 3:
							tmp.add(getBuah());
							break;
						default:
							break;
					}
				}
			});
			threads[i].start();
		}

		while (threads[0].isAlive() || threads[1].isAlive()
				|| threads[2].isAlive() || threads[3].isAlive())
			;

		for (int i = 0; i < 4; i++)
		{
			try
			{
				komoditas.addAll(tmp.get(i));
			} catch (Exception e)
			{

			}
		}

		return komoditas;
	}
}
