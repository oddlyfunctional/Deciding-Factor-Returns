package decidingFactorReturns.policies;

import decidingFactorReturns.exceptions.PolicyException;
import decidingFactorReturns.exceptions.WorstPolicyException;
import decidingFactorReturns.structures.Node;
import decidingFactorReturns.utils.I18n;

public class Worst extends Policy {

    private float worst;

    @Override
    protected void initialize() {
        worst = Float.POSITIVE_INFINITY;
    }

    @Override
    protected void iterate(float childValue) {
        if (childValue < worst) {
            worst = childValue;
        }
    }

    @Override
    protected float evaluationValue() {
        return worst;
    }

    @Override
    protected PolicyException exception() {
        return new WorstPolicyException();
    }

    @Override
    public Node getConditionNode() {
        return null;
    }

    @Override
    public void setConditionNode(Node condition) {
    }

    @Override
    protected boolean considerNode(Node child) {
        return true;
    }
}
