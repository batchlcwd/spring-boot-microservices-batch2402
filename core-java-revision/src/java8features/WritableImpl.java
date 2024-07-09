package java8features;

public class WritableImpl implements  Writable{
    @Override
    public void write() {
        System.out.println("writing to file");
    }
}
