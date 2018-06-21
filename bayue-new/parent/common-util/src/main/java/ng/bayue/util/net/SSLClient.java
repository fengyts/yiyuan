package ng.bayue.util.net;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * SSLClient
 * @author sk.chen
 * @version 2017-05-23
 *
 */
public class SSLClient {
	public static CloseableHttpClient registerSSL()
		    throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sslContext = SSLContext.getInstance("TLS");
		X509TrustManager tm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain,String authType) 
					throws java.security.cert.CertificateException {
			   
			}
			public void checkServerTrusted(X509Certificate[] chain,String authType) 
	    		  throws java.security.cert.CertificateException {
				if ((chain == null) || (chain.length == 0)) {
					throw new IllegalArgumentException("证书链为空或长度为零");
				}
		        if ((authType == null) || (authType.length() == 0)) {
		        	throw new IllegalArgumentException("认证类型为空或长度为零");
		        }
		        boolean br = false;
		        Principal principal = null;
		        for (X509Certificate x509Certificate : chain) {
		        	principal = x509Certificate.getSubjectX500Principal();
		        	if (principal != null) {
		        		br = true;
		        		return;
		        	}
		        }
		        if (!(br)) {
		        	 throw new CertificateException("服务器证书验证失败");
		        }
			}
	
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}
	    };
	    sslContext.init(null, new TrustManager[] { tm }, new SecureRandom());
	    SSLConnectionSocketFactory sslcsf = new SSLConnectionSocketFactory(sslContext);
		
	    return HttpClients.custom().setSSLSocketFactory(sslcsf).build();
	}
}
