package decidingFactorReturns.structures;

import java.util.ArrayList;
import java.util.List;

public class IncompleteNode {

    private Node node;
    private List<String> children;
    private IncompletePolicy policy;

    public IncompleteNode() {
        children = new ArrayList<String>();
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public IncompletePolicy getPolicy() {
        return policy;
    }

    public void setPolicy(IncompletePolicy policy) {
        this.policy = policy;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IncompleteNode) {
            return node.equals(((IncompleteNode) obj).getNode());
        }
        if (obj instanceof Node) {
            return node.equals(obj);
        }
        if (obj instanceof String) {
            return node.equals(obj);
        }
        return false;
    }
}
