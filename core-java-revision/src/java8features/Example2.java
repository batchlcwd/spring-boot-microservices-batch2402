package java8features;

public class Example2 {
    public static void main(String[] args) {

        AddInter ob = (a, b) -> {
            System.out.println(a + b);
            return a + b;
        };
        ob.add(1, 3);
        Runnable runnable = () -> {
            System.out.println("this is my thread");
        };


    }
}
