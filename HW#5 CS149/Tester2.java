import java.lang.*;
import java.util.*;
public class Tester2 implements Runnable
{
	private int Iter = 10000;
	private static int iter = 10000;
	private static ArrayList<Thread> list = new ArrayList<Thread>();
	private static int Threads = 10;
	static BasicCounter2 count = new BasicCounter2(true);
	
	public static void main(String arg[])
	{
		Tester2 test = new Tester2();
		for(int i =0; i < Threads; i++)
		{
			Thread thread = new Thread(test);
			list.add(thread);
		}
		long startTime = System.nanoTime();
		list.stream().parallel();
		
		for(Thread t : list)
		{
			t.start();
		}
		for(Thread t : list)
		{
			try{t.join();}
			catch(Exception e){}
		}
		long endTime = System.nanoTime();
		long total = endTime - startTime;
		long operations = Threads * iter * 2;
		System.out.println(Threads + " threads x " + iter + " Iterations x (add + subtract) = " 
		+ operations + " operations");
		System.out.println("Error: Final counter Value: " + count.get());
		System.out.println("Total Time Elapsed: " + total + "ns");
		total = total / operations;
		System.out.println("Average time/op: " + total + "ns");
		
	}

	@Override
	public void run() 
	{	
		for(int i =0; i < Iter; i++)
		{
			count.add(1);
			count.add(-1);
		}
		//System.out.println(count.get());
	}

}
