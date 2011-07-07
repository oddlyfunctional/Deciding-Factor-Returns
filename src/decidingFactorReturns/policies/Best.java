package decidingFactorReturns.policies;

import decidingFactorReturns.exceptions.BestCutException;
import decidingFactorReturns.exceptions.CutException;
import decidingFactorReturns.structures.Node;
import java.util.List;

public class Best extends Policy {

    @Override
    public float evaluate(Float cutValue, List<Node> children) throws CutException {
        float best = -5;
        for(Node child : children){
            if (child.getValue() > best){
                best = child.getValue();
            }
        }
        if (best < cutValue){
            throw new BestCutException(cutValue, best);
        }
        return best;
    }
}
