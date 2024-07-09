package inherit;

public class Human extends Animal {

    String food = "Rice";

    public void eat() {
        System.out.println("wow human  is eating " + this.food + " with " + super.food);
    }

    public void sleep() {
        System.out.println("human is sleeping");
    }

}
