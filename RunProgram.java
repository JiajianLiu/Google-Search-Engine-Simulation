package pa1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.GridLayout;

/**
 * This class is user interface of the program
 * By running RunProgam, the user will be able to 
 * get the list of the top websites and adjust the list 
 * by entering keyword or PageRank Score
 * 
 * Also, the user can insert a new website to the list
 * 
 * @author jiajianliu
 *
 */
public class RunProgram implements ActionListener

{
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
	
	/**
	 * A method for printing out the elements of
	 *  the Url Array received on parameter
	 *  It will print out the Url objects 
	 *  with the Order Number, url, and Pagerank
	 *  
	 * @param A A is an Url Array
	 */
	public static void printUrlarr(Url[] A)
	{
		for (int i = 0; i < A.length; i++)
		{
			sop(A[i].getOrder()+ ".  " + A[i].getLink() 
					+ "    PageRank: " + A[i].getPageRank());
		}
	}
	
	/**
	 * A method for sorting the Order Number of 
	 * the Url Array received on parameter
	 * The order number will start from 1 
	 * to the length of the Url Array
	 * 
	 * @param A A is an Url Array
	 */
	public static void sortOrderNumOfUrlArr(Url[] A)
	{
		for (int i = 0; i < A.length; i++)
		{
			int n = i+1;
			A[i].setOrder(n);
		}
	}
	
	public static final int maxPageRank = 400;
	
