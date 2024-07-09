package maps;

import java.io.FilterOutputStream;
import java.util.*;
import java.util.function.BiConsumer;

public class Example {
    public static void main(String[] args) {

        // collection friends->marks

        Map<String, Integer> friends = new HashMap<>();
        // put
        friends.put("ankit", 90);
        friends.put("ravi", 92);
        friends.put("durgesh", 95);
        friends.put("amresh", 111111);
        friends.put("pari", 10);
        friends.put("ankit", 40);

        //key duplications--not  allowed
        System.out.println(friends);
        //getting
        Integer amresh = friends.get("amresh");
        System.out.println(amresh);
        System.out.println(friends.size());
        System.out.println(friends.isEmpty());

//        getting all the keys

        Set<String> keySet = friends.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key + " => " + friends.get(key));
        }

        Collection<Integer> values = friends.values();
        for (int value : values) {
            System.out.println(value);
        }

        System.out.println(friends.containsKey("ankit kumar"));
        friends.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String key, Integer value) {
                System.out.println(key + " : " + value);
            }
        });
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.putAll(friends);
        System.out.println(treeMap);



    }
}
