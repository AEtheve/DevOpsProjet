import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        try {
            DataFrame df = new DataFrame("src/main/ressources/example3.csv");
            df.displayFirst(1);
            System.out.println();
            System.out.println("ID\tName\tAge\tScore\n1\tJohn\t25\t85\n");
            // df.displayLast(1);
            // df.display();
            // df.selectRows(new int[]{0, 2}).display();
            // df.selectColumns(new String[]{"Nom"}).display();
            // double meanAge = df.calculateMean("Age");
            // System.out.println("Moyenne de l'âge : " + meanAge);

            // Calcul du minimum et du maximum de l'âge
            // double minAge = df.calculateMinimum("Age");
            // double maxAge = df.calculateMaximum("Age");
            // System.out.println("Minimum de l'âge : " + minAge);
            // System.out.println("Maximum de l'âge : " + maxAge);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}