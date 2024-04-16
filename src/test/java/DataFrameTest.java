
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

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

}




