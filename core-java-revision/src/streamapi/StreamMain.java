package streamapi;

import java.util.stream.Stream;

public class StreamMain {
    public static void main(String[] args) {


        Stream<Integer> integerStream = Stream.of(3, 4, 5, 5);
        integerStream.distinct().forEach(System.out::println);

    }
}
