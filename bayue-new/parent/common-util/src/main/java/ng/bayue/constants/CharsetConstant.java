package ng.bayue.constants;

import java.nio.charset.Charset;

public final class CharsetConstant {

	public static final String UTF8 = "UTF-8";
	public static final String GBK = "GBK";
	public static final String GB2312 = "GB2312";
	public static final String ISO_8859_1 = "ISO-8859-1";
	public static final String US_ASCII = "US-ASCII";

	public static final Charset Charset_UTF8 = Charset.forName(UTF8);
	public static final Charset Charset_GBK = Charset.forName(GBK);
	public static final Charset Charset_GB2312 = Charset.forName(GB2312);
	public static final Charset Charset_ISO_8859_1 = Charset.forName(ISO_8859_1);
	public static final Charset Charset_ASCII = Charset.forName(US_ASCII);

}
