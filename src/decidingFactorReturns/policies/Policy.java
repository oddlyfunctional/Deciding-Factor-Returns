package decidingFactorReturns.policies;

import decidingFactorReturns.exceptions.InvalidConditionFactorException;
import decidingFactorReturns.exceptions.PolicyException;
import decidingFactorReturns.structures.Node;
import java.util.List;

public abstract class Policy {

    protected Node conditionNode;
    protected int totalImportance;

    public Node getConditionNode() {
        return conditionNode;
    }

    public void setConditionNode(Node condition) {
        this.conditionNode = condition;
    }

    protected abstract void beforeIterate();

    protected abstract void iterate(float childValue);

    protected abstract void afterIterate();

    protected abstract float evaluationValue();

    protected abstract PolicyException exception();

    public float evaluate(Node parent, List<Node> children) throws PolicyException {
        totalImportance = 0;
        for (Node child : children) {
            totalImportance += child.getImportance();
        }
        beforeIterate();
        for (Node child : children) {
            if (conditionNode != null) {
                if (isInvalid(importanceOfNode(conditionNode), parent)) {
                    throw new InvalidConditionFactorException();
                }
            }
            iterate(importanceOfNode(child));
        }
        afterIterate();
        if (isInvalid(evaluationValue(), parent)) {
            throw exception();
        }
        return evaluationValue();
    }

    private boolean isInvalid(float value, Node parent) {
        return value >= parent.getMinValue() && value <= parent.getMaxValue();
    }

    private float importanceOfNode(Node child) {
        return child.getWeightedValue() * normalizedImportance(child);
    }

    private float normalizedImportance(Node child) {
        return child.getImportance() * 100 / totalImportance;
    }
}
