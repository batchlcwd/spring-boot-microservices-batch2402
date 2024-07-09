package inter;

public interface Bank {
    int b = 50;

    void getRate();

    String getName();

    // normal method--> automatically abstract

    // default methods

    private  void sameLogic(){
        System.out.println("same logic : 100 lines");
    }

    default void test()
    {
        this.sameLogic();
        //same--> lines
        System.out.println("this is default method  with body");
    }

    default void test1(){
        this.sameLogic();
        //same->100 lines
        System.out.println("this is another default method...");
    }


}
