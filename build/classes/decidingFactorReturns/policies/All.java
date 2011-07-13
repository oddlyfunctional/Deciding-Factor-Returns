package decidingFactorReturns.policies;

import decidingFactorReturns.exceptions.AllPolicyException;
import decidingFactorReturns.exceptions.PolicyException;
import decidingFactorReturns.structures.Node;

public class All extends Policy {

    private float sum;
    private int i;

    @Override
    protected void initialize() {
        sum = 0;
        i = 0;
    }

    @Override
    protected void iterate(float childValue) {
        sum += childValue;
        i++;
    }

    @Override
    protected float evaluationValue() {
        return sum / i;
    }

    @Override
    protected PolicyException exception() {
        return new AllPolicyException();
    }

    @Override
    protected boolean considerNode(Node child) throws PolicyException {
        return child.isValid();
    }

    @Override
    protected void invalidNode() throws PolicyException {
    }
}
