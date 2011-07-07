package decidingFactorReturns.policies;

import decidingFactorReturns.exceptions.BestPolicyException;
import decidingFactorReturns.exceptions.PolicyException;

public class Best extends Policy {

    private float best;

    @Override
    protected void beforeIterate() {
        best = Float.NEGATIVE_INFINITY;
    }

    @Override
    protected void iterate(float childValue) {
        if (childValue > best) {
            best = childValue;
        }
    }

    @Override
    protected void afterIterate() {
    }

    @Override
    protected float evaluationValue() {
        return best;
    }

    @Override
    protected PolicyException exception() {
        return new BestPolicyException();
    }
}
