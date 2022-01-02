import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class Main {

	public static void main(String[] args) throws IOException, FileNotFoundException {
			/*
			 *  first step: add the keyword related to the e-book
			 */
			
			ArrayList<Keyword> keywordList = new ArrayList<Keyword>();
			keywordList.add(new Keyword("epub", 5));
			keywordList.add(new Keyword("txt", 5));
			keywordList.add(new Keyword("ebook", 5));
			keywordList.add(new Keyword("book", 5));
			keywordList.add(new Keyword("books", 5));
			keywordList.add(new Keyword("amazon", 4));
			keywordList.add(new Keyword("pdf", 4));
			keywordList.add(new Keyword("free", 3));
			keywordList.add(new Keyword("github", 2));
			keywordList.add(new Keyword("fiction book", 2));
			keywordList.add(new Keyword("online", 2));
			
			// the spcae will become %20 ex: harry%20potter
			//System.out.println(new GoogleQuery("harry%20potter%20epub").query());
			/*
			 *  the word that user want to search in our search engine
			 */
			GoogleQuery g = new GoogleQuery("Holmes%20book");
			g.query();
			
			/*
			 * for the web score sort
			 */

			KeywordList lst = new KeywordList();

			
			for(String title: g.query().keySet()) {

				String webUrl = g.query().get(title);
				if(webUrl.contains("&sa")) {
					webUrl = webUrl.substring(7, webUrl.indexOf("&sa"));
				}
				System.out.println(webUrl);					
				/*
				 * Construct the tree node like HW6
				 */
				WebPage rootPage = new WebPage(webUrl, title);
				WebTree tree = new WebTree(rootPage);
				/*
				 * use the for loop to get the sub-url in the main url
				 * the parameter is subWebUrl(webUrl) and subWebName(title)
				 */				
				try {
					SubPageQuery subWebPageSearch = new SubPageQuery(webUrl);
					//System.out.print("The subWebPageTitle" + subWebPageSearch.query().get);

					if(subWebPageSearch.query() != null) {
						for(String subWebPageTitle: subWebPageSearch.query().keySet()) {
							//System.out.print("The subWebPageTitle" + subWebPageTitle);

							String subWebUrl = subWebPageSearch.query().get(subWebPageTitle);
							//System.out.print(subWebUrl);
							if(subWebUrl.contains("&sa")) {
								subWebUrl = subWebUrl.substring(7, subWebUrl.indexOf("&sa"));
							} else {
								subWebUrl = subWebUrl;
							}
							tree.root.addChild(new WebNode(new WebPage(subWebUrl,subWebPageTitle)));
						}
					} else {
						System.out.println("Wrong");
					}
				} catch (FileNotFoundException e) {
					
				} catch(IOException e) {

				}

				tree.setPostOrderScore(keywordList);
				tree.eularPrintTree();
				
				ArrayList<Keyword> kWord = new ArrayList<Keyword>();
				kWord = tree.getKeywordList();
				lst.add(kWord.get(0));
				System.out.println("---------");
			
			}
			/*
			 * sort the webPage
			 */
			lst.sort();	
			System.out.println("++++++++++++++++++++");

			// lst.output();
//			System.out.println(lst.getKeywordList().get(0).name);
//			System.out.println(lst.getKeywordList().get(0).weight);
			
			// to get the url (which is not modified yet)
//			System.out.println(g.query().get(lst.getKeywordList().get(0).name));

			//StringBuilder sb = new StringBuilder();
			String[][] s = new String[g.query().size()][2];

			int num = 0;
			for(int i = lst.getKeywordList().size()-1; i>=0 ; i--){

				Keyword k = lst.getKeywordList().get(i);
				String name = k.name;
			    String sWeight = String.valueOf(k.weight);
			    String url = g.query().get(lst.getKeywordList().get(i).name);
			    s[num][0] = name;
			    s[num][1] = url;
				num++;
			}

			
	}
	
	

}
