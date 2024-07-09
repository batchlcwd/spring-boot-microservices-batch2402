package inherit;

public class Demo {
    public static void main(String[] args) {
        Human human = new Human();
        human.eat();
        System.out.println(human.food);
        human.sleep();
    }
}
