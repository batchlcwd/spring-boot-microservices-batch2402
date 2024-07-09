package encap;

public class Samosa {
    private String type = "teekha";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.equals("Meetha") || type.equals("Teekha"))
            this.type = type;
        else {
            System.out.println("Invalid samosa value !!");
            throw new RuntimeException("Invalid Value !!");
        }

    }

}
