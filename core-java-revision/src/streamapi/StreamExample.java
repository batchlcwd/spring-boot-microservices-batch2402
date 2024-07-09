package streamapi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {

        //
        List<Integer> marks = new ArrayList<>();
        marks.add(90);
        marks.add(10);
        marks.add(30);
        marks.add(73);
        marks.add(20);

        //even wale sare marks

        //filtering

//        Stream<Integer> stream = marks.stream();
//        stream.fi
        marks.stream()
                .filter(item -> item % 2 == 0)
                .forEach(StreamExample::dkPrint);


//        System.out.println(resultList);

        List<Integer> collect = marks.stream()
                .map(input -> input + 10)
                .collect(Collectors.toList());

        System.out.println(collect);


    }

    public static void dkPrint(int n) {
        System.out.println(n);
    }
}
