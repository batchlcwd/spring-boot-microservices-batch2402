package collections;

import java.util.*;
import java.util.function.Consumer;

public class FirstExample {
    public static void main(String[] args) {

        Collection<Integer> numbers = new ArrayList<>();
        numbers.add(12);
        numbers.add(56);
        numbers.add(7700);
        System.out.println(numbers);

        List<String> friends = new ArrayList<>();
        friends.add("ankit");
        friends.add(0, "ram singh");
        friends.add("shyammu");
        friends.add("raman");
        friends.add("shivam");
        friends.add("raghav");
        System.out.println(friends);

        Set<Double> set = new HashSet<>();
        set.add(3.3);
        set.add(4.78);
        set.add(33.23);
        System.out.println(set);


        // Iterator--> collection[ child of all Collection]

        //ListIterator-> List [child of all List]

        //Enumeration -> Vector

        //Iterable -> foreach loop[all the collection can be traverse through iterable(for each loop)]

        //forEach emthod--> java 8


        for (double d : set) {
            System.out.println(d);
        }


        // iterator

        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


        //list iterator--> forward and backward

        ListIterator<String> stringListIterator = friends.listIterator(friends.size());
        while (stringListIterator.hasPrevious()) {
            System.out.println(stringListIterator.previous());
        }


        //for each

        friends.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("value =>"+s);
            }
        });

        TreeSet<String> treeSet=new TreeSet<>();
        treeSet.addAll(friends);
        System.out.println(treeSet);


    }
}
