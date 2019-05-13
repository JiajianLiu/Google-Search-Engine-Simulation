package pa1;



import java.io.IOException;
import javax.swing.JOptionPane;
/**
 * This class has the Priority Queue
 *  method for the Url Arrays
 *  This class also can do Heap 
 *  Sort for Url Arrays
 *  
 * This class is a modification of HeapSort.
 * @author jiajianliu
 *
 */
public class HeapSortForUrl
{
	private int heapsize;
	
	/**
	 * 	Creates a Heap Sort holder for Url Array A
	 * @param A  A is the Url Array 
	 */
	public HeapSortForUrl(Url[] A)
	{
		this.heapsize = A.length;
	}
	
	/**
	 * Gets the heapsize of the Url Array
	 * @return Return the heapsize of the Url Array
	 */
	 public int getHeapsize()
	 {
		return heapsize;
	 }
	/**
	 * Maxheapifies the heap tree brunch 
	 * of the heap base on parent's index
	 * @param A A is the Url Array
	 * @param i i is the index of the parent Url
	 */
	public void MaxHeapify(Url [] A, int i)
	{
		
		int largest = i; // largest is parent at the moment
		int left = i*2 + 1 ; // left child = 2*index + 1, the +1 because java starts at 0
		int right = i*2 + 2; // right child = left child + 1
		// if left child's PageRank
		// is greater than the current largest PageRank 
		// let the largest index equals 
		// left child's index
		if (left < heapsize && A[left].getPageRank() > A[largest].getPageRank())
		{
			largest = left;
		}
		// if right child's PageRanl 
		// is greater than the current largest PageRank ,
		// let the largest index equals 
		// right child's index
		if (right < heapsize && A[right].getPageRank() > A[largest].getPageRank())
		{
			largest = right;
		}
		// if the largest index get changed, swap the urls 
		if (largest != i)
		{
			// swap
			Url tempI = A[i];
			Url tempLar = A[largest];
			A[i] = tempLar;
			A[largest] = tempI;
			// call MaxHeapify on the current largest url to check its child 
			MaxHeapify(A, largest);
		}
	}

	/**
	 * Make the Url Array max-heaped
	 * 
	 * @param A A is an Url Array
	 */
	public void BuildMaxHeap(Url [] A)
	{
		// store the heapsize of the Url Array
		heapsize = A.length;
		// call MaxHeapify on all Urls to make max heap
		for (int i = A.length / 2 ; i>=0; i--)
		{
			MaxHeapify(A, i);
		}
	}
	/**
	 * Sort the Url Array with Heap Sort
	 * @param A A is an Url Array
	 */
	public void Heapsort(Url [] A)
	{
		// make the Url Array max heaped
		BuildMaxHeap(A);
		
		// swap the last child(smallest at the moment) 
		// with the first parent(largest at the moment)
		for (int i = A.length -1; i>=0; i--)
		{
			// swap
			Url temp1 = A[0];
			Url tempI = A[i];
			A[0] = tempI;
			A[i] = temp1;
			// decrese the heapsize 
			// so that the last child 
			// will not be affected anymore
			heapsize = heapsize - 1;
			//swap first parent(smalest at the moment)
			// with the largest child of this parent
			MaxHeapify(A,0);
		}
	}
	
	/**
	 * Insert the url object into the Url Array
	 * Notice: this method has been 
	 * modified from the pseudocodes
	 * because array cannot be resized
	 * 
	 * IMPORTANT: this mtehod can only be used on
	 *  on Array with empty slots at the end
	 *  Otherwise the array's last non-empty 
	 *  element will be changed
	 * 
	 * @param A A is the Url Array
	 * @param u u is the url which needed to be insert
	 * @throws IOException if the HeapINcreaeKey not working
	 */
	public void HeapInsert(Url []A, Url u) throws IOException
	{
		// store the url of u
		String s = u.getLink();
		
		// decrease heapsixe since the heapsize should be the array.length -1
		heapsize = heapsize-1;
		// set the PageRank of the last Url 
		// in the array to a very small number
		// so that HeapIncreaseKey will work
		A[heapsize].setPageRank( -(int) Double.MAX_VALUE); 
		// Insert u into Url Array A
		HeapIncreaseKey(A, heapsize, u);
		
		//Find u in Array A
		for(int i = 0; i < A.length; i++)
		{
			if (A[i].getPageRank() == u.getPageRank() && 
					A[i].getOrder() == u.getOrder() && 
					A[i].getLink() == "")
			{
				//Restore the Url for u in Array A
				A[i].setLink(s);
				return;
			}
		}
	}
	
