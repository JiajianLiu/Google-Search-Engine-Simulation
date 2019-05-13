package pa1;

import java.util.Random;

/**
 * This class is the class for Url object
 * The object Url can be created and modified with 
 * the methods and constructor in this class
 * 
 * @author jiajianliu
 *
 */
public class Url
{
	private String link;
	private int pageRank;
	private int factor1;
	private int factor2;
	private int factor3;
	private int factor4;
	private int order;
	private static final String factorString1 = "How many "
			+ "people searched this website today";
	private static final String factorString2 = "People's "
			+ "reviews about this website";
	private static final String factorString3 = "How relev"
			+ "eant is the website";
	private static final String factorString4 = "How reliab"
			+ "le is the website's source";
	
	/**
	 * Constructs a Url object with link and pageRank
	 * @param link link is the website link
	 * @param pageRank pageRank is the PageRank score of the url
	 */
	public Url(String link, int pageRank)
	{
		// Store the link and pageRank, 
		// order number is set to 0 automatically
		this.link = link;
		this.order = 0;
		this.pageRank = pageRank;
		
		// generate the 4 factor scores with Class Random
		Random r = new Random();
		
		this.factor1 = r.nextInt(100) + 1;
		this.factor2 = r.nextInt(100) + 1;
		this.factor3 = r.nextInt(100) + 1;
		this.factor4 = r.nextInt(100) + 1;
	}
	
	/**
	 * Gets the factor String base on the input number
	 * @param factorNum factoNum is the factor number 
	 * @return the factor String assigned to factor 1-4
	 */
	public String getFactorString(int factorNum)
	{
		// check if factorNum is the number of 1 of the factor
		// return the corresponding string if it is
		if (factorNum == 1)
			return factorString1;
		if (factorNum == 2)
			return factorString2;
		if (factorNum == 3)
			return factorString3;
		if (factorNum == 4)
			return factorString4;
		else 
			return "no such a String"; // Otherwise, return "no such a String"
	} 
	
	/**
	 * Set the Link of the Url object
	 * @param l l is the link
	 */
	public void setLink(String l)
	{
		this.link = l;
	}
	
	/**
	 * Gets the Link of the Url object
	 * @return Returns the link of the Url object
	 */
	public String getLink()
	{
		return link;
	}
	
	/**
	 * Gets the Order Number of the Url Object
	 * @return Returns the Order Number of the Url object
	 */
	public int getOrder()
	{
		return order;
	}
	
	/**
	 * Sets the Order Number of the Url Object
	 * @param n n is the new Order Number
	 */
	public void setOrder(int n)
	{
		order = n;
	}
	
	/**
	 * Sets the PageRank of the Url Object
	 * @param PageRank PageRank is the new Pagerank score
	 */
	public void setPageRank(int PageRank)
	{
		this.pageRank = PageRank;
	}
	
	/**
	 * Gets the PageRank of the Url Object
	 * @return Returns the PageRank of the Url Object
	 */
	public int getPageRank()
	{
		return pageRank;
	}
	
	/**
	 * Gets the Factor 1 score of the Url
	 * @return Returns the Factor 1 score of the Url
	 */
	public int getFactor1()
	{
		return factor1;
	}
	
	/**
	 * Gets the Factor 2 score of the Url
	 * @return Returns the Factor 2 score of the Url
	 */
	public int getFactor2()
	{
		return factor2;
	}
	
	/**
	 * Gets the Factor 3 score of the Url
	 * @return Returns the Factor 3 score of the Url
	 */
	public int getFactor3()
	{
		return factor3;
	}
	
	/**
	 * Gets the Factor 4 score of the Url
	 * @return Returns the Factor 4 score of the Url
	 */
	public int getFactor4()
	{
		return factor4;
	}
	
	/**
	 * A method for printing any object received on parameter
	 * This method is abbreviation for System.out.println(Object)
	 * 
	 * @param x x is any object
	 */
	public static void sop(Object x)
	{
		System.out.println(x);
	}
//public static void main(String[] args)
//{
//	Url url = new Url("String", 0);
//	sop(url.getLink());
//	sop(url.getPageRank());
//	
//	sop(url.getFactor1());
//	sop(url.getFactor2());
//	sop(url.getFactor3());
//	sop(url.getFactor4());
//	sop(url.getOrder());
//	url.setOrder(1);
//	sop(url.getOrder());
//	String l = "link";
//	url.setLink(l);
//	sop(url.getLink());
//	WebCrawler web = new WebCrawler("us"); 
//	web.search();
//	Set<String> urls = web.getUris();
//	int c = 0;
//	
//	for (String s : urls)
//	{
//		c++;
//	}
//	Url[] urlsWithOrder = new Url[c];
//	
//	for(int i = 0; i < c; i++)
//	{
//		urlsWithOrder[i] = new Url("",0);
//	}
//	int counter2 = 0;
//	
//	for (String s : urls)
//	{
//		
//		int temp = counter2;
//		sop(s);
//		
//		Url u = urlsWithOrder[temp];
//		sop(u.getFactor1());
//		//u.setLink(k);
//		//urlsWithOrder[temp].setLink(k);
//		counter2++;
//		urlsWithOrder[temp].setOrder(counter2);
//		
	//}
//	Url u = new Url("",0);
//	sop(u.getFactorString(1));
//	sop(u.getFactorString(2));
//	sop(u.getFactorString(3));
//	sop(u.getFactorString(4));
//	sop(u.getFactorString(0));
	
//}
}
