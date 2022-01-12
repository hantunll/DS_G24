import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLEncode {
	public URLEncode() {

	}

	public String urlEncoder(String url) {
		String encodedURL = "";
		try {
			// �i�� URL �ʤ���s�X//
			encodedURL = URLEncoder.encode(url, "UTF-8");
			System.out.println(encodedURL);
		} catch (UnsupportedEncodingException e) {
			// �ҥ~�B�z ...
		}
		return encodedURL;
	}
}
