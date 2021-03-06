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



public class GoogleQuery 

{

	public String searchKeyword;

	public String url;

	public String content;
	
	//public String subWebUrl;
	
	public String subWebName;
	
	public URLEncode en = new URLEncode();


	public GoogleQuery(String searchKeyword)

	{

//		this.searchKeyword = searchKeyword;
//
//		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=10";
		this.searchKeyword = searchKeyword;
		System.out.println(this.searchKeyword);
		String enKeyword = en.urlEncoder(searchKeyword);
		this.url = "http://www.google.com/search?q=" + enKeyword + "&oe=utf8&num=20";
	}
	/*
	 * Another constructor
	 */
	public GoogleQuery(String url, String subWebName) {
		this.url = url;
		this.subWebName = subWebName;
	}

	

	private String fetchContent() throws IOException, FileNotFoundException

	{
		String retVal = "";

		URL u = new URL(url);

		URLConnection conn = u.openConnection();

		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in,"utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line=bufReader.readLine())!=null)
		{
			retVal += line;

		}
		return retVal;
	}
	public HashMap<String, String> query() throws IOException, FileNotFoundException

	{

		if(content==null)

		{

			content= fetchContent();

		}

		HashMap<String, String> retVal = new HashMap<String, String>();
		
		Document doc = Jsoup.parse(content);
//		System.out.println(doc.text());
		Elements lis = doc.select("div");
//		 System.out.println(lis);
		lis = lis.select(".kCrYT");
//		 System.out.println(lis.size());
		
		
		for(Element li : lis)
		{
			try 

			{
				String citeUrl = li.select("a").get(0).attr("href");
				String title = li.select("a").get(0).select(".vvjwJb").text();
				if(title.equals("")) {
					continue;
				}
				//System.out.println(title + ","+citeUrl);
				retVal.put(title, citeUrl);

			} catch (IndexOutOfBoundsException e) {

//				e.printStackTrace();

			}

			

		}

		

		return retVal;

	}

}

	

	

