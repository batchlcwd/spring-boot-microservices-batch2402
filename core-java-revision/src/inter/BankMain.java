package inter;

public class BankMain {
    public static void main(String[] args) {

        Bank bank= new ICICIBank();
        bank.getRate();
        bank.getName();
        bank.test();

    }

    public void ramu(Bank bank){
        bank.getRate();
    }
}
