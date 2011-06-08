package decisionTree.policies;

import decisionTree.exceptions.BestCutException;
import decisionTree.structures.Node;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BestTest extends TestCase {

    private Best best;
    private List<Node> nodes;

    public BestTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        best = new Best();
        nodes = new ArrayList<Node>();
        Node n = new Node();
        n.setValue(2.0F);
        nodes.add(n);
        n = new Node();
        n.setValue(3.0F);
        nodes.add(n);
    }

    @After
    public void tearDown() {
    }

    /**
     * Testa o método <i>evaluate</i> em caso de sucesso
     */
    @Test
    public void testEvaluateSuccess() throws Exception {
        System.out.println("evaluate success");
        Float cutValue = 3.0F;
        float expResult = 3.0F;
        float result = best.evaluate(cutValue, nodes);
        assertEquals(expResult, result,
                0.0F // Necessário incluir um delta para a comparação de floats
                );
    }

    /**
     * Testa o método <i>evaluate</i> em caso de falha
     */
    @Test
    public void testEvaluateFail() throws Exception {
        System.out.println("evaluate fail");
        Float cutValue = 3.1F;
        try {
            best.evaluate(cutValue, nodes);
            fail("Deveria lançar exceção");
        } catch (Exception e) {
            assertSame(e.getClass(), BestCutException.class);
        }
    }
}