	/**
	 * Gets the Url with the highest PageRank in the Url Array
	 * IMPORTANT: The the Url with the highest 
	 * PageRank will be taken from the Array
	 * 
	 * @param S S is the Url Array
	 * @return the url object with the highest PageRank
	 * @throws UnderflowException if the Array doesn't 
	 * have any Url object
	 */
	public Url HeapExtractMax(Url [] S) throws UnderflowException
	{
		// get the heapsize
		heapsize = S.length;
		// check if the Array contains any object
		// if not, throw UnderflowException and 
		// pops up window with error message
		if (heapsize < 1)
		{
			JOptionPane.showMessageDialog(null, "Heap UnderFlow");
			throw new  UnderflowException("heap Underflow");
			
		}
		// store the url object with highest PageRank score
		Url max = S[0];
		
		// Extract the url object with highest
		// PageRank score from the Array
		S[0] = S[heapsize-1];
		heapsize--;
		MaxHeapify(S,0);
		
		// return the url object with highest PageRank score
		return max;
		
	}
	
	/**
	 * Increase the PageRank of the current url 
	 * object to the new PageRank if the input url object's
	 * PageRank is greater than the original PageRank
	 * 
	 * @param A A is the Url Array
	 * @param i i is the index of the url object in the Array
	 * @param u u is the Url object with the new PageRank
	 * 
	 * @throws IOException if the new PageRank is less than 
	 * the original PageRank of the chosen url object in the Array
	 */
	public void HeapIncreaseKey(Url [] A,int i, Url u)throws IOException
	{
		// check if the new PageRank is less than 
		// the original PageRank of the url object 
		// in the Array, if it is, thows IOException
		// if not, continue to increase the PageRank 
		// of the url object in the Array
		if (u.getPageRank() < A[i].getPageRank())
		{
			JOptionPane.showMessageDialog(null, "New PageRank Score is smaller than current PageRank Score");
			throw new IOException("New PageRank Score is smaller than current PageRank Score");
		}
		// Increase the PageRank of the url object in the Array
		A[i].setPageRank(u.getPageRank());
		
		// check if the modified url object 
		// has higher PageRank than its parent url
		// if it is, swap
		// if not, do nothing
		while (i > 0 && A[parentIndex(i)].getPageRank() < A[i].getPageRank())
		{
			//store
			Url tempX = A[i];
			Url tempP = A[parentIndex(i)];
			//swap
			A[i] = tempP;
			A[parentIndex(i)] = tempX;
			
			// update i to the new index since A[i] is the parent now
			i = parentIndex(i);
		}
		
	}
	/**
	 * Gets the parent index by inputting child's index
	 * @param i i is the child's index
	 * @return Returns the parent index based on 
	 * the input child's index
	 */
	public int parentIndex(int i)
	{
		// check if i is the index of 
		// the first child, return 0 if it is 
		if (i == 1)
			return 0;
		// else, return the index of 
		// the parent url by calculation
		return (i-1)/2;
	}
	
	/**
	 * Gets the url object of the Url Array which has
	 *  the maximum PageRank of the Url Array
	 * @param a a is the Url Array
	 * @return Returns the url object which has the 
	 *  maximum PageRank of the Url Array
	 */
	public Url HeapMaximum(Url[] a)
	{
		// return the first parent
		return a[0];
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
			System.out.println(A[i].getOrder()+ ".  " + A[i].getLink() 
					+ ",    PageRank: " + A[i].getPageRank());
		}
	}
	
	/**
	 * A method for printing any object received on parameter
	 * This method is abbreviation for System.out.println(Object)
	 * 
	 * @param x x is any object
	 */
	public static void sop(Object x)
	{
		System.out.print(x);
	}

	
	// test codes
	public static void main(String[] args) throws UnderflowException, IOException
	{
		
		
		Url[] A = { new Url("A",1),  
				new Url("A",2), 
				new Url("B",3), 
				new Url("C",4), 
				new Url("D",5), 
				new Url("E",6), 
				new Url("F",7), 
				new Url("",0)
				};
		HeapSortForUrl H = new HeapSortForUrl(A);
		//H.MaxHeapify(A,0);
		//H.BuildMaxHeap(A);
		//H.Heapsort(A);
		//Url u = new Url("G", 8);
		//H.HeapIncreaseKey(A, 4, u);
		Url o = H.HeapMaximum(A);
		sop("the current max: " + o.getOrder() + ".  " + o.getLink() 
		+ ",   PageRank: "+ o.getPageRank() );
		System.out.println();
		//Url[] B = {};
		//Url u = H.HeapExtractMax(A);
		//sop("the current max: " + u.getOrder() + 
		//		".  " + u.getLink() 
		//+ ",   PageRank: "+ u.getPageRank());
		//System.out.println();
		//System.out.println();
		//Url u = new Url("G",8);
		//H.HeapInsert(A, u);
		//sop(H.heapsize+"\n");
		printUrlarr(A);
		
	}

}

