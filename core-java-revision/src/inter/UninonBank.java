package inter;

public class UninonBank implements Bank {
    @Override
    public void getRate() {
        System.out.println("Union Bank 60%");
    }

    @Override
    public String getName() {
        return "Union Bank";
    }
}
