    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;
    import java.util.stream.Collectors;

    /**
     * Une classe représentant un DataFrame pour la manipulation de données tabulaires.
     */
    public class DataFrame {
        private List<Object[]> data;
        private String[] columns;

        /**
         * Obtient les données du DataFrame.
         *
         * @return Les données du DataFrame.
         */
        public List<Object[]> getData() {
            return data;
        }
        
        /**
         * Récupère une ligne spécifique du DataFrame.
         *
         * @param rowIndex L'indice de la ligne à récupérer (commence à 0).
         * @return Un tableau d'objets contenant les valeurs de la ligne.
         * @throws IndexOutOfBoundsException Si l'indice de ligne est invalide.
         */
        public Object[] getRow(int rowIndex) {
            if (rowIndex < 0 || rowIndex >= data.size()) {
                throw new IndexOutOfBoundsException("Indice de ligne invalide : " + rowIndex);
            }
            return data.get(rowIndex);
        }

        /**
         * Constructeur prenant des données et des colonnes en tant que paramètres.
         *
         * @param data    Les données du DataFrame.
         * @param columns Les noms des colonnes.
         */
        public DataFrame(Object[][] data, String[] columns) {
            this.data = new ArrayList<>(Arrays.asList(data));
            this.columns = columns;
        }

        /**
         * Constructeur prenant un chemin de fichier CSV comme paramètre pour charger les données.
         *
         * @param csvFilePath Le chemin du fichier CSV.
         * @throws IOException En cas d'erreur lors de la lecture du fichier.
         */
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

        /**
         * Affiche les premières lignes du DataFrame.
         *
         * @param numRows Le nombre de lignes à afficher.
         */
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

        /**
         * Affiche les dernières lignes du DataFrame.
         *
         * @param numRows Le nombre de lignes à afficher.
         */
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

        /**
         * Affiche toutes les données du DataFrame.
         */
        public void display() {
            // Affiche toutes les données du DataFrame
            System.out.println(Arrays.toString(columns));
            for (Object[] row : data) {
                System.out.println(Arrays.toString(row));
            }

        }

        /**
         * Sélectionne les lignes spécifiées par les indices.
         *
         * @param indices Les indices des lignes à sélectionner.
         * @return Un nouveau DataFrame contenant les lignes sélectionnées.
         */
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

        /**
         * Sélectionne les colonnes spécifiées par les labels.
         *
         * @param labels Les noms des colonnes à sélectionner.
         * @return Un nouveau DataFrame contenant les colonnes sélectionnées.
         */
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

        /**
         * Calcule la moyenne des valeurs dans la colonne spécifiée.
         *
         * @param columnName Le nom de la colonne pour laquelle calculer la moyenne.
         * @return La moyenne des valeurs de la colonne.
         */
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
        
        /**
         * Calcule la valeur maximale dans la colonne spécifiée.
         *
         * @param columnName Le nom de la colonne pour laquelle calculer la valeur maximale.
         * @return La valeur maximale de la colonne.
         */
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

        /**
         * Filtrer les lignes du DataFrame en fonction d'une condition simple.
         *
         * @param columnName Le nom de la colonne à filtrer.
         * @param seuil  Le seuil à comparer.
         * @return Un nouveau DataFrame contenant les lignes filtrées.
         */
        public DataFrame filter(String columnName, double seuil) {
            // Obtient l'index de la colonne
            int columnIndex = Arrays.asList(columns).indexOf(columnName);
            // Vérifie si la colonne existe
            if (columnIndex == -1) {
                System.out.println("La colonne '" + columnName + "' n'existe pas.");
                return null;
            }
            
            // Initialise une liste pour stocker les données filtrées
            List<Object[]> filteredData = new ArrayList<>();
            // Parcourt les lignes du DataFrame
            for (Object[] row : data) {
                // Récupère la valeur de la colonne
                Object value = row[columnIndex];
                // Vérifie si la valeur est supérieure au seuil
                if (value instanceof String) {
                    try {
                        // Tente de convertir la valeur en Double
                        Double numericValue = Double.parseDouble((String) value);
                        // Vérifie si la valeur convertie est supérieure au seuil
                        if (numericValue > seuil) {
                            // Ajoute la ligne au DataFrame filtré
                            filteredData.add(row);
                        }
                    } catch (NumberFormatException e) {
                        // Gère les valeurs qui ne peuvent pas être converties en Double
                        System.out.println("La valeur '" + value + "' dans la colonne '" + columnName + "' n'est pas un nombre valide.");
                    }
                } else if (value instanceof Double && (Double) value > seuil) {
                    // Si la valeur est déjà de type Double, vérifie simplement si elle est supérieure au seuil
                    filteredData.add(row);
                }
            }
            // Convertit la liste en tableau pour créer un nouveau DataFrame
            Object[][] filteredDataArray = filteredData.toArray(new Object[filteredData.size()][]);
            // Retourne un nouveau DataFrame contenant les lignes filtrées
            return new DataFrame(filteredDataArray, columns);
        }
        

        
    }
