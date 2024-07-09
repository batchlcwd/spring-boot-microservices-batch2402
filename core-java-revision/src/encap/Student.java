package encap;

public class Student {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new RuntimeException("invalid age");
        }
        this.age = age;
    }
}
