package ng.bayue.util.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ng.bayue.constants.CharsetConstant;
import ng.bayue.constants.RequestMethod;

public class URLUtil {

	private static final Logger logger = LoggerFactory.getLogger(URLUtil.class);

	public static InputStream url(String spec) {
		try {
			URL url = new URL(spec);
			URLConnection conn = url.openConnection();
			HttpURLConnection hcon = (HttpURLConnection) conn;

			hcon.setRequestMethod(RequestMethod.POST.toString());
			hcon.setRequestProperty("Charsert", CharsetConstant.UTF8);
			// hcon.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			// hcon.setRequestProperty("content-type", "text/html");
			// hcon.setRequestProperty("connection", "Keep-Alive");
			// hcon.setDoInput(true);

//			hcon.connect();
			InputStream is = hcon.getInputStream();

//			String result = "";
//			byte[] bytes = new byte[1024];
//			int len = 0;
//			while (-1 != (len = is.read(bytes))) {
//				result += new String(bytes, 0, len);
//			}
//			System.out.println(result);

			return is;

		} catch (MalformedURLException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		}
		return null;
	}

	public static void main(String[] args) {
		String str1 = "https://www.kuaidi100.com/all/";
		url(str1);
	}

}
