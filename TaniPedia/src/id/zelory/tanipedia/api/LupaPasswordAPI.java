package id.zelory.tanipedia.api;

import id.zelory.tanipedia.model.PakTani;
import id.zelory.tanipedia.util.DBHelper;
import id.zelory.tanipedia.util.RandomString;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LupaPasswordAPI extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("application/json; charset=utf-8");
		String email = req.getParameter("email");

		PakTani pakTani = DBHelper.ambilPakTani(email);
		RandomString randomString = new RandomString(6);
		pakTani.setPassword(randomString.nextString());
		DBHelper.simpan(pakTani);
		if (pakTani.getNama() == null)
		{
			resp.getWriter().println("{\"status\": \"Akun tidak ditemukan\"}");
		} else
		{
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);

			String msgBody = "Halo "
					+ pakTani.getNama()
					+ "\n\n"
					+ "Anda baru saja mengirimkan permintaan bahwa anda lupa password akun TaniPedia milik anda, maka dari itu kami telah mengatur ulang password anda dengan teks berikut :\n"
					+ "\nPassword baru anda : "
					+ pakTani.getPassword()
					+ "\n\n"
					+ "Silahkan masukan kembali password diatas pada halaman login TaniPedia, kemudian ubah password tersebut pada halaman edit profil demi keamanan akun anda. Terimakasih.\n\n"
					+ "Salam\n\nAdmin TaniPedia.";
			System.out.println(msgBody);

			boolean berhasil = true;

			try
			{
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress("zetlogger@gmail.com",
						"Admin TaniPedia"));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						pakTani.getEmail(), pakTani.getNama()));
				msg.setSubject("Lupa Password Akun TaniPedia");
				msg.setText(msgBody);
				Transport.send(msg);

			} catch (AddressException e)
			{
				berhasil = false;
				resp.getWriter().println("{\"status\": \"Gagal\"}");
				e.printStackTrace();
			} catch (MessagingException e)
			{
				berhasil = false;
				resp.getWriter().println("{\"status\": \"Gagal\"}");
				e.printStackTrace();
			}

			if (berhasil)
				resp.getWriter().println("{\"status\": \"Berhasil\"}");
		}
	}
}
