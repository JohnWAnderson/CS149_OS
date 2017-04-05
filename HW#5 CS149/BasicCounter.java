public class BasicCounter implements Counter {
  private long counter;

  public BasicCounter() {
    this.counter = 0;
  }

  public void add(long value) {
    counter += value;
  }

  public long get() {
    return counter;
  }
}