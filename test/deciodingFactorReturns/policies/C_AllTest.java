package deciodingFactorReturns.policies;

import decidingFactorReturns.policies.C_All;
import decidingFactorReturns.exceptions.AllCutException;
import decidingFactorReturns.exceptions.ConditionCutException;
import decidingFactorReturns.structures.Node;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class C_AllTest extends TestCase {

    private C_All c_all;
    private List<Node> nodes;

    public C_AllTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        nodes = new ArrayList<Node>();
        Node n = new Node();
        n.setValue(3.0F);
        nodes.add(n);
        n = new Node();
        n.setValue(2.0F);
        nodes.add(n);
        c_all = new C_All(n);
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
        Float cutValue = 2.0F;
        float expResult = 2.5F;
        float result = c_all.evaluate(cutValue, nodes);
        assertEquals(expResult, result,
                0.0F // Necessário incluir um delta para a comparação de floats
                );
    }

    /**
     * Testa o método <i>evaluate</i> em caso de falha por ferir a condição,
     * mas quando deveria passar pela média
     */
    @Test
    public void testEvaluateFail() throws Exception {
        System.out.println("evaluate fail");
        Float cutValue = 2.1F;
        try {
            c_all.evaluate(cutValue, nodes);
            fail("Deveria lançar exceção");
        } catch (Exception e) {
            assertSame(e.getClass(), ConditionCutException.class);
        }
    }

    /**
     * Testa o método <i>evaluate</i> em caso de falha por ferir a condição,
     * quando não deveria passar pela média
     */
    @Test
    public void testEvaluateFailCondition() throws Exception {
        System.out.println("evaluate fail condition");
        Float cutValue = 3.0F;
        try {
            c_all.evaluate(cutValue, nodes);
            fail("Deveria lançar exceção");
        } catch (Exception e) {
            assertSame(e.getClass(), ConditionCutException.class);
        }
    }
}
