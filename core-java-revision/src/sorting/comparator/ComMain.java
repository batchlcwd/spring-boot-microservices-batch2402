package sorting.comparator;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class ComMain {
    public static void main(String[] args) {




        List<Student> students = new ArrayList<>();

        students.add(new Student(12, "amit"));
        students.add(new Student(10, "suman"));
        students.add(new Student(13, "rangeet"));
        students.add(new Student(9, "ankit"));
        students.add(new Student(20, "ravi"));


        //age

        TreeSet<Student> ageSortStudents=new TreeSet<>(new AgeComparator());
        ageSortStudents.addAll(students);

        System.out.println(ageSortStudents);
        //name wise
        TreeSet<Student> nameWiseStudents=new TreeSet<>(new NameComparator());
        nameWiseStudents.addAll(students);
        System.out.println(nameWiseStudents);

        Collections.sort(students,new AgeComparator());
        System.out.println(students);


    }
}
