package decidingFactorReturns.controllers;

import decidingFactorReturns.policies.Policy;
import decidingFactorReturns.structures.IncompleteNode;
import decidingFactorReturns.structures.Node;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Edit {

    private Edit() {
    }
    private static Edit instance;

    public static Edit getInstance() {
        if (instance == null) {
            instance = new Edit();
        }
        return instance;
    }
    private List<Node> nodes;
    private Node root;

    public void loadTree(File file) throws IOException {
        nodes = new ArrayList<Node>();
        List<IncompleteNode> incompleteNodes = new ArrayList<IncompleteNode>();
        DataInputStream in = null;
        String rootName = null;
        try {
            in = new DataInputStream(new FileInputStream(file));
            /* Get the root node name */
            rootName = in.readUTF();
            /* Load incomplete nodes from file */
            while (true) {
                incompleteNodes.add(Node.load(in));
            }
        } catch (EOFException e) {
            /* File ended */
        } finally {
            if (in != null) {
                in.close();
            }
        }

        /* Process incomplete nodes */
        for (IncompleteNode incompleteNode : incompleteNodes) {
            Node node = incompleteNode.getNode();
            node.setHipotesis(!incompleteNode.getChildren().isEmpty());
            /* Search real children from names */
            for (String childName : incompleteNode.getChildren()) {
                node.addChild(incompleteNodes.get(incompleteNodes.indexOf(childName)).getNode());
            }

            Policy policy = incompleteNode.getPolicy().getPolicy();
            /* Search the policy's condition node, if any */
            if (incompleteNode.getPolicy().hasCondition()) {
                policy.setConditionNode(incompleteNodes.get(incompleteNodes.indexOf(incompleteNode.getPolicy().getConditionNode())).getNode());
            }

            /* Set node policy */
            node.setPolicy(policy);

            nodes.add(node);
        }

        /* Set root node */
        root = nodes.get(nodes.indexOf(rootName));
    }

    public void saveTree(File file) throws IOException {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new FileOutputStream(file));
            /* Write the root node name */
            out.writeUTF(root.getName());
            /* Write every node */
            for (Node node : nodes) {
                node.save(out);
            }
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public Node getRoot() {
        return root;
    }

    public void newTree() {
        root = new Node();
        nodes = new ArrayList<Node>();
    }
}
