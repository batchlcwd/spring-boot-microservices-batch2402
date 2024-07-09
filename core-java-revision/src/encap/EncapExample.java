package encap;

public class EncapExample {
    public static void main(String[] args) {
        Samosa samosa = new Samosa();
        System.out.println(samosa.getType());
        samosa.setType("Meetha");
        samosa.setType("Human Samosa");
        System.out.println(samosa.getType());
        // "changes"

    }
}
