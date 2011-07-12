package decidingFactorReturns.policies;

import decidingFactorReturns.exceptions.InvalidConditionFactorException;
import decidingFactorReturns.exceptions.PolicyException;
import decidingFactorReturns.structures.IncompletePolicy;
import decidingFactorReturns.structures.Node;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class Policy {

    /* POLICIES CODES */
    private static final int ALL = 0;
    private static final int BEST = 1;
    private static final int MOST = 2;
    private static final int WORST = 3;

    /* ATTRIBUTES */
    protected Node conditionNode;
    protected int totalImportance;

    public Node getConditionNode() {
        return conditionNode;
    }

    public void setConditionNode(Node condition) {
        this.conditionNode = condition;
    }

    public boolean hasCondition() {
        return conditionNode != null;
    }

    protected abstract void initialize();

    protected abstract void iterate(float childValue);

    protected abstract boolean considerNode(Node child);

    protected abstract float evaluationValue();

    protected abstract PolicyException exception();

    public float evaluate(Node parent, List<Node> children) throws PolicyException {
        totalImportance = 0;
        for (Node child : children) {
            totalImportance += child.getImportance();
        }
        initialize();
        for (Node child : children) {
            if (hasCondition()) {
                if (isInvalid(importanceOfNode(conditionNode), parent)) {
                    throw new InvalidConditionFactorException();
                }
            }
            if (considerNode(child)) {
                iterate(importanceOfNode(child));
            }
        }
        if (isInvalid(evaluationValue(), parent)) {
            throw exception();
        }
        return evaluationValue();
    }

    private boolean isInvalid(float value, Node parent) {
        return value < parent.getMinValue() || value > parent.getMaxValue();
    }

    private float importanceOfNode(Node child) {
        return child.getWeightedValue() * normalizedImportance(child);
    }

    private float normalizedImportance(Node child) {
        return child.getImportance() * 100 / totalImportance;
    }

    private int policyCode() {
        Class klass = this.getClass();
        if (klass == All.class) {
            return ALL;
        }
        if (klass == Best.class) {
            return BEST;
        }
        if (klass == Most.class) {
            return MOST;
        }
        if (klass == Worst.class) {
            return WORST;
        }
        return -1;
    }

    /**
     * Writes the policy to an output stream, following the order:
     * <ul>
     *  <li>the policy's numerical code</li>
     *  <li>a boolean stating if there's a condition node</li>
     *  <li>the name of the condition node, if any</li>
     * </ul>
     */
    public void save(DataOutputStream out) throws IOException {
        out.writeInt(policyCode());
        out.writeBoolean(conditionNode != null);
        out.writeUTF(conditionNode.getName());
    }

    /**
     * Reads an incomplete policy from an input stream, following the order:
     * <ul>
     *  <li>the policy's numerical code</li>
     *  <li>a boolean stating if there's a condition node</li>
     *  <li>the name of the condition node, if any</li>
     * </ul>
     */
    public static IncompletePolicy load(DataInputStream in) throws IOException {
        int code = in.readInt();
        Policy policy = null;
        switch (code) {
            case ALL:
                policy = new All();
                break;
            case BEST:
                policy = new Best();
                break;
            case MOST:
                policy = new Most();
                break;
            case WORST:
                policy = new Worst();
                break;
        }
        IncompletePolicy incomplete = new IncompletePolicy();
        incomplete.setPolicy(policy);
        boolean thereIsCondition = in.readBoolean();
        if (thereIsCondition) {
            incomplete.setConditionNode(in.readUTF());
        }
        return incomplete;
    }
}
