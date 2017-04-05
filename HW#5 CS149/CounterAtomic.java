import java.util.concurrent.atomic.AtomicInteger;


public class CounterAtomic
{
	private AtomicInteger count = new AtomicInteger(0);
	public void add(int value)
	{
		count.addAndGet(value);
	}

	public long get() {
		return count.get();
	}

}
