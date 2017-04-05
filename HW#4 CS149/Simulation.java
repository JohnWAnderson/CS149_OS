import java.util.*;
/**
 * Has a link list showing a physical size of 4 running algs FIFO, Random(RR), LFU, LRU
 * ,and MFU.  counts the hits and misses that are in the current physical and 
 * prints what is currently in the 4 physical link list.
 * @author John Anderson
 * @version 1.1
 *
 * !note can comment out the print() in each to only see the hits a misses
 */
public class Simulation
{
	private static LinkedList<Page> list = new LinkedList<Page>();
	private static Random ranGen = new Random();
	private static int past;
	private static int first;
	private static int hit;
	private static int miss;
	/*
	 * runs for the % prob for each page being added
	 * @return Rand  the random number generated for type
	 */
	public static int Rand()
	{
		if(list.size() >= 2)
		{
			double random = ranGen.nextDouble();
			if(random < .7)
				return (first);
			else if(random >= .7 && random <=.8)
				return (past);
			else
			{
				int rand = ranGen.nextInt(10);
				while(rand == first && rand == past)
				{
					rand = ranGen.nextInt(10);
				}
				past = first;
				first = rand;
				return first;
			}
		}
		else if(list.size()== 1)
		{
			double random = ranGen.nextDouble();
			if(random < .7)
				return (first);
			else
			{
				int rand = ranGen.nextInt(10);
				while(rand == first)
				{
					rand = ranGen.nextInt(10);
				}
				first = rand;
				return first;
			}
		}
		else
		{
			int rand = ranGen.nextInt(10);
			first = rand;
			return first;
		}
	}
	/*
	 * uses the LRU alg and takes out what alg was used last meaning if it gets pushed to out
	 * of the physical 4 spots.  Also moves used ones to the front of the physical.
	 */
	public static void LRU()
	{
		for(int i = 0; i < 100; i++)
		{
			int a =0;
			int k =0;
			Page temp = new Page();
			temp.setID(Rand());
			if(list.size() == 0)
			{
				list.addFirst(temp); miss++;
			}
			else
			{
			for(int j = 0; j < list.size();j++)
			{
				if(list.get(j).getID() == temp.getID())
						{a =1; k =j;}
			}
			if(a == 1)
			{
				hit++;
					list.remove(k);
					list.addFirst(temp);
			}
			else
			{
				miss++;
				if(list.size() == 4)
				{
					list.addFirst(temp);
					list.removeLast();
				}
				else
					list.addFirst(temp);
			}
			
			} print();
		}
		System.out.println("Miss = " + miss +"\nHit = " + hit);
	}
	/*
	 * uses the LFU alg to remove the page with the lowest count and incress
	 * the count every time it is re added onces
	 */
	public static void LFU()
	{
		for(int i = 0; i < 100; i++)
		{
			int a =0;
			int k =0;
			int f =0;
			Page temp = new Page();
			temp.setID(Rand());
			if(list.size() == 0)
			{
				list.addFirst(temp); miss++;
			}
			else
			{
			for(int j = 0; j < list.size();j++)
			{
				if(list.get(j).getID() == temp.getID())
						{a =1; k =j;}
			}
			if(a == 1)
			{
				hit++;
				list.get(k).addCount();
			}
			else
			{
				miss++;
				if(list.size() == 4)
				{
					Page lowest = list.get(0);
					for(int h = 1; h <list.size(); h++)
					{
						if(lowest.getCount() > list.get(h).getCount())
							{lowest = list.get(h); f = h;}
					}
					list.remove(f);
					list.addFirst(temp);
				}
				else
				{
					temp.addCount();
					list.addFirst(temp);
				}
			}
			
			}
			if(i % 2 == 0) // every 4 reduces count by 1 to find least used
			{
				for(int h =0; h <list.size(); h++)
				{
					list.get(h).minCount();
				}
			}
			print();
		}
		System.out.println("Miss = " + miss +"\nHit = " + hit);
	}
	/*
	 * uses the FIFO well it just adds onto front of physical and kicks out the last
	 */
	public static void FIFO()
	{
		for(int i = 0; i < 100; i++)
		{
			int a =0;
			int h =0;
			Page temp = new Page();
			temp.setID(Rand());
			if(list.size() == 0)
			{
				list.addFirst(temp); miss++;
			}
			else
			{
			for(int j = 0; j < list.size();j++)
			{
				if(list.get(j).getID() == temp.getID())
						{a =1; h = j;}
			}
			if(a == 1)
			{
				hit++;
				//list.addFirst(temp); // if FIFO doesn't care about having same in cash
			}
			else
			{
				miss++;
				list.addFirst(temp);
			}
			
			}
			if(list.size() ==5 )
				list.removeLast();
			print();
	}
		System.out.println("Miss = " + miss +"\nHit = " + hit);
	}
/*
 * uses the Random pick alg which does every thing randomly for removing to adding	
 */
	public static void RR()
	{
		for(int i = 0; i < 100; i++)
		{
			int a =0;
			Page temp = new Page();
			temp.setID(Rand());
			if(list.size() == 0)
			{
				list.add(temp); miss++;
			}
				
			else
			{
				int hold = ranGen.nextInt(4);
				for(int j = 0; j < list.size();j++)
				{
					if(list.get(j).getID() == temp.getID())
							a =1;
				}
				if(a == 1)
				{
					hit++;
					/*if(list.size() < 4)
						list.add(temp);
					else
					{
						//list.remove(hold);
						//list.add(hold, temp);
					}*/
				}
				else
				{
					miss++;
					if(list.size() < 4)
						list.add(temp);
					else
					{
						list.remove(hold);
						list.add(hold, temp);
					}
				}
			}
			print();
		} 
		System.out.println("Miss = " + miss +"\nHit = " + hit);
	}
	/*
	 * uses MFU which is like LFU, but removes the one with highest count
	 */
	public static void MFU()
	{
		for(int i = 0; i < 100; i++)
		{
			int a =0;
			int k =0;
			int f =0;
			Page temp = new Page();
			temp.setID(Rand());
			if(list.size() == 0)
			{
				list.addFirst(temp); miss++;
			}
			else
			{
			for(int j = 0; j < list.size();j++)
			{
				if(list.get(j).getID() == temp.getID())
						{a =1; k =j;}
			}
			if(a == 1)
			{
				hit++;
				list.get(k).addCount();
			}
			else
			{
				miss++;
				if(list.size() == 4)
				{
					Page highest = list.get(0);
					for(int h = 1; h <list.size(); h++)
					{
						if(highest.getCount() < list.get(h).getCount())
							{highest = list.get(h); f = h;}
					}
					list.remove(f);
					list.addFirst(temp);
				}
				else
				{
					temp.addCount();
					list.addFirst(temp);
				}
			}
			
			}
			if(i % 3 == 0) // every 3 reduces count by 1 to find least used
			{
				for(int h =0; h <list.size(); h++)
				{
					list.get(h).minCount();
				}
			}
			print();
		}
		System.out.println("Miss = " + miss +"\nHit = " + hit);
	}
	/*
	 * prints the current physical.
	 */
	public static void print()
	{
		if(list.size() == 1)
			System.out.println(list.get(0).getID());
		else if(list.size() == 2)
			System.out.println(list.get(0).getID() + " " + list.get(1).getID());
		else if(list.size() == 3)
			System.out.println(list.get(0).getID() + " " + list.get(1).getID() + " " + list.get(2).getID());
		else
			System.out.println(list.get(0).getID() + " " + list.get(1).getID() + " " + list.get(2).getID() + " " + list.get(3).getID());
	}
	public static void main(String[] args)
	{
		list = new LinkedList<Page>();
		System.out.println("FIFO");
		FIFO(); miss =0; hit =0;// works
		list = new LinkedList<Page>();
		System.out.println("\nRR");
		RR(); miss =0; hit =0; // works
		list = new LinkedList<Page>();
		System.out.println("\nLRU");
		LRU(); miss =0; hit =0; // works
		list = new LinkedList<Page>();
		System.out.println("\nLFU");
		LFU(); miss =0; hit =0; // works
		list = new LinkedList<Page>();
		System.out.println("\nMFU");
		MFU(); miss =0; hit =0; // works
	}
}