	/**
	 * The executing method for creating a user interface with functions.
	 */
	public static void main(String[] args) 
	{
		
		// Store user's keyword
		String input = JOptionPane.showInputDialog("What do you want to know?");
		// Search the Keyword
		WebCrawler web = new WebCrawler(input); 
		web.search();
		// Store the Websites Founded
		Set<String> test = web.getUris();
		
		// Check if there is result for the keyword, if not,
		// pop up window with error message and require the 
		// user to re-enter the keyword
		if (test.isEmpty())
		{
			input = JOptionPane.showInputDialog("error: Keyword not found, please reenter the keyword");
			web = new WebCrawler(input);
			web.search();
			test = web.getUris();
		}
		
		// let the set urls contains the result which have websites inside
		Set<String> urls = test;
		
		int c = 0;
		// count the size of the Set urls
		for (String s : urls)
		{
			c++;
		}
		
		// Create a Url Array urlsWithOrder with the size of the Set urls
		Url[] urlsWithOrder = new Url[c];
		
		// make urlsWithOrader filled with empty Url objects
		for(int i = 0; i < c; i++)
		{
			urlsWithOrder[i] = new Url("",0);
		}
		
		int counter2 = 0;
		// Move the Links from the Set urls to Url[] urlsWithOrder
		for (String s : urls)
		{
			
			int temp = counter2;
			urlsWithOrder[temp].setLink(s);
			counter2++;
			urlsWithOrder[temp].setOrder(counter2);
			
		}
		
		//Create the user interface window
		JFrame parent = new JFrame("Guugle");
		parent.setSize(400,400);
		parent.setLayout(new GridLayout(4,2));
		
		// Create the buttons with Button names
		JButton buttonUrls = new JButton("All the Urls Found");
		JButton buttonCheckFactor = new JButton("Factor Score of A Website");
		JButton buttonFirst30 = new JButton("The First 30 Website");
		JButton buttonInsert = new JButton("Insert A New Website");
		JButton buttonFirstRank = new JButton("The First Ranked Website");
		JButton buttonSetRank = new JButton("Set PageRank for A Website");
		JButton buttonScore =  new JButton("The Websites with Scores");
		JButton buttonClose = new JButton("Close/Exist");
		
		// Add the buttons to the user interface window
		parent.add(buttonUrls);
		parent.add(buttonSetRank);
		parent.add(buttonScore);
		parent.add(buttonInsert);

		parent.add(buttonCheckFactor);
		parent.add(buttonFirstRank);
		parent.add(buttonFirst30);
		parent.add(buttonClose);
		
		//Make the user interface appear
		parent.setVisible(true);
		
		
		
		//Calculates the page rank for each url and assign them to each url
		for (Url u : urlsWithOrder)
		{
			int Pagepoint = 0;
			int FactorOne = u.getFactor1();
			int FactorTwo = u.getFactor2();
			int FactorThree = u.getFactor3();
			int FactorFour = u.getFactor4();
			Pagepoint = FactorOne + FactorTwo + FactorThree + FactorFour;
			u.setPageRank(Pagepoint);	
		}
		
		
		// Do the HeapSort for Url[] urlsWithOrder
		HeapSortForUrl H = new HeapSortForUrl(urlsWithOrder);
		H.Heapsort(urlsWithOrder);
		
		// Reset the order numbers because 
		// HeapSort will change the positions 
		// of the urls but not the order numbers
		int n = 1;
		for (int i =  urlsWithOrder.length - 1; i > 0; i--)
		{
			urlsWithOrder[i].setOrder(n);
			n++;
		}
		
		// get the first 30 urls
		int index = urlsWithOrder.length;
		// Create an Url [] urlsWith30 for holding the first 30 urls
		Url[] urlsWith30 = new Url[30];
		// fill the Url [] urlsWith30 with empty Url objects
		// This will help by not leading to NullPointer Exception
		for(int i = 0; i < urlsWith30.length; i++)
		{
			urlsWith30[i] = new Url("",0);
		}
		
		// fill the Url [] urlsWith30 with the first 30 urls
		for (int i = 0; i < urlsWith30.length; i++)
		{
			urlsWith30[i] = urlsWithOrder[index-1]; 
			index--;
			
		}
	
		// Show the first 30 urls based on the PageRank score
		// if the user click on this button
		// The order is from the highest PageRank score to the
		// lowest PageRank score
		buttonFirst30.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "";
				// sort the order number of the Url Array
				sortOrderNumOfUrlArr(urlsWith30);
				// Put all the urls as an String
				for (Url u : urlsWith30)
				{
					message = message + u.getOrder() + ".  " + u.getLink()
					+ "   PageRank score: " + u.getPageRank() + "\n"; 
				}
				// Pops up a window to show the first 30 urls
				// with order number and PageRank Score
				JOptionPane.showMessageDialog(null, message);
			}
		});
		
		buttonCheckFactor.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String list = "";
				
				// Sort the order numbet of the Url [] urlsWithOrder
				int n = 1;
				for (int i = urlsWithOrder.length - 1; i > 0 ; i--)
				{
					
					urlsWithOrder[i].setOrder(n);
					n++;
				}
				
				// Put all the urls into a string with
				// Order number and PageRank number
				for (int i =  urlsWithOrder.length - 1; i > 0; i--)
				{
					Url u = urlsWithOrder[i];
					list = list + u.getOrder() + ".  " + u.getLink()
					+ "   PageRank score: " + u.getPageRank() + "\n"; 
				}
				
				// Create a window which pops for user's reference to the urls 
				JOptionPane pane1 = new JOptionPane(list);
				 JDialog dialog1 = pane1.createDialog(null, "The website list with Page Rank");
				    dialog1.setModal(false);
				    dialog1.setVisible(true);
				int inputnum = 0;
				// Pops up a window which require user to input an integer
				try{
					inputnum = Integer.parseInt(JOptionPane.showInputDialog("Please input the"
						+ " Order Number of the Website"));
				
					// check if the input number is in the range of the Order numbers
					if (inputnum > urlsWithOrder.length || inputnum < 1)
					{
						// pop up the window again if the input number is out of range
						inputnum = Integer.parseInt(JOptionPane.showInputDialog("Error: The input "
								+ "order number " + inputnum
								+ " is greater than the order number range: "
								+ (urlsWithOrder.length - 1)
								+ " or less than the range: 1" 
								+ ", Please re-enter the order number"));
					}
				}
				catch(NumberFormatException e1)
				{
					// pop up window with error message if the input is not an integer
					JOptionPane pane2 = new JOptionPane("Error: the input is not an "
							+ "integer, please re-click the"
							+ " button after closing the poped-up windows");
					JDialog dialog2 = pane2.createDialog(null, "ERROR");
					dialog2.setModal(false);
				    dialog2.setVisible(true);
				}
				// close the window with urls
				dialog1.setVisible(false);
				// put the PageRank information of the corresponding url into a string
				String message = "";
				for (Url u : urlsWithOrder)
				{
					if (u.getOrder() == inputnum)
					{
						message = "Website Url: " + u.getLink()+ "\n" +
						u.getFactorString(1) + ": " + u.getFactor1()+ "\n" 
						+ u.getFactorString(2) + ": " + u.getFactor2() + "\n" +
						u.getFactorString(3) + ": " + u.getFactor3() + "\n" +
						u.getFactorString(4) + ": " + u.getFactor4();
						
					}
				}
				// pop up the window with the information
				JOptionPane.showMessageDialog(null, message);
			} 
		});
		
		// priority Queue(Q) with first 20 Urls
		Url[] HeapArr = new Url[30];
		
		// fill the Url[] HeapArr with empty Urls
		// this will prevent the system get into 
		// NullPointer Exception
		for(int i = 0; i < 30; i++)
		{
			HeapArr[i] = new Url("",0);
		}
		
		// Fill Q with the first 20 urls
		for (int i =0; i < 20; i++)
		{
			HeapArr[i] = urlsWith30[i];
		}
	
		// Build the max heap of Q
		HeapSortForUrl Heap = new HeapSortForUrl(HeapArr);
		Heap.BuildMaxHeap(HeapArr);
		
		// Show the first Ranked website in the Q if the user 
		// click on this button
		// The Q will not be affected since the 
		buttonFirstRank.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// Make a copy Url Array of the Q
				int count = 0;
				// count how many non-empty urls currently inside Q
				for (Url o : HeapArr)
				{
					if (o.getPageRank() != 0)
						count++;
				}
				// create a copy array of Q which has size 
				// 1 index greater than the number of 
				// non-empty urls inside Q
				Url [] copy = new Url[count+1];
				// fill with empty url
				for (int i = 0; i < copy.length; i++)
				{
					copy[i] = new Url("",0);
				}
				
				// let Url[] copy have the same url objects as Q
				for (int i = 0; i < copy.length - 1; i++)
				{
					copy[i] = HeapArr[i];
				}
				
				// store the information of the top url in a string message
				String message = "";
				HeapSortForUrl sorter = new HeapSortForUrl(copy);
				
					Url u = new Url("",0);
					try {
						u = sorter.HeapExtractMax(copy);
						
					} catch (UnderflowException e1) {
						JOptionPane.showMessageDialog(null, "Heap UnderFlow");
						e1.printStackTrace();
					}
					message = u.getOrder() + ".  " + u.getLink() 
					+ "  PageRank score:" + u.getPageRank();
										
				// Pop up a window with the information of top url 
				JOptionPane.showMessageDialog(null, message);
			}
		});
		
		
		// Insert a website into the Q after the user click the button
		// and input a url
		// This method will take whatever url that the user input
		buttonInsert.addActionListener(new java.awt.event.ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Couny how many non-empty urls inside the Q
				int count = 0;
				for (Url o : HeapArr)
				{
					if (o.getPageRank() != 0)
						count++;
				}
				// create a copy array of Q which has size 
				// 1 index greater than the number of 
				// non-empty urls inside Q
				Url [] copy = new Url[count+1];
				// fill with empty url
				for (int i = 0; i < copy.length; i++)
				{
					copy[i] = new Url("",0);
				}
				// let Url[] copy have the same url objects as Q
				for (int i = 0; i < copy.length - 1; i++)
				{
					copy[i] = HeapArr[i];
				}
				
				String input = "";
				
				// if Q is not full filled, keep pop up 
				// windows when the user click this button
				// if Q is full filled, pop error message
				if (count < 30)
				{
					input = JOptionPane.showInputDialog("Insert a New Website");
				}
				else 
				{
					JOptionPane pane1 = new JOptionPane("Error: Excceed The Maximum of Chances of "
							+ "Inserting a Website: 10.");
					JDialog dialog1 = pane1.createDialog(null, "ERROR");
					dialog1.setModal(false);
				    dialog1.setVisible(true);
				}
				// Create Url object to hold the input website
				Url l = new Url(input, 0);
				// Calculates the PageRank for the input url and assign it
				l.setPageRank(l.getFactor1() + l.getFactor2() 
				+ l.getFactor3() + l.getFactor4());
				
				
				HeapSortForUrl sortforC = new HeapSortForUrl(copy);
				
				// inset the url into Q
				// pop up error window if the url cannot be insert
					try {
						sortforC.HeapInsert(copy, l);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Execced the Maximum of input: 10");
					}
						
					// Set the order number of input url
					for (int i = 0; i < copy.length; i++)
						{
							if (copy[i].getPageRank() == l.getPageRank() && 
									copy[i].getLink() == l.getLink())
							{
								copy[i].setOrder(count+1);
							}
						}
					
					//Sort the order number of Q
					for (int i = 0; i < copy.length; i++)
						{
							int n = i + 1;
							copy[i].setOrder(n);
						}
					//Update the Queue List
					for (int i = 0; i < copy.length; i++)
					{
						HeapArr[i] = copy[i];
					}
					
				// Store the information of the urls in String message
				String message = "";
				for (Url u : copy)
				{
					if (u.getPageRank() != 0 && u.getLink() != "")
					message = message + u.getOrder() + ".  " + u.getLink()
					+ "   PageRank score: " + u.getPageRank() + "\n"; 
				}
				
				// Pop up a window with the information of the current Q list
				JOptionPane.showMessageDialog(null, message);
				
			}
		});
		
		// Show user the current urls in Q and provide a 
		// input window for user to set a new PageRank score 
		// of an url when the user click this button and click 
		// the ok button of the poped up window
		buttonSetRank.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				// Count how many non-empty urls inside the Q
				int count = 0;
				for (Url o : HeapArr)
				{
					if (o.getPageRank() != 0)
						count++;
				}
				
				JOptionPane.showMessageDialog( null,"Notice"
						+ "\n" +"The current top "
								+ count + " websites "
						+ "\n"+ "will be shown in a new window "
						+ "\n"+ "after the user click on the ok button,"
						+ "\n"+ "please pick the order number of "
						+ "\n"+ "the website that you want from the list");
				
				// store all the non-empty urls in String message
				String message = "";
				for (Url u : HeapArr)
				{
					if (u.getPageRank() != 0 && u.getLink() != "")
					message = message + u.getOrder() + ".  " + u.getLink()
					+ "   PageRank score: " + u.getPageRank() + "\n"; 
				}
				
				// pops up a window to show all the urls with order number and PageRank scores
				JOptionPane pane1 = new JOptionPane(message);
				 JDialog dialog1 = pane1.createDialog(null, "The top 20-30 website list");
				    dialog1.setModal(false);
				    dialog1.setVisible(true);
				    
				 // pops up a window to let tuser input the order number
					int inputnum = Integer.parseInt(JOptionPane.showInputDialog("Please input the"
						+ " Order Number of the Website"));
				
			// check if the input Order Number is in the range of the Order number
			// if the input Order Number is not in the range,
			// pops up a window which allows the user to re-enter the Order Number
			checkOrder:	while (inputnum > count || inputnum < 1)
				{
					// Create error window with error message and pop it up
					JOptionPane pane3 = new JOptionPane("Error: The input order number: "
					+ "" + inputnum
					+ " is not in the range of the "
					+ "order number: "+ 
					1 + " - " + count);
					
					// Create the re-enter window and pop it up
					JDialog dialog3 = pane3.createDialog(null, "ERROR");
				    dialog3.setModal(false);
				    dialog3.setVisible(true);
				    inputnum = Integer.parseInt(JOptionPane.showInputDialog("Please re-enter the"
							+ " Order Number of the Website"));
				    // close the error window
				    dialog3.setVisible(false);
				    // check again 
				    continue  checkOrder;	    
				}
				
				int inputRank = Integer.parseInt(JOptionPane.showInputDialog("Please input the"
						+ " PageRank you want for this Website"));
				
				// check if the input PageRank is in the range of the PageRank
				// if the input PageRank is not in the range,
				// pops up a window which allows the user to re-enter the PageRank
				checkrank:	while  (inputRank > maxPageRank || inputRank < 1)
				{
					// Create error window with error message and pop it up
					JOptionPane pane = new JOptionPane("Error: The input PageRank: "
					+ inputRank + " is not in the Range of PageRank score: "+ 1 
					+" - " + maxPageRank );
					
					// Create the re-enter window and pop it up 
					JDialog dialog2 = pane.createDialog(null, "ERROR");
				    dialog2.setModal(false);
				    dialog2.setVisible(true);
				    inputRank = Integer.parseInt(JOptionPane.
				    	showInputDialog("Please re-enter the"
					+ " new PageRank score of the Website"));
				    // close the error window
				    dialog2.setVisible(false);
				    // check again
				    continue checkrank;
					    
				}
				
				// close the list window
				dialog1.setVisible(false);
				
				// if the input PageRank is in the range, 
				// check the current Url object's PageRank
				// if input PageRank is larger, then increase
				// the Url's PageRank to the input PageRank
				// Otherwise pops up error window with error message
				if (inputRank <= maxPageRank && inputRank >= 1 )
				{
					// Find the Urls with the Order Number that the user want
					int index = 0;
					for (int i = 0; i < HeapArr.length; i++)
					{
						if (HeapArr[i].getOrder() == inputnum)
							index = i;
					}
					// Hold the url with Url object replace
					Url replace = new Url(HeapArr[index].getLink(), inputRank);
					
					// Increase the PageRank of the chosen url
					// if the PageRank is smaller than the target's PageRank
					// pops up Error windwo with message
					HeapSortForUrl sort = new HeapSortForUrl(HeapArr);
					try {
						sort.HeapIncreaseKey(HeapArr, index, replace);
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
								
					// Sort the order number of the Q
					sortOrderNumOfUrlArr(HeapArr);
					
					// Store the non-empty urls in the Q in a string message
					message = "";
					for (Url u : HeapArr)
					{
						if (u.getPageRank() != 0 && u.getLink() != "")
						message = message + u.getOrder() + ".  " + u.getLink()
						+ "   PageRank score: " + u.getPageRank() + "\n"; 
					}
					
					// pops up the Window which has the updates urls
					// with order number and PageRank ascore
						JOptionPane.showMessageDialog(null, message);
					}
			
	
				
			
			}
		});
		
		// Show the urls when user Click the buttonUrls
				// the order will be the same order as the urls 
				// show in search()
				buttonUrls.addActionListener(new java.awt.event.ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						String u = "";
						// counter for temporary order number since the result is not sorted
						int counter = 0;
						// get all the urls in one string u
						for (String s : urls)
						{
							counter++;
							u = u + counter + ".  "+ s + "\n";
						}
						
						// pops up a window with all the urls
						JOptionPane.showMessageDialog(null, u);
						
					} 
					}
				);
				
				// Show the urls with PageRank scores when user 
				// Click the buttonUrls the order will be the same
				// order as the urls  show in search()	
				buttonScore.addActionListener(new java.awt.event.ActionListener()
				{
							@Override
							public void actionPerformed(ActionEvent e) 
							{
								// sort the order numbers in case another method affected it
								//sortOrderNumOfUrlArr(urlsWithOrder);
								String message = "";
								// get all the urls in one string message
								for (int i =  urlsWithOrder.length - 1; i > 0; i--)
								{
									Url u = urlsWithOrder[i];
									message = message + u.getOrder() + ".  " + u.getLink()
									+ "   PageRank score: " + u.getPageRank() + "\n"; 
								}
								
								// pops up a dialog window with the urls with PageRank scores
								JOptionPane.showMessageDialog(null, message);
							
							
							}
				});
		
		// Exist the program if the user click on this button
		buttonClose.addActionListener(new ActionListener() {
			 public void actionPerformed (ActionEvent e) {
			  System.exit(0);
			 }
			});
		
		
		
	}
		
		
    // antionPerformed method for the ActionLister class
	// this method will be Overrided in the button functions
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}

