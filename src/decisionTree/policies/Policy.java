package decisionTree.policies;

import decisionTree.exceptions.CutException;
import decisionTree.structures.Node;
import java.util.List;

public abstract class Policy {

    public abstract float evaluate(Float cutValue, List<Node> children) throws CutException;
}
