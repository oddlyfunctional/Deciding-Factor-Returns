package decidingFactorReturns.policies;

import decidingFactorReturns.exceptions.PolicyException;
import decidingFactorReturns.exceptions.WorstPolicyException;
import decidingFactorReturns.structures.Node;
import decidingFactorReturns.utils.I18n;

public class Worst extends Policy {

    private float worst;

    @Override
    protected void beforeIterate() {
        worst = Float.POSITIVE_INFINITY;
    }

    @Override
    protected void iterate(float childValue) {
        if (childValue < worst) {
            worst = childValue;
        }
    }

    @Override
    protected void afterIterate() {
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
        throw new IllegalStateException(I18n.t("error_worst_condition"));
    }

    @Override
    public void setConditionNode(Node condition) {
        throw new IllegalStateException(I18n.t("error_worst_condition"));
    }
}
