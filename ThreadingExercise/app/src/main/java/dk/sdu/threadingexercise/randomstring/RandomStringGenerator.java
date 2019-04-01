package dk.sdu.threadingexercise.randomstring;

public class RandomStringGenerator {

    private static final char min = 33; // !
    private static final char max = 126; // ~
    private int length;

    public RandomStringGenerator(int length) {
        this.length = length;
    }

    public String get() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            builder.append((char) (Math.random() * (max - min) + min));
        }

        return builder.toString();
    }
}
