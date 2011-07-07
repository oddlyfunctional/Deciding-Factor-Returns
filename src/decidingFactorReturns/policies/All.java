package decidingFactorReturns.policies;

import decidingFactorReturns.exceptions.AllPolicyException;
import decidingFactorReturns.exceptions.PolicyException;

public class All extends Policy {

    private float average;
    private int i;

    @Override
    protected void beforeIterate() {
        average = 0;
        i = 0;
    }

    @Override
    protected void iterate(float childValue) {
        average += childValue;
        i++;
    }

    @Override
    protected void afterIterate() {
        average /= i;
    }

    @Override
    protected float evaluationValue() {
        return average;
    }

    @Override
    protected PolicyException exception() {
        return new AllPolicyException();
    }
}
