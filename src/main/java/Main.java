import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        try {
            DataFrame df = new DataFrame("src/main/ressources/example1.csv");
            df.display();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}