
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

// Test unitaire pour la classe DataFrame
public class DataFrameTest {
    

    @Test
    public void testDFnotnull() throws IOException { // Teste la création d'un DataFrame
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        assert(df != null);
    }

    @Test
    public void testDFdisplay() throws IOException { // Teste l'affichage
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        df.display();
    }

   @Test
    public void testDFdisplayFirst() throws IOException { // Teste l'affichage des premières lignes
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        df.displayFirst(1);
        System.out.flush();
        System.setOut(old);
        String response = baos.toString();
    
        assertTrue(response.matches("Nom\\s*\\t\\s*Age\\s*\\t\\s*Note\\s*\\n\\s*Alice\\s*\\t\\s*20\\s*\\t\\s*85\\s*\\n"));
    }

    @Test
    public void testDFcalculateMean() throws IOException { // Teste la moyenne
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        double meanAge = df.calculateMean("Age"); 
        assertEquals(20.5, meanAge, 0.001); 
    }

    @Test
    public void testDFcalculateMinimum() throws IOException { // Teste le minimum
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        double minScore = df.calculateMinimum("Age"); 
        assertEquals(19.0, minScore, 0.001); 
    }

    @Test
    public void testDFcalculateMaximum() throws IOException { // Teste le maximum
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        double maxScore = df.calculateMaximum("Age"); 
        assertEquals(22.0, maxScore, 0.001); 
    }

    @Test
    public void testDFcalculateMeanNonexistentColumn() throws IOException { // Teste la moyenne pour une colonne inexistante
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        double meanNonexistent = df.calculateMean("NonexistentColumn");
        assertTrue(Double.isNaN(meanNonexistent)); 
    }

    @Test
    public void testDFcalculateMinimumNonexistentColumn() throws IOException { // Teste le minimum pour une colonne inexistante
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        double minNonexistent = df.calculateMinimum("NonexistentColumn");
        assertTrue(Double.isNaN(minNonexistent));
    }

    @Test
    public void testDFcalculateMaximumNonexistentColumn() throws IOException { // Teste le maximum pour une colonne inexistante
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        double maxNonexistent = df.calculateMaximum("NonexistentColumn");
        assertTrue(Double.isNaN(maxNonexistent)); 
    }

    @Test
    public void testDFdisplayLast() throws IOException { // Teste l'affichage des dernières lignes
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        df.displayLast(1);
        System.out.flush();
        System.setOut(old);
        String response = baos.toString();
        assertTrue(response.matches("Nom\\s*\\t\\s*Age\\s*\\t\\s*Note\\s*\\n\\s*Dave\\s*\\t\\s*22\\s*\\t\\s*80\\s*\\n"));
    }

    @Test
    public void testFilter() throws IOException { // Teste le filtrage
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        DataFrame filteredDataFrame = df.filter("Age", 25);
        assertNotNull(filteredDataFrame);
        assertEquals(0, filteredDataFrame.getData().size());
    }

