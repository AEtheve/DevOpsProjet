import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        try {
            DataFrame df = new DataFrame("src/main/ressources/example3.csv");
            // df.displayFirst(1);
            System.out.println();
            // df.displayLast(1);
            // df.display();
            df.selectRows(new int[]{0, 2}).display();
            df.selectColumns(new String[]{"Nom"}).display();
            double meanAge = df.calculateMean("Age");
            System.out.println("Moyenne de l'âge : " + meanAge);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}