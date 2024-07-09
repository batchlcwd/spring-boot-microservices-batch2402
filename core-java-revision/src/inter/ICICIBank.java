package inter;

public class ICICIBank implements  Bank{
    @Override
    public void getRate() {
        System.out.println("ICICI 50% rate");
    }

    @Override
    public String getName() {
        return "ICICI Bank";
    }
}
