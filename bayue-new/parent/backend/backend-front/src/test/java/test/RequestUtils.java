package test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.PostMethod;

public class RequestUtils {

	private static HttpClient client = new HttpClient();
	private static PostMethod method = new PostMethod();

	private String url;

//	public RequestUtils(String url) {
//		this.url = url;
//	}
	
	public static String requester(String url) throws HttpException, IOException {
		try {
			URI uri = new URI(url, false);
			method.setURI(uri);
			int status = client.executeMethod(method);
//			System.out.println(status);
//			if (200 != status) return null;
//			String resStr = method.getResponseBodyAsString();
//			return resStr;
			InputStream is = method.getResponseBodyAsStream();
			byte[] b=new byte[1024];
			String line = null;
			int len = 0;
			StringBuffer res = new StringBuffer();
			while(-1 != (len = is.read(b))){
				line = new String(b,0,len);
				res.append(line);
			}
			return res.toString();
		} catch (HttpException e) {
			throw new HttpException();
		} catch (IOException e) {
			throw new IOException();
		}
	}
	
	public static void main(String[] args) {
//		String url="http://www.kuaidi100.com/all/";
		String url="http://www.baidu.com";
		String str;
		try {
			str = RequestUtils.requester(url);
			System.out.println(str);
		} catch (HttpException e) {
		} catch (IOException e) {
		}
	}

}