    @Test
    public void testSelectColumns() { // Teste la sélection de colonnes
        try {
            DataFrame df = new DataFrame("src/main/ressources/example1.csv");
            DataFrame selectedColumns = df.selectColumns(new String[]{"Nom", "Age"});
            assertNotNull(selectedColumns);
            assertEquals(2, selectedColumns.getData().get(0).length);
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testSelectRows() { // Teste la sélection de lignes
        try {
            DataFrame df = new DataFrame("src/main/ressources/example1.csv");
            DataFrame selectedRows = df.selectRows(new int[]{0, 2}); // Sélectionne les lignes 0 et 2
            assertNotNull(selectedRows);
            assertEquals(2, selectedRows.getData().size());
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testGetData() { // Teste la récupération des données
        try {
            DataFrame df = new DataFrame("src/main/ressources/example1.csv");
            List<Object[]> data = df.getData();
            assertNotNull(data);
            assertEquals(4, data.size());
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testFilterNonNumericValues() throws IOException { // Teste le filtrage pour des valeurs non numériques
        DataFrame df = new DataFrame("src/main/ressources/non_numeric.csv");
        DataFrame filteredDataFrame = df.filter("Note", 25); 
        assertNotNull(filteredDataFrame);
        assertEquals(4, filteredDataFrame.getData().size());
    }

    @Test
    public void testFilterNull() throws IOException { // Teste le filtrage pour des valeurs nulles
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        DataFrame filteredDataFrame = df.filter("Age", 25); 
        assertEquals(0, filteredDataFrame.getData().size());
    }

    @Test
    public void testSelectRowsNull() { // Teste la sélection de lignes
        try {
            DataFrame df = new DataFrame("src/main/ressources/example1.csv");
            DataFrame selectedRows = df.selectRows(new int[]{0, 4}); // Sélectionne les lignes 0 et 4
            assertNotNull(selectedRows);
            assertEquals(1, selectedRows.getData().size());
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testSelectRowsNull2() {  // Teste la sélection de lignes
        try {
            DataFrame df = new DataFrame("src/main/ressources/example1.csv");
            DataFrame selectedRows = df.selectRows(new int[]{4}); 
            assertNotNull(selectedRows);
            assertEquals(0, selectedRows.getData().size());
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testFilterNull2() throws IOException { // Teste le filtrage pour des valeurs nulles
        DataFrame df = new DataFrame("src/main/ressources/empty.csv");
        DataFrame filteredDataFrame = df.filter("Age", 25); 
        assertEquals(0, filteredDataFrame.getData().size());
    }

    @Test
    public void testFilterNull3() throws IOException { // Teste le filtrage pour des valeurs non acceptées
        DataFrame df = new DataFrame("src/main/ressources/empty.csv");
        DataFrame filteredDataFrame = df.filter("Note", 25); 
        assertEquals(3, filteredDataFrame.getData().size());
    }


        @Test
    public void testGroupBy() throws IOException { // Teste le regroupement des données
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        Map<Object, DataFrame> groupedData = df.groupBy("Age");
        assertNotNull(groupedData);
        assertEquals(4, groupedData.size()); // Attendu: 4 groupes distincts d'âges
    }

    @Test
    public void testAggregateMean() throws IOException { // Teste l'agrégation avec la moyenne
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        Map<Object, Double> aggregatedResults = df.aggregate("mean", "Note", "Age");
        assertNotNull(aggregatedResults);
        assertEquals(4, aggregatedResults.size()); // Attendu: 4 moyennes différentes correspondant à 3 groupes d'âges différents
    }

    @Test
    public void testAggregateSum() throws IOException { // Teste l'agrégation avec la somme
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        Map<Object, Double> aggregatedResults = df.aggregate("sum", "Note", "Age");
        assertNotNull(aggregatedResults);
        assertEquals(4, aggregatedResults.size()); // Attendu: 4 sommes différentes correspondant à 3 groupes d'âges différents
    }

    @Test
    public void testAggregateMin() throws IOException { // Teste l'agrégation avec le minimum
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        Map<Object, Double> aggregatedResults = df.aggregate("min", "Note", "Age");
        assertNotNull(aggregatedResults);
        assertEquals(4, aggregatedResults.size()); // Attendu: 4 minimums différents correspondant à 3 groupes d'âges différents
    }

    @Test
    public void testAggregateMax() throws IOException { // Teste l'agrégation avec le maximum
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        Map<Object, Double> aggregatedResults = df.aggregate("max", "Note", "Age");
        assertNotNull(aggregatedResults);
        assertEquals(4, aggregatedResults.size()); // Attendu: 4 maximums différents correspondant à 3 groupes d'âges différents
    }

    @Test
    public void testAggregateOpInconnu() throws IOException { // Teste l'agrégation pour une colonne inexistante
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        Map<Object, Double> aggregatedResults = df.aggregate("test", "Note", "Age");
        assertNull(aggregatedResults);
    }

    @Test
    public void testAggregateColonneInexistante() throws IOException { // Teste l'agrégation pour une colonne inexistante
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        Map<Object, Double> aggregatedResults = df.aggregate("mean", "Note", "NonexistentColumn");
        assertNull(aggregatedResults);
    }

    @Test
    public void testAggregateOrdermean() throws IOException { // Teste l'ordre des groupes dans l'agrégation
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        
        Map<Object, Double> meanResults = df.aggregate("mean", "Note", "Age");
        assertNotNull(meanResults);
        assertEquals(4, meanResults.size());
        Integer prevAge = null; 
        
        for (Object ageObj : meanResults.keySet()) {
            if (ageObj instanceof Integer) { 
                Integer age = (Integer) ageObj;
                if (prevAge != null) {
                    assertTrue(age >= prevAge); 
                }
                prevAge = age;
            }
        }
    }
}




