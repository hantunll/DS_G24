import java.util.ArrayList;

public class KeywordList {
	private ArrayList<Keyword> lst;
	
	public KeywordList(){
		ArrayList <Keyword> keywordList = new ArrayList<Keyword>();
		keywordList.add(new Keyword("Title of the book", 5));
		keywordList.add(new Keyword("EPUB", 4));
		keywordList.add(new Keyword("Amazon", 4));
		keywordList.add(new Keyword("E-book", 4));
		keywordList.add(new Keyword("pdf", 4));
		keywordList.add(new Keyword("free", 3));
		keywordList.add(new Keyword("github", 2));
		keywordList.add(new Keyword("fiction book", 2));
		keywordList.add(new Keyword("online", 2));
		//printKeywordList(keywordList);
		this.lst = new ArrayList<Keyword>();
    }
	
	public ArrayList<Keyword> getKeywordList() {
		return this.lst;
	}
	
	
	public void add(Keyword keyword){
		lst.add(keyword);
//		System.out.println("Done");
    }
	
	//quick sort
	public void sort(){
		if(lst.size() == 0)
		{
			System.out.println("InvalidOperation");
		}
		else 
		{
			quickSort(0, lst.size()-1);
//			System.out.println("Done");
		}

	}
	

	private void quickSort(int low, int high){
	    if(low < high){
	        int p = partition(low, high);
	        quickSort(low, p-1);
	        quickSort(p+1, high);
	    }
	}
	
	private int partition(int leftbound, int rightbound){
		int p = leftbound, j;
		for (j = leftbound+1; j<= rightbound ;j++) {
			if(lst.get(j).weight < lst.get(leftbound).weight) {
				swap(++p,j);
			}
		}
		swap(leftbound, p);
		return p;
	}
	
	private void swap(int aIndex, int bIndex){
		Keyword temp = lst.get(aIndex);
		lst.set(aIndex, lst.get(bIndex));
		lst.set(bIndex, temp);
	}
	
	public void output(){
		//TODO: write output and remove all element logic here...
		StringBuilder sb = new StringBuilder();
		
//		for(int i=0; i<lst.size();i++){
//			Keyword k = lst.get(i);
//			if(i>0)sb.append(" ");
//			sb.append(k.toString());
//		}
		for(int i=lst.size()-1; i>=0;i--){
			Keyword k = lst.get(i);
			if(i>0)sb.append(" ");
			sb.append(k.toString());

		}
		
		System.out.println(sb.toString());	
	}
	
	public void find(String s){
		int maxValue = -1;
		int maxIndex = -1;
		for(int i=0; i<lst.size(); i++){
			int lcs = findLCS(lst.get(i).name, s);
//			System.out.println(lcs);
			if(lcs > maxValue){
				maxValue = lcs;
				maxIndex = i;
			}
		}
		System.out.println(s+": "+lst.get(maxIndex).toString());
	}
	
	public int findLCS(String x, String y){
		//1. fill this method
		int len1 = x.length();
		int len2 = y.length();
		int c[][] = new int[len1+1][len2+2];
		for (int i = 0; i<=len1; i++) {
			for (int j = 0; j<=len2; j++) {
				if(i==0 | j==0) {
					c[i][j] = 0;
				} else if (x.charAt(i-1) == y.charAt(j-1)) {
					c[i][j] = c[i-1][j-1] + 1;
				} else {
					c[i][j] = Math.max(c[i-1][j], c[i][j-1]);
				}
			}
		}
		return c[len1][len2];
		
	}
	
	private void printMatrix(int[][] matrix){
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix[0].length; j++){
				System.out.print(matrix[i][j] + " ");
				if(j==matrix[0].length-1)System.out.print("\n");
			}
		}
	}
}