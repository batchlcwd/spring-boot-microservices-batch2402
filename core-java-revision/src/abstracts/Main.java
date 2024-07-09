package abstracts;

public class Main {
    public static void main(String[] args) {
        Bank bank=new HdfcBank();
        bank.getRate();
    }
}
