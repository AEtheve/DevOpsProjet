import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
                // La première ligne correspond aux noms des colonnes
                this.columns = line.split(",");
                firstLine = false;
            } else { 
                // Les autres lignes correspondent aux données
                String[] rowData = line.split(",");
                data.add(rowData);
            }
        }
        br.close();
    }

    public void displayFirst(int numRows) {
        // Affiche numRows premières lignes du DataFrame 
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
        // Affiche numRows dernières lignes du DataFrame
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
        // Affiche toutes les données du DataFrame
        System.out.println(Arrays.toString(columns));
        for (Object[] row : data) {
            System.out.println(Arrays.toString(row));
        }

    }

    public DataFrame selectRows(int[] indices) {
        // Sélectionne les lignes spécifiées par les indices
        // Vérifie que les indices fournis sont valides
        if (indices == null || indices.length == 0) {
            System.out.println("Aucun indice spécifié pour la sélection des lignes.");
            return null;
        }
        // Initialise une liste pour stocker les données sélectionnées
        List<Object[]> selectedData = new ArrayList<>();
        // Parcourt les indices spécifiés
        for (int index : indices) {
            // Vérifie que l'index est valide
            if (index >= 0 && index < data.size()) {
                // Ajoute la ligne correspondante aux données sélectionnées
                selectedData.add(data.get(index));
            } else {
                System.out.println("L'indice " + index + " est invalide.");
            }
        }
        // Convertit la liste en tableau pour créer un nouveau DataFrame
        Object[][] selectedDataArray = selectedData.toArray(new Object[selectedData.size()][]);
        // Retourne un nouveau DataFrame contenant les lignes sélectionnées
        return new DataFrame(selectedDataArray, columns);
    }

    public DataFrame selectColumns(String[] labels) {
        // Sélectionne les colonnes spécifiées par les labels
        int[] selectedIndices = new int[labels.length];
        // Parcourt les labels spécifiés
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                if (columns[j].equals(labels[i])) {
                    selectedIndices[i] = j;
                    break;
                }
            }
        }
        // Initialise un tableau pour stocker les données sélectionnées
        Object[][] selectedData = new Object[data.size()][labels.length];
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < labels.length; j++) {
                selectedData[i][j] = data.get(i)[selectedIndices[j]];
            }
        }
        // Crée et retourne un nouveau DataFrame contenant les colonnes sélectionnées
        return new DataFrame(selectedData, labels);
    }

    public double calculateMean(String columnName) {
        int columnIndex = Arrays.asList(columns).indexOf(columnName);
        double mean = 0; // Déclaration de mean en tant que double
        if (columnIndex == -1) {
            System.out.println("La colonne '" + columnName + "' n'existe pas.");
            return Double.NaN;
        }
        // Récupère les valeurs de la colonne
        List<Double> values = data.stream().map(row -> Double.parseDouble(row[columnIndex].toString())).collect(Collectors.toList());
        // Calcule la moyenne
        mean = values.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
        return mean;
    }

    public double calculateMinimum(String columnName) {
        // Obtient l'index de la colonne
        int columnIndex = Arrays.asList(columns).indexOf(columnName);
        // Vérifie si la colonne existe
        if (columnIndex == -1) {
            System.out.println("La colonne '" + columnName + "' n'existe pas.");
            return Double.NaN;
        }
        // Récupère les valeurs de la colonne
        List<Double> values = data.stream()
                                  .map(row -> Double.parseDouble(row[columnIndex].toString()))
                                  .collect(Collectors.toList());
        // Trouve la valeur minimale
        return values.stream().mapToDouble(Double::doubleValue).min().orElse(Double.NaN);
    }

    public double calculateMaximum(String columnName) {
        // Obtient l'index de la colonne
        int columnIndex = Arrays.asList(columns).indexOf(columnName);
        // Vérifie si la colonne existe
        if (columnIndex == -1) {
            System.out.println("La colonne '" + columnName + "' n'existe pas.");
            return Double.NaN;
        }
        // Récupère les valeurs de la colonne
        List<Double> values = data.stream()
                                  .map(row -> Double.parseDouble(row[columnIndex].toString()))
                                  .collect(Collectors.toList());
        // Trouve la valeur maximale
        return values.stream().mapToDouble(Double::doubleValue).max().orElse(Double.NaN);
    }

    
}
