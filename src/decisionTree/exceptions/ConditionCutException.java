package decisionTree.exceptions;

import decisionTree.structures.Node;

public class ConditionCutException extends CutException{

    private Node node;

    public ConditionCutException(float expected, float got, Node node) {
        super(expected, got);
        this.node = node;
    }

}
