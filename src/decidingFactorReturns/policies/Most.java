/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package decidingFactorReturns.policies;

import decidingFactorReturns.exceptions.MostPolicyException;
import decidingFactorReturns.exceptions.PolicyException;
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
    protected void beforeIterate() {
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
    protected void afterIterate() {
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
}
