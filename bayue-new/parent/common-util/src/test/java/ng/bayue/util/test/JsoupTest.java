package ng.bayue.util.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {
	
	public static void test(){
		try {
			URL url = new URL("http://xh.5156edu.com/html/1.html");
			Document document = Jsoup.parse(url, 3000);
			String doc = document.toString();
			System.out.println(doc);
		} catch (IOException e) {
		}
	}
	
	public static void main(String[] args) {
		test();
	}
	

}
