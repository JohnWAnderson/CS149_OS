import java.util.concurrent.locks.Lock;

public class CounterLock
{
	private long counter;
	private Lock lock;
	public CounterLock(Lock myLock) {
		this.lock = myLock;
		this.counter = 0;
  }
	  public void add(long value) {
	    counter += value;
	  }

	  public long get() {
		  lock.lock();
		  try{
			  counter++;
			  return counter;
		  }
		  finally{
			  lock.unlock();
		  }
	  }
}
