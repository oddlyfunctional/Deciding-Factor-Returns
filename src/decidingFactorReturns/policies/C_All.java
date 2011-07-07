package decidingFactorReturns.policies;

import decidingFactorReturns.exceptions.AllCutException;
import decidingFactorReturns.exceptions.ConditionCutException;
import decidingFactorReturns.exceptions.CutException;
import decidingFactorReturns.structures.Node;
import java.util.List;

public class C_All extends Policy {

    private Node condition;

    public C_All(Node condition) {
        this.condition = condition;
    }

    @Override
    public float evaluate(Float cutValue, List<Node> children) throws CutException {
        float average = 0;
        for(Node child : children){
            if (child.getValue() < cutValue){
                throw new ConditionCutException(average, average, child);
            }
            average += child.getValue();
        }
        average /= children.size();
        if(average < cutValue){
            throw new AllCutException(cutValue, average);
        }
        return average;
    }

}
