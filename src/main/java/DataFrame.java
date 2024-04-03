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
    

    public void display() {
        System.out.println(Arrays.toString(columns));
        for (Object[] row : data) {
            System.out.println(Arrays.toString(row));
        }

    }
}
