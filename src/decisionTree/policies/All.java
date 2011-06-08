package decisionTree.policies;

import decisionTree.exceptions.AllCutException;
import decisionTree.exceptions.CutException;
import decisionTree.structures.Node;
import java.util.List;

public class All extends Policy {

    @Override
    public float evaluate(Float cutValue, List<Node> children) throws CutException{
        float average = 0;
        for(Node child : children){
            average += child.getValue();
        }
        average /= children.size();
        if(average < cutValue){
            throw new AllCutException(cutValue, average);
        }
        return average;
    }

}
