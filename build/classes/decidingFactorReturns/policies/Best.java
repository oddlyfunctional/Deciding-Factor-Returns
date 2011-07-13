package decidingFactorReturns.policies;

import decidingFactorReturns.exceptions.BestPolicyException;
import decidingFactorReturns.exceptions.PolicyException;
import decidingFactorReturns.structures.Node;

public class Best extends Policy {

    private float best;

    @Override
    protected void initialize() {
        best = Float.NEGATIVE_INFINITY;
    }

    @Override
    protected void iterate(float childValue) {
        if (childValue > best) {
            best = childValue;
        }
    }

    @Override
    protected float evaluationValue() {
        return best;
    }

    @Override
    protected PolicyException exception() {
        return new BestPolicyException();
    }

    @Override
    protected boolean considerNode(Node child) throws PolicyException {
        return child.isValid();
    }

    @Override
    protected void invalidNode() throws PolicyException {
    }
}
