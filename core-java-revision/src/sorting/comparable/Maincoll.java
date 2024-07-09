package sorting.comparable;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Maincoll {
    public static void main(String[] args) {


        List<Student> students = new ArrayList<>();

        students.add(new Student(12, "Raman"));
        students.add(new Student(10, "suman"));
        students.add(new Student(13, "rangeet"));
        students.add(new Student(9, "ankit"));
        students.add(new Student(20, "ravi"));

        System.out.println(students);
        //sort data

        TreeSet<Student> treeSet=new TreeSet<>(students);
        System.out.println(treeSet);

    }
}
