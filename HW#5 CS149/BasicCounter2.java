public class BasicCounter2 implements Counter {
  private long counter;
  private final boolean yield;

  public BasicCounter2(boolean yield) {
    this.counter = 0;
    this.yield = yield;
  }

  public void add(long value) {
    if (yield)
      Thread.yield();
    counter += value;
  }

  public long get() {
    return counter;
  }
}