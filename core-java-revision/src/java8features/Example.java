package java8features;

public class Example {

    static void  testing(Writable writable){

    }

    public static void main(String[] args) {
        Writable writable=new WritableImpl();
        writable.write();

        Writable writable1=new Writable()
        {
            @Override
            public void write() {
                System.out.println("writing file using annoymous");
            }
        };

        writable1.write();

        testing(new Writable() {
            @Override
            public void write() {

            }
        });


        Writable writable2= ()->  {};
    }
}
