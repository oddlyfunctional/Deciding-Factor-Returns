/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package decidingFactorReturns.policies;

import decidingFactorReturns.exceptions.MostPolicyException;
import decidingFactorReturns.exceptions.PolicyException;
import decidingFactorReturns.structures.Node;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marcos
 */
public class Most extends Policy {

    private List<Float> positives;
    private List<Float> negatives;

    @Override
    protected void initialize() {
        positives = new ArrayList<Float>();
        negatives = new ArrayList<Float>();
    }

    @Override
    protected void iterate(float childValue) {
        if (childValue < 0) {
            negatives.add(childValue);
        } else {
            positives.add(childValue);
        }
    }

    @Override
    protected float evaluationValue() {
        float sum = 0;
        List<Float> values = null;
        if (positives.size() > negatives.size()) {
            values = positives;
        } else {
            values = negatives;
        }
        for (Float value : values) {
            sum += value;
        }

        return sum / values.size();
    }

    @Override
    protected PolicyException exception() {
        return new MostPolicyException();
    }

    @Override
    protected boolean considerNode(Node child) throws PolicyException {
        return child.isValid();
    }

    @Override
    protected void invalidNode() throws PolicyException {
    }
}
