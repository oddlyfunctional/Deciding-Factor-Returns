/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package decidingFactorReturns;

import decidingFactorReturns.controllers.Controller;
import decidingFactorReturns.policies.All;
import decidingFactorReturns.policies.Most;
import decidingFactorReturns.structures.Node;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author visitante
 */
public class Main {

    public static void main(String[] args) {

        Controller.getInstance().newTree();
        Node root = Controller.getInstance().getRoot();
        root.setName("Raíz");
        root.setDescription("Raíz da árvore");
        root.setMinValue(-5.0f);
        root.setMaxValue(5.0f);
        root.setNegativeWeight(-1.0f);
        root.setPositiveWeight(1.0f);
        root.setImportance(100);
        root.setHipotesis(true);
        root.setPolicy(new All());

        Node node = new Node();
        node.setName("Filho01");
        node.setDescription("Filho01 da raiz");
        node.setMinValue(-4.0f);
        node.setMaxValue(4.0f);
        node.setNegativeWeight(-0.5f);
        node.setPositiveWeight(0.5f);
        node.setImportance(50);
        node.setHipotesis(false);
        root.addChild(node);
        root.getPolicy().setConditionNode(node);
        Controller.getInstance().addNode(node);

        node = new Node();
        node.setName("Filho02");
        node.setDescription("Filho02 da raiz");
        node.setMinValue(-3.0f);
        node.setMaxValue(3.0f);
        node.setNegativeWeight(-0.5f);
        node.setPositiveWeight(0.5f);
        node.setImportance(40);
        node.setHipotesis(true);
        node.setPolicy(new Most());
        root.addChild(node);
        Controller.getInstance().addNode(node);

        Node grandson = new Node();
        grandson.setName("Neto01");
        grandson.setDescription("Neto01 do Filho02");
        grandson.setMinValue(-5.0f);
        grandson.setMaxValue(3.0f);
        grandson.setNegativeWeight(-0.3f);
        grandson.setPositiveWeight(0.5f);
        grandson.setImportance(30);
        grandson.setHipotesis(false);
        node.addChild(grandson);
        Controller.getInstance().addNode(grandson);

        try {
            Controller.getInstance().saveTree(new File("teste"));
            Controller.getInstance().newTree();
            Controller.getInstance().loadTree(new File("teste"));
            for (Node n : Controller.getInstance().getNodes()) {
                System.out.println("!!!!!!!!!!!");
                System.out.println(n.getName());
                System.out.println(n.getMinValue());
                System.out.println(n.getMaxValue());
                System.out.println(n.getNegativeWeight());
                System.out.println(n.getPositiveWeight());
                System.out.println(n.getImportance());
                System.out.println(n.isHipotesis());
                System.out.println(n.getPolicy());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
