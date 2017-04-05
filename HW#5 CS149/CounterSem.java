import java.util.concurrent.Semaphore;

public class CounterSem 
{
	  private long counter;
	  private final Semaphore Sem;
	  public CounterSem() {
	    this.counter = 0;
	    Sem = new Semaphore(1);
	  }
	  public void add(long value)
	  {
		try
		{
			Sem.acquire();
			counter += value;
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			Sem.release();
		}
	  }

	  public long get() {
	    return counter;
	  }
}
