
// from hw6

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class WebTree {
	public WebNode root;
	private ArrayList<Keyword> lst;
	
	
	public WebTree(WebPage rootPage){
		this.root = new WebNode(rootPage);
		this.lst = new ArrayList<Keyword>();
		
	}
	/*
	 * return the keyword that can be sorted in the main 
	 */
	
	public ArrayList<Keyword> getKeywordList() {
		return this.lst;
	}
	
	
	public void add(Keyword keyword){
		lst.add(keyword);
		System.out.println("Done");
    }
	
	
	public void setPostOrderScore(ArrayList<Keyword> keywords) throws IOException{
		setPostOrderScore(root, keywords);
	}
	
	private void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException{
		for(WebNode child : startNode.children){
			setPostOrderScore(child,keywords);
			
		}
		startNode.setNodeScore(keywords);
		}
	
	public void eularPrintTree(){
		eularPrintTree(root);
	}
	

		
	
	public void eularPrintTree(WebNode startNode){
		int nodeDepth = startNode.getDepth();
		
		if(nodeDepth > 1) System.out.print("\n" + repeat("\t", nodeDepth-1));
		System.out.print("(");
		System.out.print(startNode.webPage.name+","+startNode.nodeScore);
		
		/*
		 * add to the keyword list
		 */				
		lst.add(new Keyword(startNode.webPage.name, startNode.nodeScore));

		for(WebNode child : startNode.children){
			eularPrintTree(child);
		}
		
		System.out.print(")");
		
		/*for example
		(Soslab,459.0
				(Publication,286.2)
				(Projects,42.0
						(Stranger,0.0)
				)
				(MEMBER,12.0)
				(Course,5.3999999999999995)
		)
		*/
		if(startNode.isTheLastChild()) System.out.print("\n" + repeat("\t", nodeDepth-2));
		
	}
	
	private String repeat(String str,int repeat){
		String retVal  = "";
		for(int i=0;i<repeat;i++){
			retVal+=str;
		}
		return retVal;
	}

}
