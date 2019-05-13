package pa1;

import javax.swing.JOptionPane;
/**
 * This class is the UnderflowException
 * Can be throw when the Heap's size 
 * is less than 1
 * @author jiajianliu
 *
 */
public class UnderflowException extends Exception 
{
	public UnderflowException(String message)
	{
		super(message);
	}
	
}
