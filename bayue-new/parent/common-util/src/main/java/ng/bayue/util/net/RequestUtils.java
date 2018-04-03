package ng.bayue.util.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ng.bayue.constants.CharsetConstant;

public class RequestUtils {

	private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

	public static String doRequestReturnStr(String url, List<NameValuePair> parameters, String charset) {
		StringBuilder resultData = new StringBuilder();
		if (StringUtils.isBlank(charset)) {
			charset = CharsetConstant.UTF8;
		}
		CharsetInputStream cis = doRequest(url, parameters, charset);
		String ccharset = cis.getCharset();
		if (StringUtils.isNotBlank(ccharset)) {
			charset = ccharset;
		}
		try {
			final Reader reader = new InputStreamReader(cis.getIs(), charset);
			final char[] cbuf = new char[1024];
			int len = 0;
			while (-1 != (len = reader.read(cbuf))) {
				resultData.append(cbuf, 0, len);
			}

			return resultData.toString();
		} catch (UnsupportedEncodingException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		}
		return null;

	}

	public static CharsetInputStream doRequest(String url, List<NameValuePair> parameters, String charset) {
		try {
			HttpClientBuilder builder = HttpClientBuilder.create();
			HttpClient httpClient = builder.build();
			HttpPost httpPost = new HttpPost(url);

			RequestConfig config = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(5000)
					.setConnectionRequestTimeout(5000).setExpectContinueEnabled(false).build();
			httpPost.setConfig(config);

			Charset cs = null;
			if (StringUtils.isBlank(charset)) {
				charset = CharsetConstant.UTF8;
				// cs = Charset.forName(charset);
			}
			if(CollectionUtils.isNotEmpty(parameters)){
				httpPost.setEntity(new UrlEncodedFormEntity(parameters, cs));
			}
			Header header = new BasicHeader("Content-Type", "text/html;charset=UTF-8");
			httpPost.setHeader(header);

			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();

			ContentType contentType = ContentType.get(entity);
			try {
				if (null != contentType) {
					cs = contentType.getCharset();
				}
			} catch (UnsupportedCharsetException e) {
				throw new UnsupportedEncodingException(e.getMessage());
			}
			InputStream is = entity.getContent();

			CharsetInputStream cis = new CharsetInputStream();
			cis.setCharset(charset);
			cis.setIs(is);
			return cis;

		} catch (IOException e) {
			logger.error("", e);
		}
		return null;
	}
	
	public static String doRequest(String url, Map<String, Object> params) throws Exception {
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		if(null != params){
			for(Map.Entry<String, Object> p : params.entrySet()){
				String name = p.getKey();
				String value = (String) p.getValue();
				NameValuePair nvp = new BasicNameValuePair(name, value);
				parameters.add(nvp);
			}
		}
		HttpEntity entityParams = new UrlEncodedFormEntity(parameters, CharsetConstant.UTF8);
		post.setEntity(entityParams);
		post.setHeader("Content-Type", "text/html;charset=UTF-8");
		HttpResponse response = client.execute(post);
		HttpEntity resEntity = response.getEntity();
		String result = EntityUtils.toString(resEntity, CharsetConstant.UTF8);
		return result;
	}
	
	public static String doReqeust(String url) throws Exception {
		return doRequest(url, null);
	}


	public static void main(String[] args) {
		String str1 = "https://www.kuaidi100.com/all/";
		doRequest(str1, null, null);
	}

}
