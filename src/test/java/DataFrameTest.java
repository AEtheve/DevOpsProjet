
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.*;



public class DataFrameTest {
    
    

    @Test
    public void testDFnotnull() throws IOException {
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        assert(df != null);
    }

    @Test
    public void testDFdisplay() throws IOException {
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        df.display();
    }

   @Test
    public void testDFdisplayFirst() throws IOException {
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
    public void testDFcalculateMean() throws IOException {
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        double meanAge = df.calculateMean("Age"); // Calcule la moyenne d'âge
        assertEquals(20.5, meanAge, 0.001); 
    }

    @Test
    public void testDFcalculateMinimum() throws IOException {
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        double minScore = df.calculateMinimum("Age"); // Calcule l'age minimum
        assertEquals(19.0, minScore, 0.001); 
    }

    @Test
    public void testDFcalculateMaximum() throws IOException {
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        double maxScore = df.calculateMaximum("Age"); // Calcule l'age maximum
        assertEquals(22.0, maxScore, 0.001); 
    }

    @Test
    public void testDFcalculateMeanNonexistentColumn() throws IOException {
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        double meanNonexistent = df.calculateMean("NonexistentColumn");
        assertTrue(Double.isNaN(meanNonexistent)); // Vérifie que la moyenne est NaN pour une colonne inexistante
    }

    @Test
    public void testDFcalculateMinimumNonexistentColumn() throws IOException {
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        double minNonexistent = df.calculateMinimum("NonexistentColumn");
        assertTrue(Double.isNaN(minNonexistent)); // Vérifie que le minimum est NaN pour une colonne inexistante
    }

    @Test
    public void testDFcalculateMaximumNonexistentColumn() throws IOException {
        DataFrame df = new DataFrame("src/main/ressources/example1.csv");
        double maxNonexistent = df.calculateMaximum("NonexistentColumn");
        assertTrue(Double.isNaN(maxNonexistent)); // Vérifie que le maximum est NaN pour une colonne inexistante
    }

    @Test
    public void testDFdisplayLast() throws IOException {
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



}
