/**
 * A page class that holds the id and count for usesd
 * @author John Anderson
 * @version 1.1
 */
public class Page
{
	public int count = 2; // gives it a buffer to give it a head start in LFU
	public int id = 0;
	public int getID()
	{
		return id;
	}
	/*
	 * @param ID the id number
	 * sets the page id
	 */
	public void setID(int ID)
	{
		id = ID;
	}
	/*
	 * @return count
	 * returns the current count
	 */
	public int getCount()
	{
		return count;
	}
	/*
	 *@param C
	 *sets the count 
	 */
	public void setCount(int C)
	{
		count = C;
	}
	/*
	 *  +1 to the count
	 */
	public void addCount()
	{
		count++;
	}
	/*
	 * -1 to count
	 */
	public void minCount()
	{
		count--;
	}
}