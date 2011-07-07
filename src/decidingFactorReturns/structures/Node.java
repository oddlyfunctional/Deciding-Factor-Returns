package decidingFactorReturns.structures;

import decidingFactorReturns.exceptions.PolicyException;
import java.util.ArrayList;
import java.util.List;
import decidingFactorReturns.policies.Policy;
import decidingFactorReturns.utils.I18n;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Node {

    /* CONSTANTS */
    public static final float BOTTOM_VALUE = -5.0f;
    public static final float TOP_VALUE = 5.0f;
    public static final float BOTTOM_WEIGHT = -1.0f;
    public static final float TOP_WEIGHT = 1.0f;
    public static final int BOTTOM_IMPORTANCE = 1;
    public static final int TOP_IMPORTANCE = 100;

    /* ATTRIBUTES */
    private Float value;
    private Float minValue;
    private Float maxValue;
    private Float positiveWeight;
    private Float negativeWeight;
    private Integer importance;
    private String description;
    private String name;
    private Policy policy;
    private List<Node> children;

    public Node() {
        children = new ArrayList();
        value = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Float value) {
        validateValue(value);
        this.value = value;
    }

    public Float getValue() throws PolicyException {
        if (value == null){
            value = policy.evaluate(this, children);
        }
        throw new NotImplementedException();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public void addChild(Node node) {
        children.add(node);
    }

    public List<Node> getChildren() {
        return children;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        validateImportance(importance);
        this.importance = importance;
    }

    public Float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Float maxValue) {
        validateValue(maxValue);
        this.maxValue = maxValue;
    }

    public Float getMinValue() {
        return minValue;
    }

    public void setMinValue(Float minValue) {
        validateValue(minValue);
        this.minValue = minValue;
    }

    public Float getNegativeWeight() {
        return negativeWeight;
    }

    public void setNegativeWeight(Float negativeWeight) {
        this.negativeWeight = negativeWeight;
    }

    public Float getPositiveWeight() {
        return positiveWeight;
    }

    public void setPositiveWeight(Float positiveWeight) {
        this.positiveWeight = positiveWeight;
    }

    public boolean isHipotesis(){
        return children.isEmpty();
    }

    public boolean isValid(){
        float value = getWeightedValue();
        return value >= minValue || value <=maxValue;
    }

    private void validateValue(Float value) {
        if (value < BOTTOM_VALUE) {
            throw new IllegalArgumentException(I18n.t("error_bottom_value"));
        }
        if (value > TOP_VALUE) {
            throw new IllegalArgumentException(I18n.t("error_top_value"));
        }
    }

    private void validateWeight(Float weight) {
        if (weight < BOTTOM_WEIGHT) {
            throw new IllegalArgumentException(I18n.t("error_bottom_weight"));
        }
        if (weight > TOP_WEIGHT) {
            throw new IllegalArgumentException(I18n.t("error_top_weight"));
        }
    }

    private void validateImportance(int importance) {
        if (importance < BOTTOM_IMPORTANCE) {
            throw new IllegalArgumentException(I18n.t("error_bottom_importance"));
        }
        if (importance > TOP_IMPORTANCE) {
            throw new IllegalArgumentException(I18n.t("error_top_importance"));
        }
    }

    public Float getWeightedValue() {
        return value < 0 ? value * negativeWeight : value * positiveWeight;
    }
}
