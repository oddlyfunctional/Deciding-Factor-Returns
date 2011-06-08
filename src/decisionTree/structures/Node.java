package decisionTree.structures;

import decisionTree.exceptions.CutException;
import java.util.ArrayList;
import java.util.List;
import decisionTree.policies.Policy;

public class Node {

    private Float value;
    private Float cutValue;
    private String description;
    private String errorMessage;
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
        this.value = value;
    }

    public Float getValue() throws CutException {
        if (value == null) {
            value = policy.evaluate(cutValue, children);
        }
        return value;
    }

    public Float getCutValue() {
        return cutValue;
    }

    public void setCutValue(Float cutValue) {
        this.cutValue = cutValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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
}
