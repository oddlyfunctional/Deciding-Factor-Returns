package decisionTree.policies;

import decisionTree.exceptions.CutException;
import decisionTree.exceptions.WorstCutException;
import decisionTree.structures.Node;
import java.util.List;

public class Worst extends Policy {

    @Override
    public float evaluate(Float cutValue, List<Node> children) throws CutException {
        float worst = 5;
        for (Node child : children) {
            if (child.getValue() < worst) {
                worst = child.getValue();
            }
        }
        if (worst < cutValue) {
            throw new WorstCutException(cutValue, worst);
        }
        return worst;
    }
}
