package decidingFactorReturns.structures;

import decidingFactorReturns.policies.Policy;

public class IncompletePolicy {

    private Policy policy;
    private String conditionNode;

    public String getConditionNode() {
        return conditionNode;
    }

    public void setConditionNode(String conditionNode) {
        this.conditionNode = conditionNode;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public boolean hasCondition() {
        return conditionNode != null;
    }
}
