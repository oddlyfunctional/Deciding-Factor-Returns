package decidingFactorReturns.policies;

import decidingFactorReturns.exceptions.CutException;
import decidingFactorReturns.structures.Node;
import java.util.List;

public abstract class Policy {

    public abstract float evaluate(Float cutValue, List<Node> children) throws CutException;
}
