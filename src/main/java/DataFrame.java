import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFrame {
    private List<Object[]> data;
    private String[] columns;

    public DataFrame(Object[][] data, String[] columns) {
        this.data = new ArrayList<>(Arrays.asList(data));
        this.columns = columns;
    }

    public DataFrame(String csvFilePath) throws IOException {
        this.data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
        String line;
        boolean firstLine = true;
        while ((line = br.readLine()) != null) {
            if (firstLine) { 
                // La première ligne est les noms des colonnes
                this.columns = line.split(",");
                firstLine = false;
            } else { 
                // Les autres lignes sont les données
                String[] rowData = line.split(",");
                data.add(rowData);
            }
        }
        br.close();
    }

    public void displayFirst(int numRows) {
        // Affiche numRows les premières lignes du DataFrame 
        for (String column : columns) {
            System.out.print(column + "\t");
        }
        System.out.println();
        for (int i = 0; i < numRows && i < data.size(); i++) {
            Object[] row = data.get(i);
            for (Object cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }

    public void displayLast(int numRows) {
        // Affiche numRows les dernières lignes du DataFrame
        for (String column : columns) {
            System.out.print(column + "\t");
        }
        System.out.println();
        for (int i = Math.max(0, data.size() - numRows); i < data.size(); i++) {
            Object[] row = data.get(i);
            for (Object cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }
    

    public void display() {
        System.out.println(Arrays.toString(columns));
        for (Object[] row : data) {
            System.out.println(Arrays.toString(row));
        }

    }
}
