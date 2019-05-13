package pa1;

import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * This class has the Priority Queue
 *  method for the int Arrays
 *  This class also can do Heap 
 *  Sort for int Arrays
 *  
 * @author jiajianliu
 *
 */
public class HeapSort 
{
	private int heapsize;
	
	/**
	 * 	Creates a Heap Sort holder for int Array A
	 * @param A  A is the int Array 
	 */
	public HeapSort(int [] A)
	{
		this.heapsize = A.length;
	}
	
	/**
	 * Gets the heapsize of the int Array
	 * @return Return the heapsize of the int Array
	 */
	public int getHeapsize()
	{
		return heapsize;
	}
	
	/**
	 * Maxheapifies the heap tree brunch 
	 * of the heap base on parent's index
	 * @param A A is the int Array
	 * @param i i is the index of the parent 
	 */
	public void MaxHeapify(int [] A, int i)
	{
		
		int largest = i;// largest is parent at the moment
		int left = i*2 + 1 ; // left child = 2*index + 1, the +1 because java starts at 0
		int right = i*2 + 2; // right child = left child + 1
		// if left child's int
		// is greater than the current largest int
		// let the largest index equals 
		// left child's index
		if (left < heapsize && A[left] > A[largest])
		{
			largest = left;
		}
		// if right child's int 
		// is greater than the current largest int
		// let the largest index equals 
		// right child's index
		if (right < heapsize && A[right] > A[largest])
		{
			largest = right;
		}
		// if the largest index get changed, swap the int
		if (largest != i)
		{
			// swap
			int tempI = A[i];
			int tempLar = A[largest];
			A[i] = tempLar;
			A[largest] = tempI;
			// call MaxHeapify on the current largest int to check its child 
			MaxHeapify(A, largest);
		}
	}

	/**
	 * Make the int Array max-heaped
	 * 
	 * @param A A is an int Array
	 */
	public void BuildMaxHeap(int [] A)
	{
		// store the heapsize of the int Array
		heapsize = A.length;
		// call MaxHeapify on all int to make max heap
		for (int i = A.length / 2 ; i>=0; i--)
		{
			MaxHeapify(A, i);
		}
	}
	
	/**
	 * Sort the int Array with Heap Sort
	 * @param A A is an int Array
	 */
	public void Heapsort(int [] A)
	{
		// make the int Array max heaped
		BuildMaxHeap(A);
		
		// swap the last child(smallest at the moment) 
		// with the first parent(largest at the moment)
		for (int i = A.length -1; i>=0; i--)
		{
			// swap
			int temp1 = A[0];
			int tempI = A[i];
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
	 * Gets the element with the highest int in the int Array
	 * IMPORTANT: The the element with the highest 
	 * int will be removed from the Array
	 * 
	 * @param S S is the int Array
	 * @return the element object with the highest int
	 * @throws UnderflowException if the Array doesn't 
	 * have any int object
	 */
	public int HeapExtractMax(int [] S) throws UnderflowException
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
		// store the int object with highest int
		int max = S[0];
		
		// Extract the int object with highest
		// int from the Array
		S[0] = S[heapsize-1];
		heapsize--;
		MaxHeapify(S,0);
		// return the int object with highest int
		return max;
		
	}
	
	/**
	 * Increase the int of the current int
	 * object to the int if the input 
	 * int is greater than the original int
	 * 
	 * @param A A is the int Array
	 * @param i i is the index of the int object in the Array
	 * @param u u is the int object with the new int
	 * 
	 * @throws IOException if the new int is less than 
	 * the original int of the chosen int object in the Array
	 */
	public void HeapIncreaseKey(int [] A,int i, int k)throws IOException
	{
		// check if the new int is less than 
		// the original int of the int object 
		// in the Array, if it is, thows IOException
		// if not, continue to increase the int 
		// of the int object in the Array
		if (k < A[i])
		{
			JOptionPane.showMessageDialog(null, "New key is smaller than current key");
			throw new IOException("new key is smaller than current key");
		}
		
		// Increase the int of the int object in the Array
		A[i] = k;
		
		// check if the modified int object 
		// has higher int than its parent int object
		// if it is, swap
		// if not, do nothing
		while (i > 0 && A[parentIndex(i)] < A[i])
		{
			// Store
			int tempX = A[i];
			int tempP = A[parentIndex(i)];
			// Store
			A[i] = tempP;
			A[parentIndex(i)] = tempX;
			
			// update i to the new index since A[i] is the parent now
			i = parentIndex(i);
		}
		
	}
	
	/**
	 * Gets the parent index by inputting child's ndex
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
	 * Insert the int object into the int Array
	 * Notice: this method has been 
	 * modified from the pseudocodes
	 * because array cannot be resized
	 * 
	 * IMPORTANT: this method can only be used on
	 *  on Array with empty slots at the end
	 *  Otherwise the array's last non-empty 
	 *  element will be changed
	 * 
	 * @param A A is the int Array
	 * @param u u is the int object which needed to be insert
	 * @throws IOException if the HeapINcreaeKey not working
	 */
	public void HeapInsert(int []A, int k) throws IOException
	{
		// decrease heapsixe since the heapsize should the array.length -1 
		heapsize = heapsize-1;
		// set the int of the last int object 
		// in the array to a very small number
		// so that HeapIncreaseKey will work
		A[heapsize] = -(int) Double.MAX_VALUE;
		// Insert u into int Array A
		HeapIncreaseKey(A, heapsize, k);
	}
	
	/**
	 * Gets the int object of the int Array which has
	 *  the maximum int of the int Array
	 * @param a a is the int Array
	 * @return Returns the int object which has the 
	 *  maximum int of the int Array
	 */
	public int HeapMaximum(int [] A)
	{
		// return the first parent
		return A[0];
	}
	
	/**
	 * A method for printing out the elements of
	 *  the int Array received on parameter
	 *  It will print out the int objects 
	 *  with the int
	 *  
	 * @param A A is an int Array
	 */
	public static void printArr(int [] A)
	{
		for (int i : A)
		{	
		System.out.print(i + " ");
		}
		System.out.println("");
	}
	
	/**
	 * A method for printing any object received on parameter
	 * This method is abbreviation for System.out.print(Object)
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
		
		
		int [] A = {1,2,3,4,5,6,7,0};
		HeapSort H = new HeapSort(A);
		//H.MaxHeapify(A,0);
		//H.BuildMaxHeap(A);
		//H.Heapsort(A);
		//H.HeapIncreaseKey(A, 4, 8);
		sop("current max:" + H.HeapMaximum(A));
		//int[] B = {1};
	//	sop("the current max: "+ H.HeapExtractMax(A));
		System.out.println();
		//H.HeapInsert(A, 8);
		//sop(H.heapsize+"\n");
		printArr(A);
		
	}

}