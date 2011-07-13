package decidingFactorReturns.policies;

import decidingFactorReturns.controllers.Controller;
import decidingFactorReturns.exceptions.InvalidConditionFactorException;
import decidingFactorReturns.exceptions.PolicyException;
import decidingFactorReturns.structures.IncompletePolicy;
import decidingFactorReturns.structures.Node;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public abstract class Policy {

    /* POLICIES CODES */
    public static final int ALL = 0;
    public static final int BEST = 1;
    public static final int MOST = 2;
    public static final int WORST = 3;
    public static final int POLICIES_CODES[] = new int[]{ALL, BEST, MOST, WORST};
    public static final String POLICIES_NAMES[] = new String[]{"ALL", "BEST", "MOST", "WORST"};

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

    protected abstract boolean considerNode(Node child) throws PolicyException;

    protected abstract float evaluationValue();

    protected abstract PolicyException exception();

    protected abstract void invalidNode() throws PolicyException;

    public float evaluate(Node parent, List<Node> children) throws PolicyException {
        totalImportance = 0;
        for (Node child : children) {
            totalImportance += child.getImportance();
        }
        initialize();
        try {
            if (hasCondition()) {
                if (isInvalid(conditionNode.getWeightedValue(), parent)) {
                    throw new InvalidConditionFactorException();
                }
            }
            for (Node child : children) {
                if (considerNode(child)) {
                    iterate(child.getWeightedValue());
                }

                if (isInvalid(child.getWeightedValue(), parent)) {
                    invalidNode();
                }
            }
            if (isInvalid(evaluationValue(), parent)) {
                throw exception();
            }
        } catch (PolicyException e) {
            Controller.getInstance().showWarning(e.getMessage());
        }
        return weightedSum(parent);
    }

    private boolean isInvalid(float value, Node parent) {
        return value < parent.getMinValue() || value > parent.getMaxValue();
    }

    public Float importanceOfNode(Node child) throws PolicyException {
        return child.getValueInPercentage() * normalizedImportance(child);
    }

    private Float normalizedImportance(Node child) {
        return child.getImportance().floatValue() / totalImportance;
    }

    private Float weightedSum(Node parent) throws PolicyException {
        float sum = 0;
        for (Node child : parent.getChildren()) {
            sum += importanceOfNode(child);
        }
        return sum;
    }

    public int policyCode() {
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
        out.writeBoolean(hasCondition());
        if (hasCondition()) {
            out.writeUTF(conditionNode.getName());
        }
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
        Policy policy = createPolicy(code);
        IncompletePolicy incomplete = new IncompletePolicy();
        incomplete.setPolicy(policy);
        boolean thereIsCondition = in.readBoolean();
        if (thereIsCondition) {
            incomplete.setConditionNode(in.readUTF());
        }
        return incomplete;
    }

    public static Policy createPolicy(int code) {
        switch (code) {
            case ALL:
                return new All();
            case BEST:
                return new Best();
            case MOST:
                return new Most();
            case WORST:
                return new Worst();
            default:
                return null;
        }
    }
}
