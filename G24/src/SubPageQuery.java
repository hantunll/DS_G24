import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SubPageQuery {
	public String url;
	public String content;

	public SubPageQuery(String url)
	{
 		this.url = url;
	}
	
	private String fetchContent() throws IOException, FileNotFoundException
	{
		String retVal = "";
		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
		InputStream in = conn.getInputStream();
		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null; 
		
		while((line=bufReader.readLine()) != null)
		{
			retVal += line;
		}
		return retVal;
	}
	
	public HashMap<String, String> query() throws IOException, FileNotFoundException
	{
		if(content == null)
		{
			content= fetchContent();
		}

		HashMap<String, String> retVal = new HashMap<String, String>();
		
		Document doc = Jsoup.parse(content);
		//System.out.println(doc.text());
		Elements lis = doc.select("div");
	//	lis = lis.select(".kCrYT");
				
		for(Element li : lis)
		{
			try 
			{
				String citeUrl = li.select("a").get(0).attr("href");
				String title = li.select("a").get(0).select(".vvjwJb").text();
				//System.out.println("The sub page query title" + title);
				retVal.put(title, citeUrl);
			} 
			
			catch (IndexOutOfBoundsException e) 
			{
//				e.printStackTrace();
			}
		}
		return retVal;
	}
	
//	public HashMap<String, String> query() throws IOException, FileNotFoundException
//	{
//		if(content == null)
//		{
//			content= fetchContent();
//		}
//
//		HashMap<String, String> retVal = new HashMap<String, String>();
//		
//		Document doc = Jsoup.parse(content);
//		//System.out.println(doc.text());
//		Elements lis = doc.select("div");
//	//	lis = lis.select(".kCrYT");
//		try {
//			String citeUrl = lis.get(0).select("a").get(0).attr("href");
//			String title = lis.get(0).select("a").get(0).select(".vvjwJb").text();
//			retVal.put(title, citeUrl);
//			System.out.println("The sub page query title" + title);
//		} catch (IndexOutOfBoundsException e) 
//		{
//			//	e.printStackTrace();
//		}
//		
//		
//		return retVal;
//	}
//	

}
