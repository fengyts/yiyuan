package ng.bayue.util.net;

import java.io.InputStream;

public abstract class CharsetInputStream extends InputStream {

	private String charset;

	public CharsetInputStream() {
		super();
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

}
