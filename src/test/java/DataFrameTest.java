
import org.junit.Test;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

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

    // @Test
    // public void testDFdisplayFirst() throws IOException {
    //     DataFrame df = new DataFrame("src/main/ressources/example1.csv");
    //     String response = df.displayFirst(1);
    //     assertEquals(response, "ID\tName\tAge\tScore\n1\tJohn\t25\t85");
    // }
}
