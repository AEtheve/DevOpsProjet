# DevOpsProjet

Commande :
mvn -B package --file pom.xml
mvn exec:java -Dexec.mainClass="Main"

Afficher tout le dataframe = ok : display()
Afficher seulement les premieres lignes = ok : displayFirst(int numRows)
Afficher seulement les dernieres lignes = ok : displayLast(int numRows)

Un sous-ensemble de lignes a partir de leur index = ok : selectRows(int[] indices)
Un sous-ensemble de colonnes en utilisant les labels = ok : selectColumns(String[] labels)
mecanisme de selection avance = ok : filter(String columnName, double seuil)

Statistiques sur un dataframe = ok : calculateMaximum(String columnName) / calculateMinimum(String columnName) / calculateMean(String columnName)