package ng.bayue.util.net;

import java.io.InputStream;

public class CharsetInputStream {

	private String charset;

	private InputStream is;

	public CharsetInputStream() {
	}

	public CharsetInputStream(InputStream is, String charset) {
		this.is = is;
		this.charset = charset;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

}
